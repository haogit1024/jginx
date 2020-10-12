package com.czh.httpd.strategy;

import com.czh.httpd.enums.CommonEnum;

import java.nio.file.Path;

/**
 * @author chenzh
 * @date 2020/10/12
 */
public enum CommandStrategy {
    /**
     * 执行初始化命令
     * 复制 resource 下的 application.json 和 server/defaultServer.json 到当前目录
     */
    INIT(CommonEnum.Command.INIT) {
        @Override
        public void run() {
//            Path resourcePath =
        }
    },
    /**
     * 执行重启命令
     */
    RESTART(CommonEnum.Command.RESTART) {
        @Override
        public void run() {

        }
    },
    /**
     * 执行启动命令
     */
    START(CommonEnum.Command.START) {
        @Override
        public void run() {

        }
    },
    /**
     * 执行停止命令
     */
    STOP(CommonEnum.Command.STOP) {
        @Override
        public void run() {

        }
    }
    ;
    private final CommonEnum.Command command;
    CommandStrategy(CommonEnum.Command command) {
        this.command = command;
    }

    public CommonEnum.Command getCommand() {
        return command;
    }

    public abstract void run();

    public static CommandStrategy valueOf(CommonEnum.Command command) {
        for (CommandStrategy item : values()) {
            if (item.getCommand().equals(command)) {
                return item;
            }
        }
//        throw new
        return null;
    }
}
