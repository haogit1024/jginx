package com.czh.httpd;

import com.czh.httpd.exception.BaseException;
import com.czh.httpd.properties.SettingProperties;
import com.czh.httpd.thread.HttpThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
    public static final String WORK_SPACE = "C:\\www";

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
            initHttpThread(SettingProperties.SERVER_PORT);
        } catch (BaseException e) {
            e.printStackTrace();
            // TODO 自定义异常处理
        }
    }

    private static void initHttpThread(Integer serverPort) {
        new Thread(() -> {
        	ServerSocket server = null;
            ExecutorService executor = Executors.newFixedThreadPool(100);
            try {
                server = new ServerSocket(serverPort);
                System.out.println("启动成功");
                while (RUN_ABLE) {
                    Socket socket = server.accept();
                    try {
//                        new HttpThread(socket).start();
                        executor.submit(new HttpThread(socket));
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
