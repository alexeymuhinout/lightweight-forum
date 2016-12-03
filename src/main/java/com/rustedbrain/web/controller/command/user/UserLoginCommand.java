package com.rustedbrain.web.controller.command.user;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.ProxyUser;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class UserLoginCommand implements ActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page = null;

        try {
            String login = CommandUtil.User.getLogin(requestContent);
            String password = CommandUtil.User.getPassword(requestContent);
            if (logic.isCorrectLoginCredentials(login, password)) {
                ProxyUser user = logic.getProxyUserByLogin(login);
                requestContent.getSessionAttributes().put("user", user);
                requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("login.success"));
                page = ConfigurationManager.getInstance().getProperty("path.page.index");
            } else {
                String loginErrorMessage = "Wrong user or password";
                requestContent.getRequestAttributes().put("error", loginErrorMessage);
                page = ConfigurationManager.getInstance().getProperty("path.page.login");
            }
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new UserLoginShowCommand().execute(requestContent);
        }

        return page;
    }
}
