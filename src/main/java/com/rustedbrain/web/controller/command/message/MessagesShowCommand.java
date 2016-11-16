package com.rustedbrain.web.controller.command.message;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.RequestParameters;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;
import com.rustedbrain.web.model.servlet.UserMessage;

import java.util.List;

public class MessagesShowCommand implements ActionCommand {
    private ForumLogic forumLogic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer subcategoryId = getSubcategoryId(requestContent);
            requestContent.getRequestAttributes().put("subcategory_id", subcategoryId);

            List<UserMessage> messages = forumLogic.getUserMessages(subcategoryId);
            requestContent.getRequestAttributes().put("messages", messages);

            page = ConfigurationManager.getInstance().getProperty("path.page.messages");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("message", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }

        return page;
    }

    private Integer getSubcategoryId(SessionRequestContent requestContent) {
        if ((requestContent.getRequestParameters().get(RequestParameters.SUBCATEGORY_ID_PARAMETER.getParameterName())) == null
                && (requestContent.getRequestParameters().get(RequestParameters.SUBCATEGORY_ID_PARAMETER.getParameterName()).length <= 0)) {
            throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.subcategory.id.not.found"));
        } else {
            return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.SUBCATEGORY_ID_PARAMETER.getParameterName())[0]);
        }
    }
}
