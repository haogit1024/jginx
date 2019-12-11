package com.czh.httpd.log;

public interface Logger {
    void debug(String s);

    void info(String s);

    void error(String s);
}
