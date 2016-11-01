package com.rustedbrain.web.model.jdbc;

public class Subcategory extends Category {

    private int authorId;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Subcategory that = (Subcategory) o;

        return authorId == that.authorId;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + authorId;
        return result;
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "authorId=" + authorId +
                "} " + super.toString();
    }
}
