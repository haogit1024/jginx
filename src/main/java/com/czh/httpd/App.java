package com.czh.httpd;

import com.czh.httpd.properties.SettingProperties;
import com.czh.httpd.thread.HttpThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author chenzh
 */
public class App {
    /**
     * http换行符
     */
    public static final String CRLF="\r\n";

    /**
     * 空格
     */
    public static final String SPACE =" ";

    /**
     * http头分隔符
     */
    public static final String HEADER_SEPARATOR = ": ";

    /**
     * 工作目录
     */
    public static final String WORK_SPACE = "D:\\www";

    /**
     * 是否可以运行
     */
    public static boolean RUN_ABLE = true;

    public static final String INDEX_PAGE = "/static/index.html";
    public static void main(String[] args) {
        SettingProperties settingProperties = SettingProperties.instance();
        initHttpThread(settingProperties.getGuardPort());
    }

    private static void initHttpThread(Integer serverPort) {
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(serverPort);
                System.out.println("启动成功");
                while (RUN_ABLE) {
                    Socket socket = server.accept();
                    new HttpThread(socket).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
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
