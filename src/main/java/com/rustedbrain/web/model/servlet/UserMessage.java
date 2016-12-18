package com.rustedbrain.web.model.servlet;

import com.rustedbrain.web.model.jdbc.Message;

import java.io.Serializable;
import java.sql.Date;

public class UserMessage implements Serializable {

    private Message message;
    private String subcategoryName;
    private String senderLogin;
    private Date senderRegistrationDate;
    private String receiverLogin;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public String getReceiverLogin() {
        return receiverLogin;
    }

    public void setReceiverLogin(String receiverLogin) {
        this.receiverLogin = receiverLogin;
    }

    public Date getSenderRegistrationDate() {
        return senderRegistrationDate;
    }

    public void setSenderRegistrationDate(Date senderRegistrationDate) {
        this.senderRegistrationDate = senderRegistrationDate;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "message=" + message +
                ", subcategoryName='" + subcategoryName + '\'' +
                ", senderLogin='" + senderLogin + '\'' +
                ", senderRegistrationDate=" + senderRegistrationDate +
                ", receiverLogin='" + receiverLogin + '\'' +
                '}';
    }
}
