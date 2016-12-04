package com.rustedbrain.web.controller.logic;

import com.rustedbrain.web.controller.jdbc.PostgresDBConnectorImpl;
import com.rustedbrain.web.controller.jdbc.category.DBCategoryController;
import com.rustedbrain.web.controller.jdbc.category.DBCategoryControllerImpl;
import com.rustedbrain.web.controller.jdbc.message.DBMessageController;
import com.rustedbrain.web.controller.jdbc.message.DBMessageControllerImpl;
import com.rustedbrain.web.controller.jdbc.message.DBSwearWordController;
import com.rustedbrain.web.controller.jdbc.message.DBSwearWordControllerImpl;
import com.rustedbrain.web.controller.jdbc.subcategory.DBSubcategoryController;
import com.rustedbrain.web.controller.jdbc.subcategory.DBSubcategoryControllerImpl;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.controller.resource.SQLManager;
import com.rustedbrain.web.controller.util.jaxb.JaxbParser;
import com.rustedbrain.web.model.exception.SwearWordException;
import com.rustedbrain.web.model.jdbc.Category;
import com.rustedbrain.web.model.jdbc.Message;
import com.rustedbrain.web.model.jdbc.Subcategory;
import com.rustedbrain.web.model.jdbc.SwearWord;
import com.rustedbrain.web.model.servlet.ModeratedCategory;
import com.rustedbrain.web.model.servlet.UserMessage;
import com.rustedbrain.web.model.servlet.UserSubcategory;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ForumLogic {

    private static ForumLogic ourInstance = new ForumLogic();

    private DBCategoryController dbCategoryController =
            new DBCategoryControllerImpl(
                    SQLManager.getInstance(),
                    PostgresDBConnectorImpl.getPostgresDBConnector(),
                    new DBUtil(ConfigurationManager.getInstance(), MessageManager.getInstance(), SQLManager.getInstance(), new JaxbParser()));

    private DBSubcategoryController dbSubcategoryController =
            new DBSubcategoryControllerImpl(
                    SQLManager.getInstance(),
                    PostgresDBConnectorImpl.getPostgresDBConnector(),
                    new DBUtil(ConfigurationManager.getInstance(), MessageManager.getInstance(), SQLManager.getInstance(), new JaxbParser()));

    private DBMessageController dbMessageController =
            new DBMessageControllerImpl(
                    SQLManager.getInstance(),
                    PostgresDBConnectorImpl.getPostgresDBConnector(),
                    new DBUtil(ConfigurationManager.getInstance(), MessageManager.getInstance(), SQLManager.getInstance(), new JaxbParser()));

    private DBSwearWordController dbSwearWordController =
            new DBSwearWordControllerImpl(
                    SQLManager.getInstance(),
                    PostgresDBConnectorImpl.getPostgresDBConnector(),
                    new DBUtil(ConfigurationManager.getInstance(), MessageManager.getInstance(), SQLManager.getInstance(), new JaxbParser()));

    private ForumLogic() {
    }

    public static ForumLogic getInstance() {
        return ourInstance;
    }

    public List<Category> getCategories(int currentCategoriesPage, int maxCategoriesPerPage) throws SQLException {
        return dbCategoryController.getCategories(currentCategoriesPage, maxCategoriesPerPage);
    }

    public List<ModeratedCategory> getModeratedCategories() throws SQLException {
        return dbCategoryController.getModeratedCategories();
    }

    public void createCategory(Integer creatorId, String name) throws SQLException {
        Category category = new Category();
        category.setCreationDate(Date.valueOf(LocalDate.now()));
        category.setName(name);
        category.setUserId(creatorId);
        dbCategoryController.insert(category);
    }

    public List<Subcategory> getSubcategories(Integer categoryId) throws SQLException {
        return dbSubcategoryController.getSubcategories(categoryId);
    }

    public void createSubcategory(Integer creatorId, Integer categoryId, String subcategoryName) throws SQLException {
        Subcategory subcategory = new Subcategory();
        subcategory.setCreationDate(Date.valueOf(LocalDate.now()));
        subcategory.setUserId(creatorId);
        subcategory.setCategoryId(categoryId);
        subcategory.setName(subcategoryName);
        dbSubcategoryController.insert(subcategory);
    }

    public void deleteCategory(Integer categoryId) throws SQLException {
        Category category = dbCategoryController.getEntityById(categoryId);
        dbCategoryController.delete(category);
    }

    public Category getCategory(Integer categoryId) throws SQLException {
        return dbCategoryController.getEntityById(categoryId);
    }

    public void updateCategory(Integer categoryId, String newName, Integer newAdmin) throws SQLException, CloneNotSupportedException {
        Category oldCategory = dbCategoryController.getEntityById(categoryId);
        Category newCategory = (Category) oldCategory.clone();
        newCategory.setName(newName);
        newCategory.setUserId(newAdmin);
        dbCategoryController.update(oldCategory, newCategory);
    }

    public List<UserSubcategory> getUserSubcategories(Integer categoryId) throws SQLException {
        return dbSubcategoryController.getUserSubcategories(categoryId);
    }

    public Integer getCategoryId(Integer subcategoryId) throws SQLException {
        return dbSubcategoryController.getEntityById(subcategoryId).getCategoryId();
    }

    public void updateSubcategory(Integer subcategoryId, String newName) throws SQLException, CloneNotSupportedException {
        Subcategory oldCategory = dbSubcategoryController.getEntityById(subcategoryId);
        Subcategory newCategory = (Subcategory) oldCategory.clone();
        newCategory.setName(newName);
        dbSubcategoryController.update(oldCategory, newCategory);
    }

    public void deleteSubcategory(Integer subcategoryId) throws SQLException {
        Subcategory subcategory = dbSubcategoryController.getEntityById(subcategoryId);
        dbSubcategoryController.delete(subcategory);
    }

    public Subcategory getSubcategory(Integer subcategoryId) throws SQLException {
        return dbSubcategoryController.getEntityById(subcategoryId);
    }

    public List<UserMessage> getUserMessages(Integer subcategoryId) throws SQLException {
        return dbMessageController.getUserMessages(subcategoryId);
    }

    public void createMessage(Integer senderId, Integer receiverId, String text, Integer subcategoryId) throws SQLException {
        if (isTextContainSwearWord(text)) {
            throw new SwearWordException(MessageManager.getInstance().getProperty("message.create.swear-word"));
        }
        Message message = new Message();
        message.setCreationDate(Date.valueOf(LocalDate.now()));
        message.setUserId(senderId);
        message.setReplyToUserId(receiverId);
        message.setText(text);
        message.setSubcategoryId(subcategoryId);
        dbMessageController.insert(message);
    }

    public void deleteMessage(Integer messageId) throws SQLException {
        Message message = this.dbMessageController.getEntityById(messageId);
        this.dbMessageController.delete(message);
    }

    public Message getMessage(Integer messageId) throws SQLException {
        return this.dbMessageController.getEntityById(messageId);
    }

    public void updateMessage(Integer messageId, String text) throws SQLException, CloneNotSupportedException {
        Message oldMessage = this.dbMessageController.getEntityById(messageId);
        if (oldMessage.getText().equals(text)) {
            throw new IllegalArgumentException(MessageManager.getInstance().getProperty("message.update.already.exist"));
        }

        if (isTextContainSwearWord(text)) {
            throw new SwearWordException(MessageManager.getInstance().getProperty("message.update.swear-word"));
        }

        Message newMessage = (Message) oldMessage.clone();
        newMessage.setText(text);
        this.dbMessageController.update(oldMessage, newMessage);
    }

    private boolean isTextContainSwearWord(String text) throws SQLException {
        List<SwearWord> swearWords = dbSwearWordController.getAll();

        for (SwearWord swearWord : swearWords) {
            if (text.contains(swearWord.getText())) {
                return true;
            }
        }

        return false;
    }

    public List<SwearWord> getSwearWords() throws SQLException {
        return this.dbSwearWordController.getAll();
    }

    public void createSwearWord(String text) throws SQLException {
        if (!dbSwearWordController.isSwearWord(text)) {
            SwearWord swearWord = new SwearWord();
            swearWord.setCreationDate(Date.valueOf(LocalDate.now()));
            swearWord.setText(text);
            this.dbSwearWordController.insert(swearWord);
        } else {
            throw new IllegalArgumentException(MessageManager.getInstance().getProperty("swear-word.creation.already.exist"));
        }
    }

    public void deleteSwearWord(Integer id) throws SQLException {
        SwearWord swearWord = this.dbSwearWordController.getEntityById(id);
        this.dbSwearWordController.delete(swearWord);
    }

    public void updateSwearWord(Integer swearWordId, String text) throws SQLException, CloneNotSupportedException {
        SwearWord oldSwearWord = this.dbSwearWordController.getEntityById(swearWordId);
        if (oldSwearWord.getText().equals(text)) {
            throw new IllegalArgumentException(MessageManager.getInstance().getProperty("swear-word.updating.already.exist"));
        }
        SwearWord newSwearWord = (SwearWord) oldSwearWord.clone();
        newSwearWord.setText(text);

        this.dbSwearWordController.update(oldSwearWord, newSwearWord);
    }
}
