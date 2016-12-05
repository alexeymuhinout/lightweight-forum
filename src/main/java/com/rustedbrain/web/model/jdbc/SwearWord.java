package com.rustedbrain.web.model.jdbc;

public class SwearWord extends DBEntity {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwearWord swearWord = (SwearWord) o;

        return text.equals(swearWord.text);

    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
