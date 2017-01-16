package com.rustedbrain.web.controller.command.synchronous.user;


import com.rustedbrain.web.controller.command.synchronous.IndexShowCommand;
import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.sql.Date;

public class UserBlockCommand implements SynchronousActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            String login = CommandUtil.User.getLogin(requestContent);
            String blockReason = CommandUtil.User.getBlockReason(requestContent);
            Date blockUntilDate = CommandUtil.User.getBlockUntilDate(requestContent);
            logic.blockUser(login, blockReason, blockUntilDate);
            requestContent.getRequestAttributes()
                    .put("message", MessageManager.getInstance().getProperty("user.block.success")
                            .replace("%1", login)
                            .replace("%2", blockUntilDate.toString()));
            page = new IndexShowCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new UserPreviewShowCommand().execute(requestContent);
        }
        return page;
    }
}
