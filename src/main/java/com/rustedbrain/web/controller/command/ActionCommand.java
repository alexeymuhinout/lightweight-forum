package com.rustedbrain.web.controller.command;

import com.rustedbrain.web.model.SessionRequestContent;

public interface ActionCommand {
    String execute(SessionRequestContent requestContent);
}
