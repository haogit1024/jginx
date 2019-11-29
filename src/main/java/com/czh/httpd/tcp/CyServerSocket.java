package com.czh.httpd.tcp;

import com.czh.httpd.util.StreamUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author czh
 */
public class CyServerSocket {
    public static void main(String[] args) throws IOException, InterruptedException {
//        while (true) {
//            try (ServerSocket serverSocket = new ServerSocket(9002)) {
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        ServerSocket serverSocket = new ServerSocket(9002);
        // 接收到了请求
        Socket socket = serverSocket.accept();
//        while (true) {
        System.out.println("1");
        InputStream in = socket.getInputStream();
        System.out.println("2");
//        String inputContent = StreamUtil.getContent(in);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        String inputContent = new String(bytes);
        System.out.println("inputContent: " + inputContent);
        socket.shutdownInput();
//        sendResponse(socket);
//        socket.shutdownOutput();
//        }
        /*InputStream in = socket.getInputStream();
        String inputContent = StreamUtil.getContent(in, "");
        System.out.println("inputContent: " + inputContent);
        sendResponse(socket);
        Thread.sleep(2000);
        sendResponse(socket);*/

    }

    public static void sendResponse(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write("aaaaa");
        bw.flush();
        bw.close();
    }
}
