package ru.kpfu.itis.fazulzyanov.homework5.services;

import ru.kpfu.itis.fazulzyanov.homework4.dto.UserDto;
import ru.kpfu.itis.fazulzyanov.homework5.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    void save(String name, String lastname, String login, String password);

    boolean verifyUser(String login, String password);
}
