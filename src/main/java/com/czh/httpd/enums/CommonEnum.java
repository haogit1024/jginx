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
        /**
         * 初始化
         */
        INIT("init"),
        /**
         * 重启
         */
        RELOAD("reload"),
        /**
         * 启动
         */
        START("start"),
        /**
         * 停止
         */
        STOP("stop"),
        ;
        Command(String name) {
        }
    }
}
