package com.rustedbrain.web.controller.command.synchronous.message;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class MessageDeleteCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        try {
            logic.deleteMessage(CommandUtil.Message.getId(requestContent));
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("message.delete.success"));
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
        }
        return new MessagesShowCommand().execute(requestContent);
    }
}
