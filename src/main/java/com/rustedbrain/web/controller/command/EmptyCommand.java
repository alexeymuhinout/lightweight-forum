package com.rustedbrain.web.controller.command;

import com.rustedbrain.web.model.SessionRequestContent;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page = null;

        return page;
    }
}
