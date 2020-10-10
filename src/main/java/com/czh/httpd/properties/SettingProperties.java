package com.czh.httpd.properties;

import com.czh.httpd.util.PropertiesLoader;

/**
 * @author czh
 * @date 2020/7/31
 */
@Deprecated
public class SettingProperties {
    public static final Integer SERVER_PORT;

    static {
        System.out.println("开始静态读取配置文件");
        Integer port;
        try {
            PropertiesLoader propertiesLoader = PropertiesLoader.instance("setting.properties");
            port = Integer.parseInt(propertiesLoader.getProperty("server.port"));;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("读取配置文件出错");
        }
        SERVER_PORT = port;
    }
}
