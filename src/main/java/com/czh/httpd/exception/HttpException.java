package com.czh.httpd.exception;

import com.czh.httpd.enums.ExceptionEnum;

/**
 * http 异常, 报错信息会以http响应的方式返回给用户
 * @author chenzh
 * @date 2020/10/10
 */
public class HttpException extends RuntimeException {
    private String message;
    private ExceptionEnum exceptionEnum;

    public HttpException(String message) {
        super(message);
    }

    public HttpException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
        if (exceptionEnum != null) {
            this.message = exceptionEnum.getCode() + ": " + exceptionEnum.getMessage();
        }
    }
}
