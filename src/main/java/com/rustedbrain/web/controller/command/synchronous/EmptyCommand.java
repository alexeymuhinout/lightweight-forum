package com.rustedbrain.web.controller.command.synchronous;

import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class EmptyCommand implements SynchronousActionCommand {

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        String errorMessage = MessageManager.getInstance().getProperty("request.command.not.found");
        requestContent.getRequestAttributes().put("error", errorMessage);
        page = ConfigurationManager.getInstance().getProperty("path.page.error");

        return page;
    }
}
