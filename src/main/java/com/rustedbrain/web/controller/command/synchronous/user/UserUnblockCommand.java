package com.rustedbrain.web.controller.command.synchronous.user;


import com.rustedbrain.web.controller.command.synchronous.IndexShowCommand;
import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class UserUnblockCommand implements SynchronousActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            String login = CommandUtil.User.getLogin(requestContent);
            logic.unblockUser(login);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("user.unblock.success").replace("%1", login));
            page = new IndexShowCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new UserPreviewShowCommand().execute(requestContent);
        }
        return page;
    }
}
