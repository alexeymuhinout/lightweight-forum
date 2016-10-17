package com.rustedbrain.web.controller.command.factory;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.LoginCommand;
import com.rustedbrain.web.controller.command.LogoutCommand;
import com.rustedbrain.web.controller.command.ShowForumCommand;

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
    },

    SHOW_FORUM {
        {
            this.action = new ShowForumCommand();
        }
    };

    protected ActionCommand action;

    public ActionCommand getCurrentCommand() {
        return this.action;
    }
}
