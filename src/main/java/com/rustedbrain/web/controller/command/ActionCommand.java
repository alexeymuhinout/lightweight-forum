package com.rustedbrain.web.controller.command;

import com.rustedbrain.web.model.servlet.SessionRequestContent;

public interface ActionCommand {
    String execute(SessionRequestContent requestContent);
}
