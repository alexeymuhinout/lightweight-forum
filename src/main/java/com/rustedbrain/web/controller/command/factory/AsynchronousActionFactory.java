package com.rustedbrain.web.controller.command.factory;

import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class AsynchronousActionFactory implements ActionFactory {

    private static AsynchronousActionFactory ourInstance = new AsynchronousActionFactory();

    private AsynchronousActionFactory() {
    }

    public static AsynchronousActionFactory getInstance() {
        return ourInstance;
    }

    @Override
    public ActionCommand defineCommand(SessionRequestContent requestContent) {
        return null;
    }
}
