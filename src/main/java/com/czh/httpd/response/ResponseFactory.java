package com.czh.httpd.response;

import com.czh.httpd.App;
import com.czh.httpd.enums.HttpStatus;
import com.czh.httpd.header.BaseRequestHeader;
import com.czh.httpd.header.BaseResponseHeader;
import com.czh.httpd.header.ResponseHeaderFactory;
import com.czh.httpd.util.HttpHeaderUtil;
import com.czh.httpd.util.ResourcesLoader;
import org.apache.commons.lang3.StringUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author czh
 * 响应工厂类
 */
public class ResponseFactory {
    public static Response getIndexResponse(String cookie) {
        return getResponseByResource("/static/index.html", cookie, HttpStatus.OK, "");
    }

    public static Response getErrorResponse(String message, String cookie) {
        return getResponseByResource("/static/error.html", cookie, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static Response getNotFoundResponse(String cookie, String url) {
        return getResponseByResource("/static/404.html", cookie, HttpStatus.NOT_FOUND, url);
    }

    public static Response getResponseByUrl(String url, String cookie, BaseRequestHeader requestHeader) {
        System.out.println("url: " + url);
        if ("/".equals(url)) {
            return getIndexResponse(cookie);
        }
        Path path = Paths.get(App.WORK_SPACE, url);
        File file = path.toFile();
        if (!file.exists()) {
            return getNotFoundResponse(cookie, url);
        }
        System.out.println("file length: " + file.length());
        // 先粗暴处理
        String contentType = new MimetypesFileTypeMap().getContentType(file);
        System.out.println("contentType: " + contentType);
        String range = requestHeader.getHeader("Range");
        byte[] content;
        BaseResponseHeader responseHeader = ResponseHeaderFactory.getBaseResponseHeader(cookie, HttpStatus.OK);
        if (StringUtils.isNotBlank(range)) {
            System.out.println("Range: " + range);
            contentType = "image/png";
            try {
                int[] rangeArr = HttpHeaderUtil.parseRequestRange(range);
                int start = rangeArr[0];
                int end = rangeArr[1];
                int len = end - start + 1;
                content = ResourcesLoader.getBytes(file, start, end);
                String responseRange = String.format("bytes %d-%d/%d", start, end, file.length());
                System.out.println("responseRange: " + responseRange);
                responseHeader.setHeader("Content-Length", String.valueOf(len));
                responseHeader.setHeader("Content-Range", responseRange);
                responseHeader.setHttpStatus("206");
                responseHeader.setStatusName(HttpStatus.PARTIAL_CONTENT.getReasonPhrase());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("处理Range出错");
                return ResponseFactory.getErrorResponse(e.getMessage(), cookie);
            }
        } else {
            System.out.println("not range");
            content = ResourcesLoader.getBytes(file);
            responseHeader.setHeader("Content-Length", String.valueOf(file.length()));
        }
        responseHeader.setHeader("Content-Type", contentType);
        return new Response(responseHeader, content);
    }

    private static Response getResponseByResource(String resourcePath, String cookie, HttpStatus httpStatus, String message) {
        String content = "";
        try {
            content = ResourcesLoader.getResourceAsString(resourcePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取默认页出错");
            return ResponseFactory.getErrorResponse("读取默认页出错", cookie);
        }
        if (StringUtils.isNotBlank(message)) {
            content = String.format(content, message);
        }
        int contentLength = content.getBytes().length;
        BaseResponseHeader header = ResponseHeaderFactory.getHtmlResponseHeader(contentLength, cookie, httpStatus);
        Response response = new Response();
        response.setResponseHeader(header);
        response.setResponseContent(content.getBytes());
        return response;
    }

//  res.setHeader('Content-Type', 'application/octet-stream');
    // 在响应之前，告诉浏览器，这个数据是要作为文件下载的
    // 通过设置响应报文头实现
//  res.setHeader('Content-Disposition', 'attachment; filename=demo.txt');
}
