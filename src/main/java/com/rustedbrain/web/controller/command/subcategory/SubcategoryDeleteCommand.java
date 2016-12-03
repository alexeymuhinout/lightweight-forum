package com.rustedbrain.web.controller.command.subcategory;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class SubcategoryDeleteCommand implements ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        try {
            Integer subcategoryId = CommandUtil.Subcategory.getId(requestContent);
            logic.deleteSubcategory(subcategoryId);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("subcategory.delete.success"));
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
        }

        return new SubcategoriesShowCommand().execute(requestContent);
    }
}
