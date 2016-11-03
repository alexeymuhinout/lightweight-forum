package com.rustedbrain.web.controller.resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SQLManager extends Manager {

    private static final String MESSAGE_PROPERTIES_PATH = "C:\\Work\\IdeaProjects\\lightweight-forum\\resources\\properties\\sql.properties";
    private static SQLManager ourInstance = new SQLManager();

    private SQLManager() {
        try {
            InputStream input = new FileInputStream(MESSAGE_PROPERTIES_PATH);
            super.properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SQLManager getInstance() {
        return ourInstance;
    }
}
