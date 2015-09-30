package com.codepath.simpletodo;

/**
 * Created by truongmm on 9/26/15.
 */

public class ToDoItem {
    private String title;
    private String priority;
    private String dueDate;
    private boolean isCompleted;

    public ToDoItem(String title, String priority, String dueDate, boolean isCompleted) {
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    public String getDataInfo() {
        return isCompleted + ";" + title + ";" + priority + ";" + dueDate;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPriority() {
        return this.priority;
    }

    public String getDueDate() {
        return this.dueDate;
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

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void toggleCompletion() {
        isCompleted = !isCompleted;
    }
}
