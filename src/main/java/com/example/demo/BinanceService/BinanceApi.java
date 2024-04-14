
package com.example.demo.BinanceService;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.MarketApiService;

@Component
public class BinanceApi implements MarketApiService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String getMarcketCandle(String symbol, String interval, String limit) {

        String result = "";
        try {
            String urlString = "https://api.binance.com/api/v3/klines?symbol=" + symbol + "&interval=" + interval
                    + "&limit=" + limit;
            URI uri = new URI(urlString);
            result = restTemplate.getForObject(uri, String.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public String getMarcketTicker() {
        String result = "";
        try {
            String urlString = "https://api.binance.com/api/v3/ticker/24hr";
            URI uri = new URI(urlString);

            result = restTemplate.getForObject(uri, String.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

}
