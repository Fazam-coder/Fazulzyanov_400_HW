package ru.kpfu.itis.fazulzyanov.homework4.dto;

public class UserDto {
    private String name;
    private String login;

    public UserDto(String name, String login) {
        this.name = name;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
