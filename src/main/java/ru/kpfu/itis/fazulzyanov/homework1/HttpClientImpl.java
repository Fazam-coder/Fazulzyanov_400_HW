package ru.kpfu.itis.fazulzyanov.homework1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClientImpl implements HttpClient {
    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        HttpURLConnection connection;
        try {
            URL getUrl = new URL(url);
            connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            StringBuilder resultParams =  new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                resultParams.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            if (!resultParams.isEmpty()) {
                resultParams.deleteCharAt(resultParams.length() - 1);
            }
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            dos.writeBytes(resultParams.toString());
            dos.flush();
            dos.close();

            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readResponse(connection);
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> data) {
        HttpURLConnection connection;
        try {
            URL postUrl = new URL(url);
            connection = (HttpURLConnection) postUrl.openConnection();
            connection.setRequestMethod("POST");
            workWithConnection(headers, data, connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readResponse(connection);
    }

    @Override
    public String put(String url, Map<String, String> headers, Map<String, String> data) {
        HttpURLConnection connection;
        try {
            URL putUrl = new URL(url);
            connection = (HttpURLConnection) putUrl.openConnection();
            connection.setRequestMethod("PUT");
            workWithConnection(headers, data, connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "putted successfully";
    }

    private void workWithConnection(Map<String, String> headers, Map<String, String> data, HttpURLConnection connection) throws IOException {
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setDoOutput(true);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        String jsonData = new ObjectMapper().writeValueAsString(data);
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        connection.disconnect();
    }

    @Override
    public String delete(String url, Map<String, String> headers, Map<String, String> data) {
        HttpURLConnection connection;
        try {
            URL deleteUrl = new URL(url);
            connection = (HttpURLConnection) deleteUrl.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "deleted successfully";
    }

    private static String readResponse(HttpURLConnection connection) {
        if (connection == null) {
            return null;
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
