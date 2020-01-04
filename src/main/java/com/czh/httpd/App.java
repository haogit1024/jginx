package com.czh.httpd;

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
    	// TODO 监听一个端口执行关闭操作
        try {
            ServerSocket server = new ServerSocket(8098);
            System.out.println("启动成功");
            while (RUN_ABLE) {
                Socket socket = server.accept();
                new HttpThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
