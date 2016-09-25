package com.rustedbrain.web.controller.resource;

import java.util.Properties;

public abstract class Manager {

    final Properties properties = new Properties();

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
