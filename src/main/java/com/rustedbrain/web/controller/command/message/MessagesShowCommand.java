package com.rustedbrain.web.controller.command.message;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.RequestParameters;
import com.rustedbrain.web.controller.command.category.CategoriesShowCommand;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class MessagesShowCommand implements ActionCommand {
    private ForumLogic forumLogic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer subcategoryId = getSubcategoryId(requestContent);
            requestContent.getRequestAttributes().put("subcategory_id", subcategoryId);
            requestContent.getRequestAttributes().put("messages", forumLogic.getUserMessages(subcategoryId));
            page = ConfigurationManager.getInstance().getProperty("path.page.messages");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new CategoriesShowCommand().execute(requestContent);
        }

        return page;
    }

    private Integer getSubcategoryId(SessionRequestContent requestContent) {
        if ((requestContent.getRequestParameters().get(RequestParameters.SUBCATEGORY_ID.getParameterName())) == null
                && (requestContent.getRequestParameters().get(RequestParameters.SUBCATEGORY_ID.getParameterName()).length <= 0)) {
            throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.subcategory.id.not.found"));
        } else {
            return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.SUBCATEGORY_ID.getParameterName())[0]);
        }
    }
}
