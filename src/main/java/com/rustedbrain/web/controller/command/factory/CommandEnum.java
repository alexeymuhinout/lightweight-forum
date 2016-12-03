package com.rustedbrain.web.controller.command.factory;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.IndexShowCommand;
import com.rustedbrain.web.controller.command.category.*;
import com.rustedbrain.web.controller.command.message.*;
import com.rustedbrain.web.controller.command.subcategory.*;
import com.rustedbrain.web.controller.command.user.*;

public enum CommandEnum {

    INDEX_SHOW {
        {
            this.action = new IndexShowCommand();
        }
    },

    USER_LOGIN_SHOW {
        {
            this.action = new UserLoginShowCommand();
        }
    },

    LOGIN {
        {
            this.action = new UserLoginCommand();
        }
    },

    REGISTER {
        {
            this.action = new UserCreateCommand();
        }
    },

    REGISTRATION_SHOW {
        {
            this.action = new UserCreateShowCommand();
        }
    },

    USER_SHOW {
        {
            this.action = new UserShowCommand();
        }
    },

    USER_DELETE {
        {
            this.action = new UserDeleteCommand();
        }
    },

    USER_UPDATE_SHOW {
        {
            this.action = new UserUpdateShowCommand();
        }
    },

    USER_UPDATE {
        {
            this.action = new UserUpdateCommand();
        }
    },


    LOGOUT {
        {
            this.action = new UserLogoutCommand();
        }
    },

    CATEGORIES_SHOW {
        {
            this.action = new CategoriesShowCommand();
        }
    },

    CATEGORY_UPDATE_SHOW {
        {
            this.action = new CategoryUpdateShowCommand();
        }
    },

    CATEGORY_CREATE_SHOW {
        {
            this.action = new CategoryCreateShowCommand();
        }
    },

    CATEGORY_CREATE {
        {
            this.action = new CategoryCreateCommand();
        }
    },

    CATEGORY_UPDATE {
        {
            this.action = new CategoryUpdateCommand();
        }
    },

    CATEGORY_DELETE {
        {
            this.action = new CategoryDeleteCommand();
        }
    },

    SUBCATEGORY_CREATE {
        {
            this.action = new SubcategoryCreateCommand();
        }
    },

    SUBCATEGORY_DELETE {
        {
            this.action = new SubcategoryDeleteCommand();
        }
    },

    SUBCATEGORY_UPDATE_SHOW {
        {
            this.action = new SubcategoryUpdateShowCommand();
        }
    },

    SUBCATEGORY_UPDATE {
        {
            this.action = new SubcategoryUpdateCommand();
        }
    },

    SUBCATEGORIES_SHOW {
        {
            this.action = new SubcategoriesShowCommand();
        }
    },

    SUBCATEGORY_CREATE_SHOW {
        {
            this.action = new SubcategoryCreateShowCommand();
        }
    },

    MESSAGE_CREATE {
        {
            this.action = new MessageCreateCommand();
        }
    },

    MESSAGE_DELETE {
        {
            this.action = new MessageDeleteCommand();
        }
    },

    MESSAGE_CREATE_SHOW {
        {
            this.action = new MessageCreateShowCommand();
        }
    },

    MESSAGE_UPDATE_SHOW {
        {
            this.action = new MessageUpdateShowCommand();
        }
    },


    MESSAGE_UPDATE {
        {
            this.action = new MessageUpdateCommand();
        }
    },

    MESSAGES_SHOW {
        {
            this.action = new MessagesShowCommand();
        }
    };

    protected ActionCommand action;

    public ActionCommand getCurrentCommand() {
        return this.action;
    }
}
