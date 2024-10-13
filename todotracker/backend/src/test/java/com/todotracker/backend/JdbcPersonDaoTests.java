package com.todotracker.backend;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.todotracker.backend.dao.JdbcPersonDao;
import com.todotracker.backend.model.Person;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcPersonDaoTests extends BaseDaoTests {

    private JdbcPersonDao dao;
    private final Person fabianPerson = new Person("Fabian LeGair",
            "flegair", "fabian1127", true);
    private final Person updateRandy = new Person(2, "Randy LeGair",
            "rlegair2", "luca1223");

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

    @Test
    public void getUserById_with_valid_id_returns_correct_user() {
        Person person = dao.getUserById(1);
        Assert.assertEquals("alegair", person.getUserLogin());
    }

    @Test
    public void getUserById_with_invalid_id_returns_null() {
        Person person = dao.getUserById(3);
        Assert.assertNull(person);
    }

    @Test
    public void getUserByUserName_with_valid_name_returns_user() {
        Person person = dao.getUserByUserName("Randy LeGair");
        Assert.assertEquals(2, person.getUserId());
    }

    @Test
    public void getUserByUserName_with_invalid_name_returns_null() {
        Person person = dao.getUserByUserName("Fabian LeGair");
        Assert.assertNull(person);
    }

    @Test
    public void createUser_creates_user() {
        Person person = dao.createUser(fabianPerson);
        Assert.assertEquals(3, person.getUserId());
    }

    @Test
    public void updateUser_updates_user() {
        Person person = dao.updateUser(updateRandy);
        Assert.assertEquals("luca1223", person.getUserPass());
    }

    @Test
    public void deleteUser_deletes_user() {
        int numberOfRows = dao.deleteUser(2);
        Assert.assertEquals(1, numberOfRows);
        Assert.assertNull(dao.getUserById(2));
    }
}
