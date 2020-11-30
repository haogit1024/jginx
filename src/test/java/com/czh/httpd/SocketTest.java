package com.czh.httpd;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {
    @Test
    void clientServerSocket() throws IOException {
        ServerSocket server = new ServerSocket(7001);
        System.out.println("等待服务端连接");
        Socket socket = server.accept();
        System.out.println("客户端socket被主动连接拉。。。。。。。。。。");
    }

    @Test
    void clientSocket() throws IOException {
        Socket socket = new Socket("47.102.137.55", 8080);
        OutputStream outputStream = socket.getOutputStream();
        String content = "restart";
        outputStream.write(content.getBytes());
        outputStream.flush();
        outputStream.close();
        socket.close();
    }

    @Test
    void serverServerSocket() throws IOException {
        ServerSocket server = new ServerSocket(7002);
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
