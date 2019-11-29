package com.czh.httpd.thread;

import com.czh.httpd.response.IndexResponse;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author chenzh
 * 处理http请求的线程, 每一个http请求生成一条线程
 */
public class HttpThread extends Thread {
    private Socket socket;

    public HttpThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        IndexResponse indexResponse = new IndexResponse();
        try {
            OutputStream ost = socket.getOutputStream();
            String response = indexResponse.getResponse();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ost));
            bw.write(response);
            bw.flush();
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("socket已关闭");
        }
    }
}
