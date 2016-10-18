package com.rustedbrain.web.controller.jdbc.message;

import com.rustedbrain.web.controller.jdbc.DBController;
import com.rustedbrain.web.model.Category;
import com.rustedbrain.web.model.Message;
import com.rustedbrain.web.model.Subcategory;
import com.rustedbrain.web.model.User;

import java.util.List;

public abstract class DBMessageController extends DBController<Message> {

    public abstract List<Message> getMessages(Category category);

    public abstract List<Message> getMessages(Subcategory subcategory);

    public abstract List<Message> getMessages(User user);
}
