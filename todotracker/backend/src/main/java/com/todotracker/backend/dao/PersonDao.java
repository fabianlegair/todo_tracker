package com.todotracker.backend.dao;
import com.todotracker.backend.model.Person;

import java.util.List;

public interface PersonDao {

    List<Person> getUsers();
    Person getUserById(int id);
    Person getUserByUserName(String userName);
    Person createUser(Person person);
    Person updateUser(Person person);
    int deleteUser(int id);
}
