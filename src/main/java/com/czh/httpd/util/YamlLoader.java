package com.czh.httpd.util;

import com.czh.httpd.entity.Server;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author chenzh
 * @date 2020/10/9
 */
public class YamlLoader {
    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        InputStream inputStream = YamlLoader.class.getResourceAsStream("/server/server.yml");
        Server server = yaml.loadAs(inputStream, Server.class);
        System.out.println(server);
    }
}
