package com.rustedbrain.web.controller.command.login;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class ProfileShowCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {

            page = ConfigurationManager.getInstance().getProperty("path.page.profile");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }

        return page;
    }
}
