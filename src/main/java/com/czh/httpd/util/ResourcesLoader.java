package com.czh.httpd.util;

import com.czh.httpd.constant.CommonConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author chenzh
 * 资源加载器
 */
public class ResourcesLoader {
    /**
     * 根据 http 中的 url 读取资源
     * @param url   http 请求头中的url, demo: /hello.html
     * @return  file, 如果找不到文件返回 null
     */
    public static File getResourceFromUrlAsFile(String url) throws IOException {
        final String filePath = CommonConstants.Symbol.RESOURCE_DIR + url;
        URL resourceUrl = ResourcesLoader.class.getResource(filePath);
        if (resourceUrl == null) {
            return null;
        }
        return new File(resourceUrl.getFile());
    }

    public static String getResourceAsString(String filePath) throws IOException {
        InputStream inputStream = ResourcesLoader.class.getResourceAsStream(filePath);
        if (inputStream == null) {
            return null;
        }
//        StringBuilder sb = new StringBuilder();
//        // 一次读取一MB
//        byte[] bytes = new byte[1024 * 1024];
//        int len;
//        while ((len = inputStream.read()) != -1) {
//            sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
//        }
//        return sb.toString();
        byte[] bytes = new byte[inputStream.available()];
        int len = inputStream.read(bytes);
        return new String(bytes, 0, len);
    }

    public static byte[] getResourceAsBytes(String filePath) {
        try {
            InputStream inputStream = ResourcesLoader.class.getResourceAsStream(filePath);
            if (inputStream == null) {
                return null;
            }
            byte[] bytes = new byte[inputStream.available()];
            int len = inputStream.read(bytes);
            return ArrayUtil.splitBytes(bytes, 0, len);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取resource出错");
        }
        return new byte[0];
    }

    /**
     *  **调用该方法前一定要判断该文件已存在**
     * @param file
     * @return
     */
    public static String getContent(File file) {
    	FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            int len = fis.read(bytes);
            return new String(bytes, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if (fis != null) {
        		try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return "";
    }

    public static byte[] getBytes(File file) {
    	FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            int len = fis.read(bytes);
            return ArrayUtil.splitBytes(bytes, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取文件出错");
        } finally {
        	if (fis != null) {
        		try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return new byte[0];
    }

    public static byte[] getBytes(File file, int start, int end) {
    	FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            // 指针
            int index= 0;
            // 指针向前移动, 一次移动1Mb, 一直移动到start
            while (index < start) {
                int temp = index;
                int oneMb = 1024 * 1024;
                index += oneMb;
                if (index > start) {
                    index = start;
                }
                byte[] bytes = new byte[index - temp];
                if ((fis.read(bytes)) == -1) {
                    return new byte[0];
                }
            }
            // 已经移动到需要的位置开始读取数据
            int len = end - start + 1;
            byte[] bytes = new byte[len];
            // 上层已经对len做了校验和处理, 理论上不需要再处理, 但是为了安全还是处理下
            int realLen = fis.read(bytes);
            if (realLen == -1) {
                return new byte[0];
            }
            return bytes;
//            return ArrayUtil.splitBytes(bytes, 0, realLen);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取文件出错, " + file.getName());
        } finally {
        	if (fis != null) {
        		try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return new byte[0];
    }

    /**
     *  **调用该方法前一定要判断该文件已存在**
     * @param file
     * @param off   偏移位
     * @param len   读取长度
     * @return
     */
    public static String getContent(File file, int off, int len) {
    	FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = new byte[len];
            len = fis.read(bytes, off, len);
            return new String(bytes, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if (fis != null) {
        		try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return "";
    }

}
