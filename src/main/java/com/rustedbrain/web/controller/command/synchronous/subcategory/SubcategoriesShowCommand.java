package com.rustedbrain.web.controller.command.synchronous.subcategory;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;
import com.rustedbrain.web.model.servlet.UserSubcategory;

import java.util.List;

public class SubcategoriesShowCommand implements SynchronousActionCommand {

    private ForumLogic forumLogic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer categoryId = CommandUtil.Category.getId(requestContent);
            List<UserSubcategory> subcategories = forumLogic.getUserSubcategories(categoryId);
            requestContent.getRequestAttributes().put("subcategories", subcategories);
            page = ConfigurationManager.getInstance().getProperty("path.page.subcategories");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("message", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }
        return page;
    }
}
