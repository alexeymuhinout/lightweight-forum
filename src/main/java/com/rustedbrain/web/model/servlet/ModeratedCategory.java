package com.rustedbrain.web.model.servlet;

import com.rustedbrain.web.model.jdbc.Category;

import java.io.Serializable;

public class ModeratedCategory implements Serializable {

    private Category category;
    private String moderator;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getModerator() {
        return moderator;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }
}
