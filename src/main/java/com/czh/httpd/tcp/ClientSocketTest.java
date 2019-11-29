package com.czh.httpd.tcp;

import com.czh.httpd.util.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocketTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        String host = "127.0.0.1";
//        String host = "120.77.14.136";
//        int port = 9002;
        int port = 9999;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        String message="你好hello";
        byte[] data = {0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03};
        socket.getOutputStream().write(data);
        Thread.sleep(2000);
        socket.getOutputStream().write(data);
        System.out.println("发送完成");
        socket.shutdownOutput();
//        Thread.sleep(5000);
//        socket.getOutputStream().write(message.getBytes("UTF-8"));
        InputStream in = socket.getInputStream();
//        String inputContent = StreamUtil.getContent(in);
//        socket.shutdownInput();
//        System.out.println("inputContent: " + inputContent);
        outputStream.close();
        socket.close();
    }
}
