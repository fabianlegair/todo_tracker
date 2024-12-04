package com.todotracker.backend.model;

import java.time.LocalDate;
import java.util.Date;

public class Todo {

    private int todoId;
    private String todoName;
    private int userId;
    private String description;
    private LocalDate dateCreated;
    private LocalDate dateCompleted;
    private boolean isComplete;

    public Todo(String todoName, int userId, String description,
                LocalDate dateCreated, LocalDate dateCompleted, boolean isComplete) {
        this.todoName = todoName;
        this.userId = userId;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
        this.isComplete = isComplete;
    }

    public Todo(String todoName, int userId, String description, LocalDate dateCreated,
                boolean isComplete) {
        this.todoName = todoName;
        this.userId = userId;
        this.description = description;
        this.dateCreated = dateCreated;
        this.isComplete = isComplete;
    }

    public Todo() { }

    public Todo(int todoId, String todoName, int userId, String description,
                LocalDate dateCreated, LocalDate dateCompleted, boolean isComplete) {
        this.todoId = todoId;
        this.todoName = todoName;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
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

    public void setUserId(int userId) {
        this.userId = userId;
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
                "Assigned To: " + userId + "\n" +
                "Description: " + description + "\n" +
                "Date Created: " + dateCreated + "\n";
    }
}
