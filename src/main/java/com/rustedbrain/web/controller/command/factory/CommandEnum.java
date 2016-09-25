package com.rustedbrain.web.controller.command.factory;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.LoginCommand;
import com.rustedbrain.web.controller.command.LogoutCommand;

/**
 * Created by Bloodar on 25.09.2016.
 */
public enum CommandEnum {

    LOGIN {
        {
            this.action = new LoginCommand();
        }
    },

    LOGOUT {
        {
            this.action = new LogoutCommand();
        }
    };

    private ActionCommand action;

    public ActionCommand getCurrentCommand() {
        return this.action;
    }
}
