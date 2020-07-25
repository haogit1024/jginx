package com.czh.httpd.properties;

import com.czh.httpd.util.PropertiesLoader;

/**
 * @author czh
 * @date 2020/7/24
 */
public class SettingProperties {
    private Integer serverPort;
    private Integer guardPort;

    private SettingProperties() {
        PropertiesLoader propertiesLoader = PropertiesLoader.instance("setting.properties");

        try {
            serverPort = Integer.parseInt(propertiesLoader.getProperty("server.port"));
            guardPort = Integer.parseInt(propertiesLoader.getProperty("guard.port"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("映射配置文件出错: " + e.getMessage());
            System.exit(0);
        }
    }

    public Integer getGuardPort() {
        return guardPort;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    private static SettingProperties settingProperties;

    public static SettingProperties instance() {
        if (settingProperties == null) {
            synchronized (SettingProperties.class) {
                if (settingProperties == null) {
                    settingProperties = new SettingProperties();
                }
            }
        }
        return settingProperties;
    }
}
