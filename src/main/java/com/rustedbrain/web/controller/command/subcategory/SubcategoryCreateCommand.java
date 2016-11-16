package com.rustedbrain.web.controller.command.subcategory;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;
import com.rustedbrain.web.model.servlet.UserSubcategory;

import java.util.List;


public class SubcategoryCreateCommand implements ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer creatorId = CommandUtil.User.getUser(requestContent).getId();
            Integer categoryId = CommandUtil.Category.getId(requestContent);
            String subcategoryName = CommandUtil.Subcategory.getName(requestContent);

            logic.createSubcategory(creatorId, categoryId, subcategoryName);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("subcategory.creation.success"));

            requestContent.getRequestAttributes().put("category_id", categoryId);
            List<UserSubcategory> subcategories = logic.getUserSubcategories(categoryId);
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
