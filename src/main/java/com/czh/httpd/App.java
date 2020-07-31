package com.czh.httpd;

import com.czh.httpd.exception.BaseException;
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
        try {
            initHttpThread(SettingProperties.SERVER_PORT);
        } catch (BaseException e) {
            e.printStackTrace();
            // TODO 自定义异常处理
        }
    }

    private static void initHttpThread(Integer serverPort) {
        // TODO 修改为线程池
        new Thread(() -> {
        	ServerSocket server = null;
            try {
                server = new ServerSocket(serverPort);
                System.out.println("启动成功");
                while (RUN_ABLE) {
                    Socket socket = server.accept();
                    new HttpThread(socket).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BaseException e) {
                // TODO 自定http义异常处理
                e.printStackTrace();
            } finally {
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
