package com.rustedbrain.web.controller.logic;

public class LoginLogic {
    private static LoginLogic ourInstance = new LoginLogic();

    private LoginLogic() {
    }

    public static LoginLogic getInstance() {
        return ourInstance;
    }

    public boolean checkLogin(String login, String password) {
        return true;
    }
}
