package ru.kpfu.itis.fazulzyanov.homework1;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        HttpClientImpl httpClient = new HttpClientImpl();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer 5dbb93bd95119ff8607b0adf250941a78475b5a76f8c81e127ce1d0a91806f95");
        Map<String, String> data = new HashMap<>();
        data.put("id", "6347569874");
        data.put("name", "gdrghuhdugh");
        data.put("email", "amirrf06@gmail.com");
        data.put("gender", "male");
        data.put("status", "active");
        System.out.println(httpClient.post("https://gorest.co.in/public/v2/users", headers, data));
    }
}
