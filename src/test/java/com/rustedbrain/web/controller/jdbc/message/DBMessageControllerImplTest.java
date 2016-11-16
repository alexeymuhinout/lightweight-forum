package com.rustedbrain.web.controller.jdbc.message;

import com.rustedbrain.web.controller.jdbc.PostgresDBConnectorImpl;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.controller.resource.SQLManager;
import com.rustedbrain.web.controller.util.jaxb.JaxbParser;
import com.rustedbrain.web.controller.util.jaxb.Parser;
import com.rustedbrain.web.model.jdbc.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class DBMessageControllerImplTest {

    private Connection connection;
    private DBMessageControllerImpl dbMessageController;
    private Message message1;

    @Before
    public void setUp() throws Exception {
        String url = "jdbc:postgresql://127.0.0.1:5432/lightweight-forum-test";
        String dbUser = "postgres";
        String dbPassword = "postgres";
        Parser parser = new JaxbParser();
        DBUtil dbUtil = new DBUtil(ConfigurationManager.getInstance(), MessageManager.getInstance(), SQLManager.getInstance(), parser);
        this.message1 = createTestMessage(0, "Hello", 0, 1, 0);
        this.message1 = createTestMessage(1, "Hello", 1, 0, 0);
        this.dbMessageController = new DBMessageControllerImpl(ConfigurationManager.getInstance(), new PostgresDBConnectorImpl(url, dbUser, dbPassword), dbUtil);
    }

    @Test
    public void testGetAllMessages() throws Exception {
        List<Message> messages = dbMessageController.getAll();
        Assert.assertNotNull(messages);
    }

    @Test
    public void testInsertMessage() throws Exception {
        dbMessageController.insert(message1);
        Message insertedMessage = dbMessageController.getEntityById(message1.getId());
        Assert.assertEquals(message1, insertedMessage);
    }

    private Message createTestMessage(int id, String text, int userId, int replayToUserId, int subcategoryId) {
        Message message = new Message();
        message.setId(id);
        message.setText(text);
        message.setUserId(userId);
        message.setReplyToUserId(replayToUserId);
        message.setSubcategoryId(subcategoryId);
        return message;
    }
}
