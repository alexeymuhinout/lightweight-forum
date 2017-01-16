package com.rustedbrain.web.controller.command.synchronous.message;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class MessageUpdateShowCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();
    private UserCredentialsLogic userCredentialsLogic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer messageId = CommandUtil.Message.getId(requestContent);
            requestContent.getRequestAttributes().put("userMessage", logic.getMessage(messageId));
            page = ConfigurationManager.getInstance().getProperty("path.page.message.update");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getSessionAttributes().put("error", e.getMessage());
            page = new MessagesShowCommand().execute(requestContent);
        }
        return page;
    }
}
