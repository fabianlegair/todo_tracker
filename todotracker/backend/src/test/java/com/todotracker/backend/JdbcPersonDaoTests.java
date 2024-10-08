package com.todotracker.backend;

import com.todotracker.backend.dao.JdbcPersonDao;
import com.todotracker.backend.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcPersonDaoTests extends BaseDaoTests {

    private JdbcPersonDao dao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcPersonDao(jdbcTemplate);
    }

    @Test
    public void getUser_gets_users() {
        List<Person> persons = dao.getUsers();
        Assert.assertEquals(2, persons.size());
    }
}
