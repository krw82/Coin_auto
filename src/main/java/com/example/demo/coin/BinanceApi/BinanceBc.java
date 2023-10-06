package com.example.demo.coin.BinanceApi;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.coin.comm.Util;

@Component
public class BinanceBc {

    @Autowired
    Util util;

    public String getCandle(String symbol, String interval, int limit) {
        URL url;
        String result = "";
        try {
            url = new URL("https://api.binance.com/api/v3/uiKlines?symbol=" + symbol + "&interval=" + interval
                    + "&limit=" + limit);
            result = BinanceInterface.getInterface(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public String getTicker() {
        URL url;
        String result = "";
        try {
            url = new URL("https://api.binance.com/api/v3/ticker/24hr");

            result = BinanceInterface.getInterface(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

}
