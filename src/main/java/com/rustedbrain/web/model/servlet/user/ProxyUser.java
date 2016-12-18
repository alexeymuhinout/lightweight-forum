package com.rustedbrain.web.model.servlet.user;

import java.io.Serializable;
import java.sql.Date;

public class ProxyUser implements Serializable {

    private Integer id;
    private Date registrationDate;
    private String name;
    private String surname;
    private String login;
    private String mail;
    private Date birthday;
    private boolean admin;
    private String city;

    public ProxyUser() {
    }

    public ProxyUser(int id, Date registrationDate, String name, String surname, String login, String mail, Date birthday, boolean admin, String city) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.mail = mail;
        this.birthday = birthday;
        this.admin = admin;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
