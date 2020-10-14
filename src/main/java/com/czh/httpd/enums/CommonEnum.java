package com.czh.httpd.enums;

import lombok.Getter;

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
    @Getter
    enum Command {
        /**
         * 初始化
         */
        INIT("init"),
        /**
         * 重启
         */
        RESTART("restart"),
        /**
         * 启动
         */
        START("start"),
        /**
         * 停止
         */
        STOP("stop"),
        ;
        private final String name;
        Command(String name) {
            this.name = name;
        }

        public static Command getByName(String name) {
            for (Command command : values()) {
                if (command.getName().equals(name)) {
                    return command;
                }
            }
            return null;
        }
    }
}
