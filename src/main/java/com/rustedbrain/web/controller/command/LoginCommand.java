package com.rustedbrain.web.controller.command;

import com.rustedbrain.web.controller.logic.LoginLogic;
import com.rustedbrain.web.model.SessionRequestContent;

public class LoginCommand implements ActionCommand {

    private LoginLogic logic = LoginLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page = null;

        String login = requestContent.getRequestParameters().get("login")[0];
        String password = requestContent.getRequestParameters().get("password")[0];

        try {
            if (logic.checkLogin(login, password)) {

            } else {

            }
        } catch (IllegalArgumentException e) {

        }

        return page;
    }
}
