package com.rustedbrain.web.controller.command.synchronous.category;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class CategoriesShowCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            requestContent.getRequestAttributes().put("categories", logic.getModeratedCategories());
            page = ConfigurationManager.getInstance().getProperty("path.page.categories");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }

        return page;
    }
}
