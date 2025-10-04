package ru.kpfu.itis.fazulzyanov.homework5.dao;

import ru.kpfu.itis.fazulzyanov.homework5.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    void save(User user);

    User getById(Integer id);

    User getByLogin(String login);
}
