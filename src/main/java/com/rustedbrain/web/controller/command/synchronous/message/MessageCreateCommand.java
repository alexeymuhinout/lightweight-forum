package com.rustedbrain.web.controller.command.synchronous.message;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class MessageCreateCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            String text = CommandUtil.Message.getText(requestContent);
            Integer senderId = CommandUtil.User.getUser(requestContent).getId();
            Integer receiverId = null;
            try {
                receiverId = CommandUtil.Message.getReceiverId(requestContent);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Integer subcategoryId = CommandUtil.Subcategory.getId(requestContent);
            logic.createMessage(senderId, receiverId, text, subcategoryId);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("message.creation.success"));

            page = new MessagesShowCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new MessageCreateShowCommand().execute(requestContent);
        }

        return page;
    }
}
