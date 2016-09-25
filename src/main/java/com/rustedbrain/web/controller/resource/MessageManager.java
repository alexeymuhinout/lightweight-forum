package com.rustedbrain.web.controller.resource;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MessageManager extends Manager {

    private static final String MESSAGE_PROPERTIES_PATH = "";
    private static MessageManager ourInstance = new MessageManager();

    private MessageManager() {
        try {
            InputStream input = new FileInputStream(MESSAGE_PROPERTIES_PATH);
            super.properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MessageManager getInstance() {
        return ourInstance;
    }
}
