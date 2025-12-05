package ru.kpfu.itis.fazulzyanov.homework3.chatbot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ChatBotLogic {

    private static final String API_KEY = "API_KEY";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String EXCHANGE_URL = "https://open.er-api.com/v6/latest/USD";

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String processCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Пустая команда.";
        }

        input = input.trim();

        if (input.equals("/list")) {
            return """ 
                    Доступные команды:
                    /list — список команд
                    /weather <город> — погода
                    /exchange <код> — курс валюты к рублю
                    /quit — очистить чат
                    """;
        } else if (input.startsWith("/weather ")) {
            String city = input.substring(9).trim();
            if (city.isEmpty()) {
                return "Укажите город: /weather Kazan";
            }
            return getWeather(city);
        } else if (input.startsWith("/exchange ")) {
            String currency = input.substring(10).trim().toUpperCase();
            if (currency.isEmpty()) {
                return "Укажите код валюты: /exchange USD";
            }
            return getExchangeRate(currency);
        } else if (input.equals("/quit")) {
            return "/quit";
        } else {
            return "Неизвестная команда. Напишите /list.";
        }
    }

    private String getWeather(String city) {
        try {
            String url = WEATHER_URL + "?q=" + city + "?app_id=" + API_KEY;
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());

            if (resp.statusCode() == 200) {
                JsonNode root = jsonMapper.readTree(resp.body());
                String desc = root.path("weather").get(0).path("description").asText();
                double temp = root.path("main").path("temp").asDouble();
                return String.format("🌤 %s: %s, %.1f°C", city, desc, temp);
            } else {
                return "Город не найден";
            }
        } catch (IOException | InterruptedException e) {
            return "Ошибка при получении погоды";
        }
    }

    private String getExchangeRate(String currency) {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(EXCHANGE_URL))
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());

            if (resp.statusCode() == 200) {
                JsonNode root = jsonMapper.readTree(resp.body());
                JsonNode rates = root.path("rates");

                if (rates.has("RUB") && rates.has(currency)) {
                    double usdToRub = rates.path("RUB").asDouble();
                    double usdToCur = rates.path(currency).asDouble();
                    double rubPerCur = usdToRub / usdToCur;
                    return String.format("1 %s = %.4f RUB", currency, rubPerCur);
                } else {
                    return "Валюта не поддерживается: " + currency;
                }
            } else {
                return "Не удалось загрузить курсы валют";
            }
        } catch (IOException | InterruptedException e) {
            return "Ошибка сети при получении курса";
        }
    }
}