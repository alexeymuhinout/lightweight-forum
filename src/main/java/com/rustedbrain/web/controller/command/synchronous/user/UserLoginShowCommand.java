package com.rustedbrain.web.controller.command.synchronous.user;


import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class UserLoginShowCommand implements SynchronousActionCommand {
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
