package com.rustedbrain.web.model;

import java.util.List;

public class City extends DBEntity {

    private String name;
    private List<City> users;

    public List<City> getUsers() {
        return users;
    }

    public void setUsers(List<City> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
