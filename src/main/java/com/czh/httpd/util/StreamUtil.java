package com.czh.httpd.util;

import java.io.*;

/**
 * @author chenzh
 */
public class StreamUtil {
    public static String getContent(InputStream inputStream, String charsetName) throws IOException {
        if (charsetName == null || "".equals(charsetName)) {
            charsetName = "UTF-8";
        }
        String line;
        StringBuilder sb = new StringBuilder();
        sb.append("/r/n");
        BufferedReader read = new BufferedReader(new InputStreamReader(inputStream,charsetName));
        while ((line = read.readLine()) != null) {
            // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(line).append("/r/n");
            // TODO 查一下为什么空行不停止会返回""
            if (StringUtils.isBlank(line)) {
                break;
            }
        }
        return sb.toString();
    }

    public static String getHttpRequestHeader(InputStream inputStream, String charsetName) throws IOException {
        if (charsetName == null || "".equals(charsetName)) {
            charsetName = "UTF-8";
        }
        String line;
        StringBuilder sb = new StringBuilder();
        sb.append("/r/n");
        BufferedReader read = new BufferedReader(new InputStreamReader(inputStream,charsetName));
        String lastLine = "";
        while ((line = read.readLine()) != null) {
            // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(line).append("/r/n");
            // TODO 查一下为什么空行不停止会返回""
            if (StringUtils.isBlank(line)) {
                break;
            }
            lastLine = line;
        }
        return sb.toString();
    }
}
