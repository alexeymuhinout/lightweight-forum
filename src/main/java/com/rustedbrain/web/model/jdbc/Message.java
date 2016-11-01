package com.rustedbrain.web.model.jdbc;

public class Message extends DBEntity {

    private int subcategoryId;
    private int userId;
    private int replyToUserId;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReplyToUserId() {
        return replyToUserId;
    }

    public void setReplyToUserId(int replyToUserId) {
        this.replyToUserId = replyToUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Message message = (Message) o;

        if (subcategoryId != message.subcategoryId) return false;
        return text.equals(message.text);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + subcategoryId;
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "subcategoryId=" + subcategoryId +
                ", userId=" + userId +
                ", replyToUserId=" + replyToUserId +
                ", text='" + text + '\'' +
                '}';
    }
}
