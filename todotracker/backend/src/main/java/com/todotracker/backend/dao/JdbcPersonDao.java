package com.todotracker.backend.dao;

import com.todotracker.backend.exception.DaoException;
import com.todotracker.backend.model.Person;
import org.springframework.dao.DataIntegrityViolationException;
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
        Person person = new Person();
        String sql = "select * from person " +
                "where user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                person = mapRowToPerson(results);
            } else return null;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return person;
    }

    @Override
    public Person getUserByUserName(String userName) {
        Person person = new Person();
        String sql = "select * from person " +
                "where user_name ilike ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
            if (results.next()) {
                person = mapRowToPerson(results);
            } else return null;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return person;
    }

    @Override
    public Person createUser(Person person) {
        Person newPerson = null;
        String sql = "insert into person (user_name, user_login, user_password, is_admin) " +
                "values (?, ?, ?, ?) returning user_id;";

        try {
            int newPersonId = jdbcTemplate.queryForObject(sql, int.class, person.getUserName(),
                    person.getUserLogin(), person.getUserPass(), person.getAdmin());
            newPerson = getUserById(newPersonId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return newPerson;
    }

    @Override
    public Person updateUser(Person person) {
        Person updatedPerson = null;
        String sql = "update person " +
                "set user_name = ?, user_login = ?, user_password = ?, is_admin = ? " +
                "where user_id = ?;";

        try {
            jdbcTemplate.update(sql, person.getUserName(), person.getUserLogin(),
                    person.getUserPass(), person.getAdmin(), person.getUserId());
            updatedPerson = getUserById(person.getUserId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return updatedPerson;
    }

    @Override
    public int deleteUser(int id) {
        int numberOfRows = 0;
        String sql = "delete from person where user_id = ?;";

        try {
            numberOfRows = jdbcTemplate.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return numberOfRows;
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
