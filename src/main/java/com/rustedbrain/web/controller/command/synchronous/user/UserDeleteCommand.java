package com.rustedbrain.web.controller.command.synchronous.user;

import com.rustedbrain.web.controller.command.synchronous.IndexShowCommand;
import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class UserDeleteCommand implements SynchronousActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer userId = CommandUtil.User.getId(requestContent);
            logic.deleteUser(userId);
            requestContent.setInvalidatedSession(true);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("profile.delete.success"));
            page = new IndexShowCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }
        return page;
    }
}
