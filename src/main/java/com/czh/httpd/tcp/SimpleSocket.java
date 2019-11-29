package com.czh.httpd.tcp;

import com.czh.httpd.util.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleSocket {
    public static void main(String[] args) throws UnknownHostException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("time-A.timefreq.bldrdoc.gov", 13);
        System.out.println(inetSocketAddress.getAddress());
        System.out.println(inetSocketAddress.getHostName());
        System.out.println(inetSocketAddress.getPort());
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address);
        try(Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("time-A.timefreq.bldrdoc.gov", 13), 10000);
            InputStream inputStream = socket.getInputStream();
            String res = StreamUtil.getContent(inputStream, "");
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
