package com.rustedbrain.web.controller.command.asynchronous;

import com.rustedbrain.web.controller.command.factory.ActionCommand;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class AsynchronousActionCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent requestContent) {
        return null;
    }
}
