package com.rustedbrain.web.controller.command.synchronous.category;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;


public class CategoryCreateCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer creatorId = CommandUtil.User.getUser(requestContent).getId();
            String categoryName = CommandUtil.Category.getName(requestContent).trim();
            logic.createCategory(creatorId, categoryName);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("category.creation.success"));
            page = new CategoriesShowCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new CategoryCreateShowCommand().execute(requestContent);
        }
        return page;
    }
}
