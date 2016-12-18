package com.rustedbrain.web.controller.command.user;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.IndexShowCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;
import com.rustedbrain.web.model.servlet.user.ProxyUser;

public class UserLoginCommand implements ActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            String login = CommandUtil.User.getLogin(requestContent);
            String password = CommandUtil.User.getPassword(requestContent);
            if (logic.isCorrectLoginCredentials(login, password)) {
                ProxyUser user = logic.getProxyUserByLogin(login);
                requestContent.getSessionAttributes().put("user", user);
                requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("login.success"));
                page = new IndexShowCommand().execute(requestContent);
            } else {
                requestContent.getRequestAttributes().put("error", MessageManager.getInstance().getProperty("login.not.success"));
                page = new UserLoginShowCommand().execute(requestContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new UserLoginShowCommand().execute(requestContent);
        }

        return page;
    }
}
