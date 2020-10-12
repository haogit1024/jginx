package com.czh.httpd.response;

import com.czh.httpd.constant.CommonConstants;
import com.czh.httpd.entity.Server;
import com.czh.httpd.enums.HttpStatus;
import com.czh.httpd.header.BaseRequestHeader;
import com.czh.httpd.header.BaseResponseHeader;
import com.czh.httpd.header.ResponseHeaderFactory;
import com.czh.httpd.header.SimpleRequestHeader;
import com.czh.httpd.util.HttpHeaderUtil;
import com.czh.httpd.util.ResourcesLoader;
import com.czh.httpd.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author czh
 * 响应工厂类
 */
public class ResponseFactory {
    public static Response getIndexResponse(String cookie, Server server) throws IOException {
        if (server != null) {
            List<String> index = server.getIndex();
            if (index.size() > 0) {
                for (String indexFileName : index) {
                    Path path = Paths.get(server.getRoot(), indexFileName);
                    File file = path.toFile();
                    if (file.exists()) {
                        return getResponseByFilePath(path, cookie, new SimpleRequestHeader());
                    }
                }
            }
        }
        return getResponseByResource(CommonConstants.Page.INDEX, cookie, HttpStatus.OK, "");
    }

    public static Response getErrorResponse(String message, String cookie) {
        return getResponseByResource(CommonConstants.Page.ERROR, cookie, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static Response getNotFoundResponse(String cookie, String url) {
        return getResponseByResource(CommonConstants.Page.NOT_FOUND, cookie, HttpStatus.NOT_FOUND, url);
    }

    /**
     * 其实这部分逻辑应该写在 simpleRequestHandler中的
     * @param url
     * @param cookie
     * @param requestHeader
     * @return
     * @throws IOException 权限不足
     */
    public static Response getResponseByUrl(String url,
                                            String cookie,
                                            Server server,
                                            BaseRequestHeader requestHeader) throws IOException {
        System.out.println("url: " + url);
        if ("/".equals(url)) {
            return getIndexResponse(cookie, server);
        }
        // 如果符合转发规则, 则转发, 不符合则本地查找
        // 先查找指定工作目录
        Path path = Paths.get(server.getRoot(), url);
        return getResponseByFilePath(path, cookie, requestHeader);
    }


    public static Response getResponseByFilePath(Path path, String cookie, BaseRequestHeader requestHeader) throws IOException {
        assert path != null : "getResponseByFilePath path为null";
        File file = path.toFile();
        if (!file.exists()) {
            return getNotFoundResponse(cookie, path.toString());
        }
        String contentType = Files.probeContentType(path);
        String range = requestHeader.getHeader("Range");
        byte[] content;
        BaseResponseHeader responseHeader = ResponseHeaderFactory.getBaseResponseHeader(cookie, HttpStatus.OK);
        if (StringUtils.isNotBlank(range)) {
            // Range 请求头支持
            System.out.println("Range: " + range);
            contentType = "image/png";
            try {
                int[] rangeArr = HttpHeaderUtil.parseRequestRange(range);
                int start = rangeArr[0];
                long end = rangeArr[1];
                if (end > file.length() - 1) {
                    end = file.length() - 1;
                }
                long len = end - start + 1;
                System.out.printf("start: %d, end: %s, len: %s\n", start, end, len);
                content = ResourcesLoader.getBytes(file, start, (int)end);
                String responseRange = String.format("bytes %d-%d/%d", start, end, file.length());
                System.out.println("responseRange: " + responseRange);
                responseHeader.setHeader("Content-Length", String.valueOf(content.length));
                responseHeader.setHeader("Content-Range", responseRange);
                responseHeader.setHttpStatus(HttpStatus.valueOf(206));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("处理Range出错");
                return ResponseFactory.getErrorResponse(e.getMessage(), cookie);
            }
        } else {
            System.out.println("not range");
            content = ResourcesLoader.getBytes(file);
            System.out.println("content length: " + content.length);
            responseHeader.setHeader("Content-Length", String.valueOf(file.length()));
        }
        responseHeader.setHeader("Content-Type", contentType);
        return new Response(responseHeader, content);
    }

    private static Response getResponseByResource(String resourcePath, String cookie, HttpStatus httpStatus, String message) {
        String content;
        try {
            content = ResourcesLoader.getResourceAsString(resourcePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取默认页出错");
            return ResponseFactory.getErrorResponse("读取默认页出错", cookie);
        }
        assert content != null : "读入resource为null, resourcePath: " + resourcePath;
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
