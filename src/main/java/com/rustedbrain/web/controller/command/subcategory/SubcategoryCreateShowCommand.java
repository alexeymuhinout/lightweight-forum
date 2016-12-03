package com.rustedbrain.web.controller.command.subcategory;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class SubcategoryCreateShowCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer categoryId = CommandUtil.Category.getId(requestContent);
            requestContent.getRequestAttributes().put("category_id", categoryId);
            page = ConfigurationManager.getInstance().getProperty("path.page.subcategory.create");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getSessionAttributes().put("error", e.getMessage());
            page = new SubcategoriesShowCommand().execute(requestContent);
        }
        return page;
    }
}
