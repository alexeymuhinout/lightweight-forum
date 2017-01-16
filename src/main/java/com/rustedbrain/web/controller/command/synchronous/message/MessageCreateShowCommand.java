package com.rustedbrain.web.controller.command.synchronous.message;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class MessageCreateShowCommand implements SynchronousActionCommand {

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            page = ConfigurationManager.getInstance().getProperty("path.page.message.create");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getSessionAttributes().put("error", e.getMessage());
            page = new MessagesShowCommand().execute(requestContent);
        }
        return page;
    }
}
