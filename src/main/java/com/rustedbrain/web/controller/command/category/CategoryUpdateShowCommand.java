package com.rustedbrain.web.controller.command.category;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class CategoryUpdateShowCommand implements ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();
    private UserCredentialsLogic userCredentialsLogic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer categoryId = CommandUtil.Category.getId(requestContent);
            requestContent.getRequestAttributes().put("category", logic.getCategory(categoryId));
            requestContent.getSessionAttributes().put("admins", userCredentialsLogic.getAdminProxyUsers());
            page = ConfigurationManager.getInstance().getProperty("path.page.category.update");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getSessionAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }

        return page;
    }
}
