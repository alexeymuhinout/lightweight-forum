package com.rustedbrain.web.controller.command.user;


import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class UserLoginShowCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            page = ConfigurationManager.getInstance().getProperty("path.page.user.login");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getSessionAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }
        return page;
    }
}
