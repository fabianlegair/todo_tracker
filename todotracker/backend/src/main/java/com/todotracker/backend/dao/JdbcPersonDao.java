package com.todotracker.backend.dao;

import com.todotracker.backend.exception.DaoException;
import com.todotracker.backend.model.Person;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcPersonDao implements PersonDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcPersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> getUsers() {
        List<Person> persons = new ArrayList<>();
        String sql = "select * from person;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Person person = mapRowToPerson(results);
                persons.add(person);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return persons;
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

    public Person mapRowToPerson(SqlRowSet results) {
        Person person = new Person();
        person.setUserId(results.getInt("user_id"));
        person.setUserName(results.getString("user_name"));
        person.setUserLogin(results.getString("user_login"));
        person.setUserPass(results.getString("user_password"));
        person.setAdmin(results.getBoolean("is_admin"));
        return person;
    }
}
