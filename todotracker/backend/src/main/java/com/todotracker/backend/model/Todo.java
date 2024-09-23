package com.todotracker.backend.model;

import java.time.LocalDate;

public class Todo {

    private int todoId;
    private String todoName;
    private String userName;
    private String description;
    private LocalDate dateCreated;
    private LocalDate dateCompleted;
    private boolean isComplete;

    public Todo(int todoId, String todoName, String userName, String description,
                LocalDate dateCreated, LocalDate dateCompleted, boolean isComplete) {
        this.todoId = todoId;
        this.todoName = todoName;
        this.userName = userName;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
        this.isComplete = isComplete;
    }

    public int getTodoId() {
        return todoId;
    }

    public String getTodoName() {
        return todoName;
    }

    public String getUserName() {
        return userName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "To-Do ID: " + todoId + "\n" +
                "To-Do Name: " + todoName + "\n" +
                "Assigned To: " + userName + "\n" +
                "Description: " + description + "\n" +
                "Date Created: " + dateCreated + "\n" +
                "To-Do Name: " + todoName + "\n";
    }
}
