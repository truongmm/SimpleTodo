package com.codepath.simpletodo;

/**
 * Created by truongmm on 9/26/15.
 */

public class ToDoItem {
    public String title;
    public String priority;

    public ToDoItem(String title, String priority) {
        this.title = title;
        this.priority = priority;
    }

    public String getDataInfo() {
        return title + ";" + priority;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setPriority(String newPriority) {
        this.priority = newPriority;
    }
}
