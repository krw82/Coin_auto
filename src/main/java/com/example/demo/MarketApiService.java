package com.example.demo;

public interface MarketApiService {
    public String getMarcketCandle(String symbol, String interval, String limit);

    public String getMarcketTicker();

}