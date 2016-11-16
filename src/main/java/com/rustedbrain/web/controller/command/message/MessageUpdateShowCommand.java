package com.rustedbrain.web.controller.command.message;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.jdbc.Message;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class MessageUpdateShowCommand implements ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();
    private UserCredentialsLogic userCredentialsLogic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page = null;

        try {
            Integer messageId = CommandUtil.Message.getId(requestContent);
            Message message = logic.getMessage(messageId);
            requestContent.getRequestAttributes().put("userMessage", message);
            page = ConfigurationManager.getInstance().getProperty("path.page.message.update");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getSessionAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }
        return page;
    }
}
