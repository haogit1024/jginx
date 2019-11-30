package com.czh.httpd.exception;

/**
 * @author czh
 * 解析http header异常
 */
public class HeaderFormatException extends RuntimeException {
    public HeaderFormatException(String message) {
        super(message);
    }
}
