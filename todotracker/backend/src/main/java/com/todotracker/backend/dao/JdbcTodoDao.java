package com.todotracker.backend.dao;

import com.todotracker.backend.exception.DaoException;
import com.todotracker.backend.model.Person;
import com.todotracker.backend.model.Todo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.todotracker.backend.dao.JdbcPersonDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JdbcTodoDao implements TodoDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcTodoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        String sql = "select * from todo;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Todo todo = mapRowToTodo(results);
                todos.add(todo);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return todos;
    }

    @Override
    public Todo getTodoById(int id) {
        Todo todo = null;
        String sql = "select * from todo " +
                "where todo_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                todo = mapRowToTodo(results);
            } else return null;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return todo;
    }

    @Override
    public Todo getTodoByName(String todoName) {
        Todo todo = null;
        String sql = "select * from todo " +
                "where todo_name ilike ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, todoName);
            if (results.next()) {
                todo = mapRowToTodo(results);
            } else return null;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return todo;
    }

    @Override
    public List<Todo> getTodosByUserId(int userId) {
        List<Todo> todos = new ArrayList<>();
        String sql = "select * from todo " +
                "where user_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Todo todo = mapRowToTodo(results);
                todos.add(todo);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return todos;
    }

    @Override
    public List<Todo> getTodosByUserLogin(String userLogin) {
        List<Todo> todos = new ArrayList<>();
        String sql = "select * from todo " +
                "where user_id = " +
                "(select user_id from person " +
                "where user_login = ?)";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userLogin);
            while (results.next()) {
                Todo todo = mapRowToTodo(results);
                todos.add(todo);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return todos;
    }

    @Override
    public List<Todo> getTodosByDateCreated(LocalDate dateCreated) {
        List<Todo> todos = new ArrayList<>();
        String sql = "select * from todo " +
                "where date_created = ?;";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, dateCreated);
            while (rs.next()) {
                Todo todo = mapRowToTodo(rs);
                todos.add(todo);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return todos;
    }

    @Override
    public List<Todo> getTodosByDateCompleted(LocalDate dateCompleted) {
        List<Todo> todos = new ArrayList<>();
        String sql = "select * from todo " +
                "where date_completed = ?;";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, dateCompleted);
            while (rs.next()) {
                Todo todo = mapRowToTodo(rs);
                todos.add(todo);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return todos;
    }

    @Override
    public Todo createTodo(Todo todo) {
        Todo newTodo = null;
        String sql = "insert into todo (todo_name, user_id, description, date_created, date_completed, is_complete) " +
                "values (?, ?, ?, ?, ?, ?) returning todo_id;";

        try {
            int newTodoId = jdbcTemplate.queryForObject(sql, int.class, todo.getTodoName(), todo.getUserId(),
                    todo.getDescription(), todo.getDateCreated(), todo.getDateCompleted(), todo.isComplete());
            newTodo = getTodoById(newTodoId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return newTodo;
    }

    @Override
    public Todo updateTodo(Todo todo) {
        Todo updatedTodo = null;
        String sql = "update todo " +
                "set todo_name = ?, user_id = ?, description = ?, date_created = ?, " +
                "date_completed = ?, is_complete = ? " +
                "where todo_id = ?;";

        try {
            jdbcTemplate.update(sql, todo.getTodoName(), todo.getUserId(), todo.getDescription(),
                    todo.getDateCreated(), todo.getDateCompleted(), todo.isComplete(), todo.getTodoId());
            updatedTodo = getTodoById(todo.getTodoId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return updatedTodo;
    }

    @Override
    public int deleteTodo(int id) {
        int numberOfRows = 0;
        String sql = "delete from todo where todo_id = ?;";

        try {
            numberOfRows = jdbcTemplate.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return numberOfRows;
    }

    public Todo mapRowToTodo(SqlRowSet results) {
        Todo todo = new Todo();
        todo.setTodoId(results.getInt("todo_id"));
        todo.setTodoName(results.getString("todo_name"));
        todo.setUserId(results.getInt("user_id"));
        todo.setDescription(results.getString("description"));
        if (results.getDate("date_created") != null) {
            todo.setDateCreated(results.getDate("date_created").toLocalDate());
        }
        if (results.getDate("date_completed") != null) {
            todo.setDateCompleted(results.getDate("date_completed").toLocalDate());
        }
        todo.setComplete(results.getBoolean("is_complete"));
        return todo;
    }
}
