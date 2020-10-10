package com.czh.httpd;

import com.alibaba.fastjson.JSON;
import com.czh.httpd.constant.CommonConstants;
import com.czh.httpd.entity.DefaultConfig;
import com.czh.httpd.entity.Server;
import com.czh.httpd.exception.BaseException;
import com.czh.httpd.properties.SettingProperties;
import com.czh.httpd.thread.HttpThread;
import com.czh.httpd.util.FileUtil;
import com.czh.httpd.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author chenzh
 */
public class App {
    /**
     * 工作目录
     */
    public static final String WORK_SPACE = "D:\\www";

    private static List<Server> serverList;

    public static DefaultConfig defaultConfig;

    /**
     * server map
     * key: port, value: server
     */
    public static Map<Integer, Server> serverMap;

    /**
     * 是否可以运行
     */
    public static boolean RUN_ABLE = true;

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append(" ");
        }
        // TODO 定义可输入的参数和参数对应的动作
        System.out.println("args: " + sb.toString());
        try {
            loadConfig();
            initHttpThread();
        } catch (BaseException e) {
            e.printStackTrace();
            // TODO 自定义异常处理
        }
    }

    /**
     * 加载配置文件
     */
    private static void loadConfig() {
        // 读取默认配置文件 如果用在系统下没找到, 就到classPath下查找
        String defaultJsonConfigPath = CommonConstants.SystemConfig.defaultJsonConfigPath;
        File defaultConfigFile = new File(defaultJsonConfigPath);
        if (!defaultConfigFile.exists()) {
            defaultConfigFile = new File(App.class.getResource("/").getPath() + defaultJsonConfigPath);
        }
        // TODO 改为自定义异常加枚举的形式, 方便统一修改
        assert defaultConfigFile.exists() : "没找到默认配置文件";
        // 读取server文件路径, 获取到所有server配置文件(目前只考虑获取第一个配置文件)
        String defaultConfigContent = FileUtil.readTextFile(defaultConfigFile);
        assert StringUtils.isNotBlank(defaultConfigContent) : "默认配置文件内容为空";
        defaultConfig = JSON.parseObject(defaultConfigContent, DefaultConfig.class);
        String serverPath = defaultConfig.getServerPath();
        assert StringUtils.isNotBlank(serverPath) : "server路径为空";
        // 读取配置文件
        File serverDir = new File(serverPath);
        if (!serverDir.exists()) {
            serverDir = new File(App.class.getResource("/").getPath() + serverPath);
        }
        assert serverDir.exists() : "serverPath不存在";
        assert serverDir.isDirectory() : "serverPath不是一个文件夹";
        String[] serverNames = serverDir.list();
        assert serverNames != null && serverNames.length > 0 : "server配置文件不存在";
        serverList = new ArrayList<>(serverNames.length);
        serverMap = new HashMap<>(serverNames.length);
        for (String serverName : serverNames) {
            // TODO 暂时粗暴处理, 后续完善读取
            if (!serverName.contains("json")) {
                continue;
            }
            File serverFile = new File(serverDir.getPath(), serverName);
            String serverContent = FileUtil.readTextFile(serverFile);
            Server server = JSON.parseObject(serverContent, Server.class);
            serverList.add(server);
            serverMap.put(server.getListen(), server);
        }
    }

    private static void initHttpThread() {
        // server 自检(先只支持一个配置文件) 1. 不能监听重复的端口 2. 检查其他配置是否正确
        Server defaultServer = serverList.get(0);
        assert defaultServer.getListen() != null : "监听端口不能为空";

        // TODO 修改为先创建线程池, 一个server创建一个循环线程, 并保存到一个map中, 循环线程统一向一个线程池提交线程
        ExecutorService executor = Executors.newFixedThreadPool(defaultConfig.getMaxTheadNum());
        new Thread(() -> {
            ServerSocket server = null;
            try {
                server = new ServerSocket(defaultServer.getListen());
                System.out.println("启动成功");
                while (RUN_ABLE) {
                    Socket socket = server.accept();
                    try {
//                        new HttpThread(socket).start();
                        executor.submit(new HttpThread(socket, defaultServer));
                    } catch (Exception e) {
                        e.printStackTrace();
                        // TODO 自定义异常判断
                    }
                }
            } catch (IOException e) {
                // TODO 处理端口被占用
                e.printStackTrace();
            } finally {
                executor.shutdown();
                try {
                    executor.awaitTermination(1, TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.err.println("线程池停止出错");
                }
                if (server != null) {
					try {
						server.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
        }).start();
    }

    /**
     * 初始化一个守护线程, 用来管理运行中的程序
     * 方案废弃, 改用生成不同的IResponseHandle来处理
     */
    @Deprecated
    private static void initGuardThread(Integer guardPort) {
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(guardPort);
                System.out.println("启动成功");
                while (RUN_ABLE) {
                    Socket socket = server.accept();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
