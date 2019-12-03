package com.czh.httpd.header;

import com.czh.httpd.enums.HttpStatus;

import java.util.Date;

/**
 * @author czh
 * HttpResponse工厂类
 */
public class ResponseHeaderFactory {
    public static BaseResponseHeader getHtmlResponse(int contentLength, String cookie, HttpStatus httpStatus) {
        BaseResponseHeader header = new SimpleResponseHeader();
        header.setHttpVersion("HTTP/1.1");
        header.setHttpStatus(httpStatus.getValue());
        header.setStatusName(httpStatus.getReasonPhrase());
        header.setHeader("Server", "czh Server/0.0.1");
        header.setHeader("Date", new Date().toString());
        header.setHeader("Content-type", "text/html;charset=UTF-8");
        header.setHeader("Content-Length", String.valueOf(contentLength));
        header.setHeader("Cookie", cookie);
        return header;
    }
}
