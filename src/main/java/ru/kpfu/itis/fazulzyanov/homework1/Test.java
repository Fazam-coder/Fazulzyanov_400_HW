package ru.kpfu.itis.fazulzyanov.homework1;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        HttpClientImpl httpClient = new HttpClientImpl();
        Map<String, String> headers1 = new HashMap<>();
        headers1.put("Content-Type", "application/json");
        headers1.put("Authorization", "Bearer");
        Map<String, String> body = new HashMap<>();
        body.put("id", "9999999");
        body.put("name", "kokichi");
        body.put("email", "imliar@ronpa.com");
        body.put("gender", "male");
        body.put("status", "active");
        System.out.println(httpClient.post1("https://gorest.co.in/public/v2/users", headers1, body));
    }
}
