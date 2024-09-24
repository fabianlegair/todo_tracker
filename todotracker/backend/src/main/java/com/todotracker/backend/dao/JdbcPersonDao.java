package com.todotracker.backend.dao;

import com.todotracker.backend.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcPersonDao implements PersonDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcPersonDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Person> getUsers() {
        return null;
    }

    @Override
    public Person getUserById(int id) {
        return null;
    }

    @Override
    public Person getUsersByUserName(String userName) {
        return null;
    }

    @Override
    public Person createUser(Person person) {
        return null;
    }

    @Override
    public Person updateUser(Person person) {
        return null;
    }

    @Override
    public int deleteUser(int id) {
        return 0;
    }
}
