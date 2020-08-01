package com.czh.httpd.exception;

/**
 * @author czh
 * @date 2020/7/31
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}
