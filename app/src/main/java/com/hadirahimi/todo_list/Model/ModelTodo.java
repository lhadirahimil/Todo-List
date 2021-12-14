package com.hadirahimi.todo_list.Model;

public class ModelTodo {
    private int id;
    private String title;
    private int checked;
    private int category_id;

    public ModelTodo(int id, String title, int checked, int category_id) {
        this.id = id;
        this.title = title;
        this.checked = checked;
        this.category_id = category_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }
}
