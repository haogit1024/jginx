package com.czh.httpd.header;

import com.czh.httpd.enums.HttpStatus;

import java.util.Date;

/**
 * @author czh
 * HttpResponse工厂类
 */
public class ResponseHeaderFactory {
    public static BaseResponseHeader getHtmlResponseHeader(long contentLength, String cookie, HttpStatus httpStatus) {
        BaseResponseHeader header = getBaseResponseHeader(contentLength, cookie, httpStatus);
        header.setHeader("Content-Type", "text/html;charset=UTF-8");
        return header;
    }

    public static BaseResponseHeader getBaseResponseHeader(long contentLength, String cookie, HttpStatus httpStatus) {
        BaseResponseHeader header = new SimpleResponseHeader();
        header.setHttpVersion("HTTP/1.1");
        header.setHttpStatus(httpStatus.getValue());
        header.setStatusName(httpStatus.getReasonPhrase());
        header.setHeader("Server", "czh Server/0.0.1");
        header.setHeader("Date", new Date().toString());
        header.setHeader("Content-Length", String.valueOf(contentLength));
        header.setHeader("Cookie", cookie);
        return header;
    }
}
