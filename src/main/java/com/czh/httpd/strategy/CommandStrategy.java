package com.czh.httpd.strategy;

import com.alibaba.fastjson.JSON;
import com.czh.httpd.App;
import com.czh.httpd.constant.CommonConstants;
import com.czh.httpd.entity.Server;
import com.czh.httpd.enums.CommonEnum;
import com.czh.httpd.enums.ExceptionEnum;
import com.czh.httpd.exception.UserException;
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
            /*FileUtil.copyFile(defaultConfigFilePath, CommonConstants.SystemConfig.DEFAULT_JSON_CONFIG_PATH);
            FileUtil.copyFile(defaultServerDirPath, CommonConstants.SystemConfig.DEFAULT_SERVER_DIR_PATH);*/
            // 判断目录下是否已经生成了文件, 如果已经生成了就跳过
            File targetDefaultConfigFile = new File(CommonConstants.SystemConfig.DEFAULT_JSON_CONFIG_PATH);
            File targetDefaultServerDir = new File(CommonConstants.SystemConfig.DEFAULT_SERVER_DIR_PATH);
            // 复制就完事了，感觉初始化这个功能有点鸡肋
            if (!targetDefaultConfigFile.exists()) {
                FileUtil.copyFile(defaultConfigFile, targetDefaultConfigFile);
            }
            if (!targetDefaultServerDir.exists()) {
                FileUtil.copyFile(defaultServerDir, targetDefaultServerDir);
            }
            File file = new File("html");
            if (!file.exists()) {
                file.mkdir();
            }
        }
    },
    /**
     * 执行重启命令
     */
    RESTART(CommonEnum.Command.RESTART) {
        @Override
        public void run() {
            // TODO 调用守护线程调用App.restart方法
        }
    },
    /**
     * 执行启动命令
     */
    START(CommonEnum.Command.START) {
        @Override
        public void run() {
            App.loadConfig();
            App.initHttpThread();
        }
    },
    /**
     * 执行停止命令
     */
    STOP(CommonEnum.Command.STOP) {
        @Override
        public void run() {
            // TODO 调用守护线程调用App.stop方法
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
     */
    public static void run(String commandStr) {
        CommonEnum.Command command = CommonEnum.Command.getByName(commandStr);
        if (command == null) {
            throw new UserException(ExceptionEnum.COMMAND_NOT_FOUND.format(commandStr));
        }
        CommandStrategy commandStrategy = valueOf(command);
        if (commandStrategy == null) {
            throw new UserException(ExceptionEnum.COMMAND_NOT_SUPPORT.format(commandStr));
        }
        commandStrategy.run();
    }
}
