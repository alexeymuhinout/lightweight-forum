package com.rustedbrain.web.model.jdbc;

public class Message extends DBEntity {

    private Integer subcategoryId;
    private Integer userId;
    private Integer replyToUserId;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReplyToUserId() {
        return replyToUserId;
    }

    public void setReplyToUserId(Integer replyToUserId) {
        this.replyToUserId = replyToUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Message message = (Message) o;

        return subcategoryId.equals(message.subcategoryId) && userId.equals(message.userId) && text.equals(message.text);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + subcategoryId.hashCode();
        result = 31 * result + userId.hashCode();
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
