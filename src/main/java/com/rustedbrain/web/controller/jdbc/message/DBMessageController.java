package com.rustedbrain.web.controller.jdbc.message;

import com.rustedbrain.web.controller.jdbc.CRUDController;
import com.rustedbrain.web.model.Category;
import com.rustedbrain.web.model.Message;
import com.rustedbrain.web.model.Subcategory;
import com.rustedbrain.web.model.User;

import java.util.List;

public interface DBMessageController extends CRUDController<Message> {

    List<Message> getMessages(Category category);

    List<Message> getMessages(Subcategory subcategory);

    List<Message> getMessages(User user);
}
