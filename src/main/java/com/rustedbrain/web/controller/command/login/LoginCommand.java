package com.rustedbrain.web.controller.command.login;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.ProxyUser;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.sql.SQLException;

public class LoginCommand implements ActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page = null;

        try {
            LoginPassword loginPassword = getLoginPassword(requestContent);
            if (logic.isCorrectLoginCredentials(loginPassword.getLogin(), loginPassword.getPassword())) {
                ProxyUser user = logic.getProxyUserByLogin(loginPassword.getLogin());
                requestContent.getSessionAttributes().put("user", user);
                requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("login.success"));
                page = ConfigurationManager.getInstance().getProperty("path.page.index");
            } else {
                String loginErrorMessage = "Wrong login or password";
                requestContent.getRequestAttributes().put("message", loginErrorMessage);
                page = ConfigurationManager.getInstance().getProperty("path.page.login");
            }
        } catch (IllegalArgumentException e) {
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.login");
        } catch (SQLException e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }

        return page;
    }

    private LoginPassword getLoginPassword(SessionRequestContent requestContent) throws IllegalArgumentException {

        if ((requestContent.getRequestParameters().get("login") != null)
                && (requestContent.getRequestParameters().get("password") != null)
                && (requestContent.getRequestParameters().get("login").length >= 1)
                && (requestContent.getRequestParameters().get("password").length >= 1)
                ) {
            LoginPassword loginPassword = new LoginPassword();
            loginPassword.setLogin(requestContent.getRequestParameters().get("login")[0]);
            loginPassword.setPassword(requestContent.getRequestParameters().get("password")[0]);
            return loginPassword;
        } else {
            throw new IllegalArgumentException("Login and password must contain symbols");
        }
    }

    private class LoginPassword {

        private String login;
        private String password;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
