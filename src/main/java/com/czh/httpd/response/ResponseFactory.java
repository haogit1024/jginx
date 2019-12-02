package com.czh.httpd.response;

import com.czh.httpd.header.BaseResponseHeader;
import com.czh.httpd.header.SimpleResponseHeader;
import com.czh.httpd.util.ResourcesLoader;

import java.io.IOException;
import java.util.Date;

/**
 * @author czh
 * 响应工厂类
 */
public class ResponseFactory {
    /*String demo = """
            HTTP/1.1 200 OK
            Server:czh Server/0.0.1
            Date:Fri Nov 29 14:22:24 CST 2019
            Content-type:text/html;charset=UTF-8
            Content-Length:%d
            """;*/
    public static Response getIndexResponse(String cookie) {
        BaseResponseHeader header = new SimpleResponseHeader();
        String content = "";
        try {
           content = ResourcesLoader.getResourceAsString("/static/index.html");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取默认页出错");
        }
        header.setHttpVersion("HTTP/1.1");
        header.setHttpStatus("200");
        header.setStatusName("OK");
        header.setHeader("Server", "czh Server/0.0.1");
        header.setHeader("Date", new Date().toString());
        header.setHeader("Content-type", "text/html;charset=UTF-8");
        header.setHeader("Content-Length", String.valueOf(content.getBytes().length));
        header.setHeader("Cookie", cookie);
        Response response = new Response();
        response.setResponseHeader(header);
        response.setResponseContent(content);
        return response;
    }

    public static Response getResponseByUrl(String url) {
        return null;
    }
}
