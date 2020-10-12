package com.czh.httpd.strategy;

import com.czh.httpd.App;
import com.czh.httpd.constant.CommonConstants;
import com.czh.httpd.enums.CommonEnum;
import com.czh.httpd.util.FileUtil;

import java.io.File;

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
            String defaultConfigFilePath = App.class.getResource("/")
                    + CommonConstants.SystemConfig.DEFAULT_JSON_CONFIG_PATH;
            String defaultServerDirPath = App.class.getResource("/")
                    + CommonConstants.SystemConfig.DEFAULT_SERVER_DIR_PATH;
            File defaultConfigFile = new File(defaultConfigFilePath);
            File defaultServerDir = new File(defaultServerDirPath);
            assert defaultConfigFile.exists() : "默认配置文件不存在";
            assert defaultServerDir.exists() : "默认serverDir不存在";
            FileUtil.copyFile(defaultConfigFilePath, CommonConstants.SystemConfig.DEFAULT_JSON_CONFIG_PATH);
            FileUtil.copyFile(defaultServerDirPath, CommonConstants.SystemConfig.DEFAULT_SERVER_DIR_PATH);
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
        return null;
    }

    /**
     * 运行命令
     * @param command
     */
    public static void run(CommonEnum.Command command) {
        CommandStrategy commandStrategy = valueOf(command);
        if (command == null) {
            // TODO 用户错误
            throw new RuntimeException("");
        }
        commandStrategy.run();
    }
}
