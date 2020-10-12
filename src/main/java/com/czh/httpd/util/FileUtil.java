package com.czh.httpd.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 文件工具类
 * @author chenzh
 * @date 2020/10/10
 */
public class FileUtil {
    /**
     * 每次读取大小 1K
     */
    private static final Integer READER_SIZE = 1024;

    public static String readTextFile(String filePath) {
        return readTextFile(new File(filePath), StandardCharsets.UTF_8);
    }

    public static String readTextFile(String filePath, Charset charset) {
        return readTextFile(new File(filePath), charset);
    }

    public static String readTextFile(File file) {
        return readTextFile(file, StandardCharsets.UTF_8);
    }

    public static String readTextFile(File file, Charset charset) {
        if (file == null || !file.exists()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, charset);
             BufferedReader bufferedReader = new BufferedReader(isr)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void copyFile(String sourceFilePath, String targetFilePath) {
        copyFile(new File(sourceFilePath), new File(targetFilePath));
    }

    /**
     * 复制文件夹, 虽然可以和上面的方法合并成一个方法, 但是我不行！
     * @param sourceFile
     * @param targetFile
     */
    public static void copyFile(File sourceFile, File targetFile) {
        if (sourceFile == null || targetFile == null)  {
            return;
        }
        if (sourceFile.isDirectory()) {
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            // 获取子目录
            String[] children = sourceFile.list();
            if (children == null) {
                return;
            }
            for (String child : children) {
                copyFile(new File(sourceFile, child), new File(targetFile, child));
            }
        } else {
            // 复制文件
            try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(sourceFile));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(targetFile))) {
                // 每次读取大小
                byte[] bytes = new byte[READER_SIZE];
                int len;
                while ((len = bufferedInputStream.read(bytes)) != -1) {
                    bufferedOutputStream.write(bytes, 0, len);
                }
                bufferedOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
