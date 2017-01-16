package com.rustedbrain.web.controller.command.synchronous;

import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class IndexShowCommand implements SynchronousActionCommand {

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            page = ConfigurationManager.getInstance().getProperty("path.page.index");
        } catch (Exception e) {
            requestContent.getRequestAttributes().put("message", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.categories.show");
        }
        return page;
    }
}
