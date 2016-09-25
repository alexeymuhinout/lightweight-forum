package com.rustedbrain.web.model;

import java.util.List;

public class Category extends DBEntity {

    private String name;
    private List<Subcategory> subcategories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
