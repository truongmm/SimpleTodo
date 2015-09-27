package com.codepath.simpletodo;

/**
 * Created by truongmm on 9/26/15.
 */

public class ToDoItem {
    private String title;
    private String priority;
    private boolean isCompleted;

    public ToDoItem(String title, String priority, boolean isCompleted) {
        this.title = title;
        this.priority = priority;
        this.isCompleted = isCompleted;
    }

    public String getDataInfo() {
        return isCompleted + ";" + title + ";" + priority;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPriority() {
        return this.priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setPriority(String newPriority) {
        this.priority = newPriority;
    }

    public void toggleCompletion() {
        isCompleted = !isCompleted;
    }
}
