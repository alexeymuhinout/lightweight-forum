package com.rustedbrain.web.controller.command.synchronous.message;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class MessageUpdateCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer messageId = CommandUtil.Message.getId(requestContent);
            String text = CommandUtil.Message.getText(requestContent);
            logic.updateMessage(messageId, text);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("message.update.success"));
            page = new MessagesShowCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new MessageUpdateShowCommand().execute(requestContent);
        }
        return page;
    }
}
