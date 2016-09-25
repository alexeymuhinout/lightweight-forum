package com.rustedbrain.web.controller.command.factory;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.EmptyCommand;
import com.rustedbrain.web.model.SessionRequestContent;

public class ActionFactory {

    public ActionCommand defineCommand(SessionRequestContent requestContent) {
        ActionCommand command = new EmptyCommand();

        String action = requestContent.getRequestParameters().get("command")[0];

        if (action == null || action.isEmpty())
            return command;

        try {
            CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());

            command = commandEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {

        }

        return command;
    }


}
