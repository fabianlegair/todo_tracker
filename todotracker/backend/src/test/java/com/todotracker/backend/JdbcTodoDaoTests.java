package com.todotracker.backend;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.todotracker.backend.dao.JdbcPersonDao;
import com.todotracker.backend.dao.JdbcTodoDao;
import com.todotracker.backend.dao.TodoDao;
import com.todotracker.backend.model.Todo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

public class JdbcTodoDaoTests extends BaseDaoTests {

    private JdbcTodoDao dao;
    private final Todo newTodo = new Todo("Re-fill water bottle", 2,
            "Put water inside of water bottle", LocalDate.parse("2024-11-20"), false);
    private final Todo updateTodo = new Todo(1, "Wash car", 1, "Alesha needs to wash her car",
            LocalDate.parse("2024-11-11"), LocalDate.parse("2024-11-20"), true);


    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcTodoDao(jdbcTemplate);
    }

    @Test
    public void getTodos_gets_todos() {
        List<Todo> todos = dao.getTodos();
        Assert.assertEquals(2, todos.size());
    }

    @Test
    public void getTodoById_with_valid_id_returns_todo() {
        Todo todo = dao.getTodoById(1);
        Assert.assertEquals("Wash car", todo.getTodoName());
    }

    @Test
    public void getTodoById_with_invalid_id_returns_null() {
        Todo todo = dao.getTodoById(5);
        Assert.assertNull(todo);
    }

    @Test
    public void getTodoByName_with_valid_name_returns_todo() {
        Todo todo = dao.getTodoByName("Wash car");
        Assert.assertEquals(1,todo.getTodoId());
    }

    @Test
    public void getTodoByName_with_invalid_name_returns_null() {
        Todo todo = dao.getTodoByName("Clean car");
        Assert.assertNull(todo);
    }

    @Test
    public void getTodosByUserId_returns_todos() {
        List<Todo> todos = dao.getTodosByUserId(1);
        Assert.assertEquals(1, todos.size());
    }

    @Test
    public void getTodosByUserLogin_returns_todos() {
        List<Todo> todos = dao.getTodosByUserLogin("alegair");
        Assert.assertEquals(1, todos.size());
    }

    @Test
    public void getTodosByDateCreated_returns_todos() {
        List<Todo> todos = dao.getTodosByDateCreated(LocalDate.parse("2024-11-01"));
        Assert.assertEquals(2, todos.size());
    }

    @Test
    public void getTodosByDateCompleted_returns_todos() {
        List<Todo> todos = dao.getTodosByDateCompleted(LocalDate.parse("2024-11-10"));
        Assert.assertEquals(1, todos.size());
    }

    @Test
    public void createTodo_creates_todo() {
        dao.createTodo(newTodo);
        Todo createdTodo = dao.getTodoById(3);
        Assert.assertEquals("Re-fill water bottle", createdTodo.getTodoName());
    }

    @Test
    public void updateTodo_updates_todo() {
        dao.updateTodo(updateTodo);
        Todo updatedTodo = dao.getTodoById(1);
        Assert.assertEquals("Wash car", updatedTodo.getTodoName());
    }

    @Test
    public void deleteTodo_deletes_todo() {
        dao.deleteTodo(2);
        Todo deletedTodo = dao.getTodoById(2);
        Assert.assertNull(deletedTodo);
    }
}
