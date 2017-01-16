package com.rustedbrain.web.controller.command.synchronous.category;


import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class CategoryDeleteCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        try {
            Integer categoryId = CommandUtil.Category.getId(requestContent);
            logic.deleteCategory(categoryId);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("category.delete.success"));
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
        }
        return new CategoriesShowCommand().execute(requestContent);
    }
}
