package com.czh.httpd;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author chenzh
 * @date 2020/11/27
 */
public class FtpServer {
    /**
     * 接受到socket请求后连接到客户端的7001端口
     * @param args
     */
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8999);
        System.out.println("等待客户端连接");
        Socket socket = server.accept();
        System.out.println("服务端开始主动连接客户端");
        InetAddress inetAddress = socket.getInetAddress();
        Socket reqClientSocket = new Socket(inetAddress, 7001);
        OutputStream outputStream = reqClientSocket.getOutputStream();
        outputStream.write("hello i server socket".getBytes());
        outputStream.flush();
        outputStream.close();
        socket.close();
    }
}
