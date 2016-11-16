package com.rustedbrain.web.controller.command.category;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.jdbc.Category;
import com.rustedbrain.web.model.servlet.ProxyUser;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.util.List;

public class CategoryUpdateShowCommand implements ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();
    private UserCredentialsLogic userCredentialsLogic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page = null;

        try {
            Integer categoryId = CommandUtil.Category.getId(requestContent);
            Category category = logic.getCategory(categoryId);
            requestContent.getRequestAttributes().put("category", category);

            List<ProxyUser> admins = userCredentialsLogic.getAdminProxyUsers();
            requestContent.getSessionAttributes().put("admins", admins);
            page = ConfigurationManager.getInstance().getProperty("path.page.category.update");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getSessionAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }

        return page;
    }
}
