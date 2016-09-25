package com.rustedbrain.web.controller.resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigurationManager extends Manager {

    private static final String CONFIGURATION_PROPERTIES_PATH = "";
    private static ConfigurationManager ourInstance = new ConfigurationManager();

    private ConfigurationManager() {
        try {
            InputStream input = new FileInputStream(CONFIGURATION_PROPERTIES_PATH);
            super.properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationManager getInstance() {
        return ourInstance;
    }
}
