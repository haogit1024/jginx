package com.czh.httpd.enums;

/**
 * @author chenzh
 * @date 2020/8/7
 */
public interface CommonEnum {
    /**
     * log 等级
     */
    enum LogLever {
    }

    /**
     * 命令
     */
    enum Command {
        INIT("init"),
        RELOAD("reload"),
        START("start"),
        STOP("stop"),
        ;
        Command(String name) {
        }
    }
}
