package com.todotracker.backend.dao;
import com.todotracker.backend.model.Todo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TodoDao {

    List<Todo> getTodos();
    Todo getTodoById(int id);
    Todo getTodoByName(String todoName);
    List<Todo> getTodosByUserId(int userId);
    List<Todo> getTodosByUserLogin(String userLogin);
    List<Todo> getTodosByDateCreated(LocalDate dateCreated);
    List<Todo> getTodosByDateCompleted(LocalDate dateCompleted);
    Todo createTodo(Todo todo);
    Todo updateTodo(Todo todo);
    int deleteTodo(int id);
}
