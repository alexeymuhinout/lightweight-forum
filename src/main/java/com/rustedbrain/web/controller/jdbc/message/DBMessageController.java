package com.rustedbrain.web.controller.jdbc.message;

import com.rustedbrain.web.controller.jdbc.DBController;
import com.rustedbrain.web.model.jdbc.Category;
import com.rustedbrain.web.model.jdbc.Message;
import com.rustedbrain.web.model.jdbc.Subcategory;
import com.rustedbrain.web.model.jdbc.User;
import com.rustedbrain.web.model.servlet.UserMessage;

import java.sql.SQLException;
import java.util.List;

public abstract class DBMessageController extends DBController<Message> {

    public abstract List<Message> getMessages(Category category);

    public abstract List<Message> getMessages(Subcategory subcategory);

    public abstract List<Message> getMessages(User user);

    public abstract List<UserMessage> getUserMessages(Integer subcategoryId) throws SQLException;
}
