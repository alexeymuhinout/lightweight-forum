package com.rustedbrain.web.model.servlet;

import com.rustedbrain.web.model.jdbc.Subcategory;

import java.io.Serializable;

public class UserSubcategory implements Serializable {

    private Subcategory subcategory;
    private String userName;

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
