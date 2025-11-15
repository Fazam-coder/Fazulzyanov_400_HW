package ru.kpfu.itis.fazulzyanov.homework5.entity;

public class User {
    private Integer id;
    private String name;
    private String lastname;
    private String login;
    private String password;
    private String imagePath;

    public User() {}

    public User(Integer id, String name, String lastname, String login, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
