package com.rustedbrain.web.model;


import java.util.Date;
import java.util.List;

public class User extends Entity {

    private String name;
    private String surname;
    private String login;
    private String password;
    private String mail;
    private Date birthday;
    private City homeCity;
    private List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public City getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(City homeCity) {
        this.homeCity = homeCity;
    }
}
