package com.todotracker.backend.controllers;

import com.todotracker.backend.dao.JdbcTodoDao;
import com.todotracker.backend.dao.TodoDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final JdbcTemplate jdbcTemplate;
    private final TodoDao todoDao;

    public TodoController(JdbcTemplate jdbcTemplate) {

        this.todoDao = new JdbcTodoDao(jdbcTemplate);
    }


}
