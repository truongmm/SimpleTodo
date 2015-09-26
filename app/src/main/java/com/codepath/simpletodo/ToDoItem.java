package com.codepath.simpletodo;

/**
 * Created by truongmm on 9/26/15.
 */

public class ToDoItem {
    public String title;

    public ToDoItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
}
