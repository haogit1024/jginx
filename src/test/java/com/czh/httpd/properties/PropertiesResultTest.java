package com.czh.httpd.properties;

import com.czh.httpd.entity.TestProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesResultTest {

    @Test
    void getEntity() {
        PropertiesResult propertiesResult = new PropertiesResult("test.properties");
        TestProperties testProperties = propertiesResult.getEntity(TestProperties.class);
        System.out.println(testProperties);
    }
}