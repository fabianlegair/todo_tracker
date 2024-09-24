package com.todotracker.backend.model;

public class Person {

    private int userId;
    private String userName;
    private String userLogin;
    private String userPass;

    public Person(int userId, String userName, String userLogin, String userPass) {
        this.userId = userId;
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPass = userPass;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public String toString() {
        return "Person{" + "id = " + userId + "\n" +
                "userName = " + userName + "\n" +
                "userLogin = " + userLogin + "\n" +
                "userPass = " + userPass + "\n";
    }
}
