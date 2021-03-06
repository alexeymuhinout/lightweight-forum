package com.rustedbrain.web.model.jdbc;


import java.sql.Date;

public class User extends DBEntity {

    private String name;
    private String surname;
    private String login;
    private String password;
    private String mail;
    private Date birthday;
    private Integer cityId;
    private boolean isAdmin;
    private Date blockUntilDate;
    private String blockReason;

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public Date getBlockUntilDate() {
        return blockUntilDate;
    }

    public void setBlockUntilDate(Date blockedUntilDate) {
        this.blockUntilDate = blockedUntilDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return name.equals(user.name) && surname.equals(user.surname) && login.equals(user.login);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                "} " + super.toString();
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        User clonedUser = (User) super.clone();
        clonedUser.birthday = (Date) this.birthday.clone();
        if (this.blockUntilDate != null) {
            clonedUser.blockUntilDate = (Date) this.blockUntilDate.clone();
        }
        return clonedUser;
    }
}
