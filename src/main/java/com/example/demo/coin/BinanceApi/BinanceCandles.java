package com.example.demo.coin.BinanceApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Component;



@Component
public class BinanceCandles {

    public String getCandles(String symbol, String interval, int limit) throws IOException {
        // Create the URL for the candles endpoint.
        URL url = new URL("https://api.binance.com/api/v3/uiKlines?symbol=" + symbol + "&interval=" + interval + "&limit=" + limit);

        // Create a new HTTP connection to the endpoint.
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the HTTP request method to GET.
        connection.setRequestMethod("GET");

        // Set the HTTP request headers.
        //connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setRequestProperty("Content-Type", "application/json");

        // Execute the HTTP request.
        int responseCode = connection.getResponseCode();

        // Check the HTTP response code.
        if (responseCode != 200) {
            throw new IOException("HTTP response code: " + responseCode);
        }

        // Get the HTTP response body.
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        return jsonBuilder.toString();

    }  
}
