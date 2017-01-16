package com.rustedbrain.web.controller.command.synchronous.category;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class CategoryUpdateCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer categoryId = CommandUtil.Category.getId(requestContent);
            String newName = CommandUtil.Category.getName(requestContent).trim();
            Integer newAdminId = CommandUtil.User.getId(requestContent);
            logic.updateCategory(categoryId, newName, newAdminId);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("category.update.success"));
            page = new CategoriesShowCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new CategoryUpdateShowCommand().execute(requestContent);
        }
        return page;
    }
}
