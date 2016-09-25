package com.rustedbrain.web.controller.logic;

/**
 * Created by Bloodar on 25.09.2016.
 */
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
