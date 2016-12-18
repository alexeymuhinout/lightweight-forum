package com.rustedbrain.web.controller.command.user;


import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.IndexShowCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class UserUnblockCommand implements ActionCommand {

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
