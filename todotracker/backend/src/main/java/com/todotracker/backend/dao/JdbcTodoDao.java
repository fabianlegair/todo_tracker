package com.todotracker.backend.dao;

import com.todotracker.backend.model.Todo;

import java.time.LocalDate;
import java.util.List;

public class JdbcTodoDao implements TodoDao{
    @Override
    public List<Todo> getTodos() {
        return null;
    }

    @Override
    public Todo getTodoById(int id) {
        return null;
    }

    @Override
    public Todo getTodoByName(String todoName) {
        return null;
    }

    @Override
    public List<Todo> getTodosByUserName(String userName) {
        return null;
    }

    @Override
    public List<Todo> getTodosByDateCreated(LocalDate dateCreated) {
        return null;
    }

    @Override
    public List<Todo> getTodosByDateCompleted(LocalDate dateCompleted) {
        return null;
    }

    @Override
    public Todo createTodo(Todo todo) {
        return null;
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return null;
    }

    @Override
    public Todo adminUpdateTodo(Todo todo) {
        return null;
    }

    @Override
    public int deleteTodo(int id) {
        return 0;
    }
}
