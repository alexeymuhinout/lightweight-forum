package com.rustedbrain.web.model;

public class SwearWord extends DBEntity {

    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwearWord swearWord = (SwearWord) o;

        return word.equals(swearWord.word);

    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }
}
