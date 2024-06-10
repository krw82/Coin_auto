package com.coin.ta;

public interface MarketApiService {
    public Object getMarcketCandle(String symbol, String interval, String limit);

    public Object getMarcketTicker();

}
