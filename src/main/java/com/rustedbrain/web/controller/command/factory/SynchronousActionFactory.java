package com.rustedbrain.web.controller.command.factory;

import com.rustedbrain.web.controller.command.synchronous.EmptyCommand;
import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class SynchronousActionFactory implements ActionFactory {

    private static SynchronousActionFactory ourInstance = new SynchronousActionFactory();

    private SynchronousActionFactory() {
    }

    public static SynchronousActionFactory getInstance() {
        return ourInstance;
    }

    @Override
    public ActionCommand defineCommand(SessionRequestContent requestContent) {
        SynchronousActionCommand command = new EmptyCommand();

        String action = requestContent.getRequestParameters().get("command")[0];

        if (action == null || action.isEmpty()) {
            return command;
        } else {
            try {
                CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
                command = commandEnum.getCurrentCommand();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return command;
    }


}
