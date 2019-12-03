package com.czh.httpd.response;

import com.czh.httpd.App;
import com.czh.httpd.enums.HttpStatus;
import com.czh.httpd.header.BaseResponseHeader;
import com.czh.httpd.header.ResponseHeader;
import com.czh.httpd.header.ResponseHeaderFactory;
import com.czh.httpd.util.ResourcesLoader;
import org.apache.commons.lang3.StringUtils;

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

    public static Response getResponseByUrl(String url, String cookie) {
        System.out.println("url: " + url);
        if ("/".equals(url)) {
            return getIndexResponse(cookie);
        }
        Path path = Paths.get(App.WORK_SPACE, url);
        File file = path.toFile();
        if (!file.exists()) {
            return getNotFoundResponse(url, cookie);
        }
        // TODO  1.判断请求类型或者文件类型, 生成response header 2.获取响应内容
        // 先粗暴处理, 所有请求都分会html文本响应头
        String content = ResourcesLoader.getContent(file);
        BaseResponseHeader responseHeader = ResponseHeaderFactory.getHtmlResponse(content.getBytes().length, cookie, HttpStatus.OK);
        return new Response(responseHeader, content);
    }

    private static Response getResponseByResource(String resourcePath, String cookie, HttpStatus httpStatus, String message) {
        String content = "";
        try {
            content = ResourcesLoader.getResourceAsString(resourcePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取默认页出错");
        }
        if (StringUtils.isNotBlank(message)) {
            content = String.format(content, message);
        }
        int contentLength = content.getBytes().length;
        BaseResponseHeader header = ResponseHeaderFactory.getHtmlResponse(contentLength, cookie, httpStatus);
        Response response = new Response();
        response.setResponseHeader(header);
        response.setResponseContent(content);
        return response;
    }
}
