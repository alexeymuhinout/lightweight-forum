package com.rustedbrain.web.controller.resource;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigurationManager extends Manager {

    private static final String CONFIGURATION_PROPERTIES_PATH = "C:\\Work\\IdeaProjects\\lightweight-forum\\resources\\properties\\configuration.properties";
    private static ConfigurationManager ourInstance = new ConfigurationManager();

    private ConfigurationManager() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(CONFIGURATION_PROPERTIES_PATH), "windows-1251"));
            super.properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationManager getInstance() {
        return ourInstance;
    }
}
