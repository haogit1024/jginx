package com.czh.httpd.exception;

import com.czh.httpd.enums.ExceptionEnum;

/**
 * 用户操作错误
 * @author chenzh
 * @date 2020/10/14
 */
public class UserException extends RuntimeException {
    private String message;
    private ExceptionEnum exceptionEnum;

    public UserException(String message) {
        super(message);
    }

    public UserException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
        if (exceptionEnum != null) {
            this.message = exceptionEnum.getCode() + ": " + exceptionEnum.getMessage();
        }
    }
}
