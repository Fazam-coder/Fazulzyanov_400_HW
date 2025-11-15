package ru.kpfu.itis.fazulzyanov.homework3;

import java.util.HashMap;
import java.util.Map;

public class LoginPasswordBase {
    private static Map<String, String> map = new HashMap<String, String>();

    public static Map<String, String> getMap() {
        return map;
    }

    public static void addElement(String login, String password) {
        map.put(login, password);
    }
}
