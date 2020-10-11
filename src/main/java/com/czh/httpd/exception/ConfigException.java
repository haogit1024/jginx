package com.czh.httpd.exception;

import com.czh.httpd.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @author chenzh
 * @date 2020/10/10
 */
@Getter
public class ConfigException extends RuntimeException {
    private String message;

    private ExceptionEnum exceptionEnum;
    public ConfigException(String message) {
        super(message);
        this.message = message;
    }

    public ConfigException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
        if (exceptionEnum != null) {
            this.message = exceptionEnum.getCode() + ": " + exceptionEnum.getMessage();
        }
    }
}
