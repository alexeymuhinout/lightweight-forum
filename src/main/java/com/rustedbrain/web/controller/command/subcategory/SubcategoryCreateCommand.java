package com.rustedbrain.web.controller.command.subcategory;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;


public class SubcategoryCreateCommand implements ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer creatorId = CommandUtil.User.getUser(requestContent).getId();
            Integer categoryId = CommandUtil.Category.getId(requestContent);
            String subcategoryName = CommandUtil.Subcategory.getName(requestContent).trim();

            logic.createSubcategory(creatorId, categoryId, subcategoryName);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("subcategory.creation.success"));
            page = new SubcategoriesShowCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new SubcategoryCreateShowCommand().execute(requestContent);
        }
        return page;
    }
}
