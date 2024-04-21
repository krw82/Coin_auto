
package com.example.demo.BinanceService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.MarketApiService;
import com.example.demo.BinanceService.candle.Vo.CandleVo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    // - Kline open time: 캔들 시작 시간
    // - Open price: 시가
    // - High price: 고가
    // - Low price: 저가
    // - Close price: 종가
    // - Volume: 거래량
    // - Kline Close time: 캔들 종료 시간
    // - Quote asset volume: 거래 대상 자산의 거래량
    // - Number of trades: 거래 횟수
    // - Taker buy base asset volume: 매수 주체의 거래량
    // - Taker buy quote asset volume: 매수 주체의 거래 대상 자산 거래량
    // - Unused field, ignore.: 사용되지 않는 필드, 무시
}