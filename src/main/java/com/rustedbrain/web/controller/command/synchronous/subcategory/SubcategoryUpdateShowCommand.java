package com.rustedbrain.web.controller.command.synchronous.subcategory;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.jdbc.Subcategory;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class SubcategoryUpdateShowCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();
    private UserCredentialsLogic userCredentialsLogic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer subcategoryId = CommandUtil.Subcategory.getId(requestContent);
            Subcategory subcategory = logic.getSubcategory(subcategoryId);
            requestContent.getRequestAttributes().put("subcategory", subcategory);
            page = ConfigurationManager.getInstance().getProperty("path.page.subcategory.update");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getSessionAttributes().put("error", e.getMessage());
            page = new SubcategoriesShowCommand().execute(requestContent);
        }
        return page;
    }
}
