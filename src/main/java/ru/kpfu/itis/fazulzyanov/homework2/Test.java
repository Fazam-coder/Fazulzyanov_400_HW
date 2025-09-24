package ru.kpfu.itis.fazulzyanov.homework2;

import ru.kpfu.itis.fazulzyanov.homework1.HttpClientImpl;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        HttpClientImpl httpClient = new HttpClientImpl();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Map<String, String> data = new HashMap<>();
        data.put("id", "6347569874");
        data.put("name", "gdrghuhdugh");
        data.put("email", "amirrf06@gmail.com");
        data.put("gender", "male");
        data.put("status", "active");
        System.out.println(httpClient.post("http://localhost:8080/hello", headers, data));
    }
}
