package com.rustedbrain.web.controller.command;

import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class IndexShowCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page = null;

        try {
            page = ConfigurationManager.getInstance().getProperty("path.page.index");
        } catch (Exception e) {
            requestContent.getRequestAttributes().put("message", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.categories.show");
        }

        return page;
    }
}
