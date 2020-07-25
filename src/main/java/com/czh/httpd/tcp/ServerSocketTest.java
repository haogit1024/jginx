package com.czh.httpd.tcp;

import com.czh.httpd.util.StreamUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1.创建ServerSocket对象，绑定监听端口。
 * 2.通过accept（）方法监听客户端请求。
 * 3.连接建立后，通过输入流读取客户端发送的请求信息。
 * 4.通过输出流向客户端发送响应信息。
 * 5.关闭响应的资源。
 */
public class ServerSocketTest {
    private static final String OUTPUT = "<html><head><title>Example</title></head><body><p>Worked!!!</p></body></html>";
    private static final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length: ";
    private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";

    public void start() {
    	// TODO 关闭serverSocket
        try {
            ServerSocket serverSocket = new ServerSocket(9002);
            boolean isStop = false;
            int i = 0;
            while (!isStop) {
                System.out.println(i);
                i++;
                Socket socket = serverSocket.accept();
                InputStream in = socket.getInputStream();
                String inputContent = StreamUtil.getContent(in, "");
                System.out.println("inputContent: " + inputContent);
                sendResponse(socket);
                socket.close();
                // TODO 查一下下一个socket不启动http就接受不到响应
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write(OUTPUT_HEADERS + OUTPUT.length() + OUTPUT_END_OF_HEADERS + OUTPUT);
        bw.flush();
        bw.close();
    }

    public static void main(String[] args){
        new ServerSocketTest().start();
    }

}
