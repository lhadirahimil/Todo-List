package com.hadirahimi.todo_list.Model;

public class ModelCategory {
    private int category_id;
    private String category_title;
    private int image;


    public ModelCategory(int category_id, String category_title, int image) {
        this.category_id = category_id;
        this.category_title = category_title;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }
}
