package ru.kpfu.itis.fazulzyanov.homework5.services.impl;

import ru.kpfu.itis.fazulzyanov.homework5.dao.UserDao;
import ru.kpfu.itis.fazulzyanov.homework5.dao.impl.UserDaoImpl;
import ru.kpfu.itis.fazulzyanov.homework4.dto.UserDto;
import ru.kpfu.itis.fazulzyanov.homework5.entity.User;
import ru.kpfu.itis.fazulzyanov.homework5.services.UserService;
import ru.kpfu.itis.fazulzyanov.homework5.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream()
                .map(user -> new UserDto(user.getName(), user.getLogin())).toList();
    }

    @Override
    public void save(String name, String lastname, String login, String password) {
        password = PasswordUtil.encrypt(password);
        // default value
        Integer id = 1;
        userDao.save(new User(id, name, lastname, login, password));
    }

    @Override
    public boolean verifyUser(String login, String password) {
        try {
            User user = userDao.getByLogin(login);
            return user.getPassword().equals(PasswordUtil.encrypt(password));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
