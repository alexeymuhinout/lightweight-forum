package com.rustedbrain.web.controller.command.category;


import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class CategoryDeleteCommand implements ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer categoryId = CommandUtil.Category.getId(requestContent);
            logic.deleteCategory(categoryId);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("category.delete.success"));

            page = new CategoriesShowCommand().execute(requestContent);
        } catch (Exception e) {
            requestContent.getRequestAttributes().put("message", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.categories.show");
        }
        return page;
    }
}
