package com.rustedbrain.web.controller.command.synchronous.user;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class UserLogoutCommand implements SynchronousActionCommand {

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;
        try {
            requestContent.setInvalidatedSession(true);
            page = ConfigurationManager.getInstance().getProperty("path.page.index");
        } catch (Exception e) {
            e.printStackTrace();
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }

        return page;
    }
}
