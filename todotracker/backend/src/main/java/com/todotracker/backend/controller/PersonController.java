package com.todotracker.backend.controller;

import com.todotracker.backend.dao.JdbcPersonDao;
import com.todotracker.backend.dao.PersonDao;
import com.todotracker.backend.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonDao personDao;

    public PersonController(JdbcTemplate jdbcTemplate) {
        this.personDao = new JdbcPersonDao(jdbcTemplate);
    }

    @RequestMapping(path = "/person", method = RequestMethod.GET)
    public List<Person> getPersons() {
        return personDao.getUsers();
    }

    @RequestMapping(path = "/person/search/{id}", method = RequestMethod.GET)
    public Person getPersonById(@PathVariable int id) {
        return personDao.getUserById(id);
    }

    @RequestMapping(path = "/person/search/", method = RequestMethod.GET)
    public Person getPersonByUserName(@RequestParam String userName) {
        if (userName != null) {
            return personDao.getUserByUserName(userName);
        } else return null;
    }

    @RequestMapping(path = "/person", method = RequestMethod.POST)
    public Person createPerson(@RequestBody Person person) {
        return personDao.createUser(person);
    }

    @RequestMapping(path = "/person", method = RequestMethod.PUT)
    public Person updatePerson(@RequestBody Person person) {
        return personDao.updateUser(person);
    }

    @RequestMapping(path = "/person/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable int id) {
        personDao.deleteUser(id);
    }
}
