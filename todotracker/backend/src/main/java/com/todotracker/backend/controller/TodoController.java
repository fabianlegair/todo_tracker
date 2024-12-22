package com.todotracker.backend.controller;

import com.todotracker.backend.dao.JdbcTodoDao;
import com.todotracker.backend.dao.TodoDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final TodoDao todoDao;

    public TodoController() {
        this.todoDao = new JdbcTodoDao();
    }


}
