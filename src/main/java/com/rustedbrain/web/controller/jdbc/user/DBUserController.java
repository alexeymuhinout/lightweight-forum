package com.rustedbrain.web.controller.jdbc.user;

import com.rustedbrain.web.controller.jdbc.CRUDController;
import com.rustedbrain.web.model.User;

public interface DBUserController extends CRUDController<User> {

    User getUser(String login);
}
