package com.rustedbrain.web.model.servlet;

import com.rustedbrain.web.model.jdbc.Message;

import java.io.Serializable;
import java.sql.Date;

public class UserMessage implements Serializable {

    private Message message;
    private String subcategoryName;
    private String senderName;
    private Date senderRegistrationDate;
    private String receiverName;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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
                ", senderName='" + senderName + '\'' +
                ", senderRegistrationDate=" + senderRegistrationDate +
                ", receiverName='" + receiverName + '\'' +
                '}';
    }
}
