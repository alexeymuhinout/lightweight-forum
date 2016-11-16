package com.rustedbrain.web.controller.resource;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessageManager extends Manager {

    private static final String MESSAGE_PROPERTIES_PATH = "C:\\Work\\IdeaProjects\\lightweight-forum\\resources\\properties\\messages.properties";
    private static MessageManager ourInstance = new MessageManager();

    private MessageManager() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(MESSAGE_PROPERTIES_PATH), "windows-1251"));
            super.properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MessageManager getInstance() {
        return ourInstance;
    }
}
