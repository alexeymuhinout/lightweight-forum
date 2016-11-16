package com.rustedbrain.web.controller.command.category;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.ModeratedCategory;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.util.List;

public class CategoriesShowCommand implements ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            List<ModeratedCategory> categories = logic.getModeratedCategories();
            requestContent.getRequestAttributes().put("categories", categories);
            page = ConfigurationManager.getInstance().getProperty("path.page.categories");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("message", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }

        return page;
    }
}
