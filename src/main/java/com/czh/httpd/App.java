package com.czh.httpd;

import com.alibaba.fastjson.JSON;
import com.czh.httpd.constant.CommonConstants;
import com.czh.httpd.entity.DefaultConfig;
import com.czh.httpd.entity.Server;
import com.czh.httpd.exception.ConfigException;
import com.czh.httpd.header.SimpleRequestHeader;
import com.czh.httpd.strategy.CommandStrategy;
import com.czh.httpd.thread.HttpThread;
import com.czh.httpd.util.FileUtil;
import com.czh.httpd.util.StreamUtil;
import com.czh.httpd.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.czh.httpd.enums.ExceptionEnum.*;

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
     * 监听端口的线程
     */
    private static List<Thread> serverThreadList;

    /**
     * 守护线程
     */
    private static Thread guardThread;

    /**
     * 监听http端口的socket
     */
    private static List<ServerSocket> serverSocketList;

    /**
     * 监听操作端口的socket
     */
    private static ServerSocket guardSocket;

    /**
     * 是否可以运行
     */
    public static boolean RUN_ABLE = true;

    /**
     * http线程池
     */
    public static ExecutorService executor;

    // TODO 修改 log4j2的配置
    private static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append(" ");
        }
        String command = sb.toString().trim();
        System.out.println("command: " + command);
        if (StringUtils.isNotBlank(command)) {
            CommandStrategy.run(command);
        }
        if (StringUtils.isBlank(command)) {
            start();
        }
        logger.info("test log4j2......");
        /*start();
        displayThread();
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop();
        displayThread();*/
    }

    public static void start() {
        RUN_ABLE = true;
        loadConfig();
        // 获取配置的操作端口
        initGuardThread(9999);
        initHttpThread();
    }

    public static void stop() {
        System.out.println("开始停止");
        RUN_ABLE = false;
        // 终止 serverThreadList 和 guardThread 中的 socket 的监听
        try {
            if (guardSocket != null) {
                guardSocket.close();
            }
            for (ServerSocket serverSocket : serverSocketList) {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 等待线程接受
        try {
            for (Thread thread : serverThreadList) {
                if (thread != null) {
                    System.out.println("server thread join");
                    thread.join();
                }
            }
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
            if (guardThread != null) {
                System.out.println("guard thread join");
                guardThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("完全停止所有线程");
    }

    public static void restart() {
        stop();
        start();
    }

    public static void displayThread() {
        serverThreadList.forEach(thread -> {
            System.out.println("server thread == null " + (thread == null));
            if (thread != null) {
                System.out.println(thread.getState().toString());
            }
        });
        System.out.println("guardThread == null " + (guardThread == null));
        if (guardThread != null) {
            System.out.println(guardThread.getState().toString());
        }
    }

    /**
     * 加载配置文件
     * TODO 重构获取配置文件方式
     */
    public static void loadConfig() throws ConfigException {
        // 读取默认配置文件 如果用在系统下没找到, 就到classPath下查找
        final String defaultJsonConfigPath = CommonConstants.SystemConfig.DEFAULT_JSON_CONFIG_PATH;
        File defaultConfigFile = new File(defaultJsonConfigPath);
        if (!defaultConfigFile.exists()) {
            defaultConfigFile = new File(App.class.getResource("/").getPath() + defaultJsonConfigPath);
        }
        if (!defaultConfigFile.exists()) {
            throw new ConfigException(DEFAULT_CONFIG_FILE_NOT_FOUND.format(defaultConfigFile.getPath()));
        }
        // 读取server文件路径, 获取到所有server配置文件(目前只考虑获取第一个配置文件)
        final String defaultConfigContent = FileUtil.readTextFile(defaultConfigFile);
        if (StringUtils.isBlank(defaultConfigContent)) {
            throw new ConfigException(DEFAULT_CONFIG_CONTENT_IS_BLANK);
        }
        defaultConfig = JSON.parseObject(defaultConfigContent, DefaultConfig.class);
        final String serverPath = defaultConfig.getServerPath();
        if (StringUtils.isBlank(serverPath)) {
            throw new ConfigException(DEFAULT_CONFIG_SERVER_PATH_IS_BLANK);
        }
        // 读取配置文件
        File serverDir = new File(serverPath);
        if (!serverDir.exists()) {
            serverDir = new File(App.class.getResource("/").getPath() + serverPath);
        }
        if (!serverDir.exists() || !serverDir.isDirectory()) {
            throw new ConfigException(DEFAULT_CONFIG_SERVER_PATH_NOT_EXISTED_OR_NOT_DIR.format(serverPath));
        }
        String[] serverNames = serverDir.list();
        if (serverNames == null || serverNames.length == 0) {
            throw new ConfigException(DEFAULT_CONFIG_SERVER_PATH_CONTENT_IS_BLANK.format(serverPath));
        }
        serverList = new ArrayList<>(serverNames.length);
        serverMap = new HashMap<>(serverNames.length);
        for (String serverName : serverNames) {
            // TODO 暂时粗暴处理, 后续完善读取
            if (!serverName.contains(".json")) {
                continue;
            }
            File serverFile = new File(serverDir.getPath(), serverName);
            String serverContent = FileUtil.readTextFile(serverFile);
            Server server = JSON.parseObject(serverContent, Server.class);
            serverList.add(server);
            serverMap.put(server.getListen(), server);
        }
    }

    public static void initHttpThread() {
        if (serverList.size() == 0) {
            throw new ConfigException(SERVER_IS_NULL);
        }
        serverSocketList = new ArrayList<>(serverList.size());
        serverThreadList = new ArrayList<>(serverList.size());
        // 一个server创建一个循环线程, 并保存到一个map中, 循环线程统一向一个线程池提交线程
        executor = Executors.newFixedThreadPool(defaultConfig.getMaxTheadNum());
        serverList.forEach(server -> {
            // TODO server自检
            assert server.getListen() != null : "监听端口不能为空";

            Thread httpThread = new Thread(() -> {
                ServerSocket serverSocket;
                try {
                    serverSocket = new ServerSocket(server.getListen());
                    serverSocketList.add(serverSocket);
                    while (RUN_ABLE) {
                        Socket socket;
                        try {
                            socket = serverSocket.accept();
                        } catch (SocketException e) {
                            e.printStackTrace();
                            return;
                        }
                        executor.submit(new HttpThread(socket, server));
                    }
                } catch (IOException e) {
                    // TODO 处理端口被占用
                    e.printStackTrace();
                }
            });
            httpThread.start();
            serverThreadList.add(httpThread);
            System.out.println("启动成功");
            System.out.println("server: " + JSON.toJSONString(server));
        });
    }

    /**
     * 初始化一个守护线程, 用来管理运行中的程序
     */
    private static void initGuardThread(Integer guardPort) {
        guardThread = new Thread(() -> {
            try {
                guardSocket = new ServerSocket(guardPort);
                System.out.println("启动成功");
                while (RUN_ABLE) {
                    Socket socket;
                    try {
                        socket = guardSocket.accept();
                    } catch (SocketException e) {
                        e.printStackTrace();
                        return;
                    }
                    InputStream inputStream = socket.getInputStream();
                    String reqData = StreamUtil.getContent(inputStream, "UTF-8");
//                    System.out.println("reqData:");
//                    System.out.println(reqData);
                    String realReqData = StringUtils.removePostfix(StringUtils.removePrefix(reqData, "/r/n"), "/r/n");
                    System.out.println("realReqData");
                    System.out.println(realReqData);
                    // TODO 验证socket的内容, 执行stop, restart等操作
                    new Thread(() -> {
                        if ("stop".equals(realReqData)) {
                            stop();
                        } else if ("restart".equals(realReqData)) {
                            restart();
                        }
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        guardThread.start();
    }
}
