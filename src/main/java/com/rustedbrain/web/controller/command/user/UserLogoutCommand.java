package com.rustedbrain.web.controller.command.user;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class UserLogoutCommand implements ActionCommand {

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
