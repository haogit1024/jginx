package com.czh.httpd;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author chenzh
 * @date 2020/11/27
 */
public class FtpClient {
    /**
     * 开启一个服务端socket等待连接
     * 开一个请求服务器的socket
     * @param args
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Thread t1 = new Thread(() -> {
            ServerSocket server = null;
            try {
                server = new ServerSocket(7001);
                System.out.println("等待服务端连接");
                Socket socket = server.accept();
                System.out.println("客户端socket被主动连接拉。。。。。。。。。。");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 7002);
                OutputStream outputStream = socket.getOutputStream();
                String content = "restart";
                outputStream.write(content.getBytes());
                outputStream.flush();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t2.join();
        t1.join();
    }
}
