package com.rustedbrain.web.controller.command.synchronous.subcategory;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class SubcategoryUpdateCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            String newName = CommandUtil.Subcategory.getName(requestContent);
            Integer subcategoryId = CommandUtil.Subcategory.getId(requestContent);
            logic.updateSubcategory(subcategoryId, newName);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("subcategory.update.success"));
            page = new SubcategoriesShowCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new SubcategoryUpdateShowCommand().execute(requestContent);
        }
        return page;
    }
}
