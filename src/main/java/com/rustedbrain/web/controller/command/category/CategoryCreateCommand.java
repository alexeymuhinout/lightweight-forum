package com.rustedbrain.web.controller.command.category;

import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.ModeratedCategory;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.sql.SQLException;
import java.util.List;


public class CategoryCreateCommand implements com.rustedbrain.web.controller.command.ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer creatorId = CommandUtil.User.getUser(requestContent).getId();
            String categoryName = CommandUtil.Category.getName(requestContent);
            logic.createCategory(creatorId, categoryName);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("category.creation.success"));
            List<ModeratedCategory> moderatedCategories = logic.getModeratedCategories();
            requestContent.getRequestAttributes().put("categories", moderatedCategories);
            page = ConfigurationManager.getInstance().getProperty("path.page.categories");
        } catch (IllegalArgumentException | SQLException e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.category.create");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }
        return page;
    }
}
