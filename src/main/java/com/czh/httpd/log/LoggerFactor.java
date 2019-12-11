package com.czh.httpd.log;

public class LoggerFactor {
    public static Logger getLogger(Class<?> clazz) {
        return new SimpleLogger();
    }
}
