
package com.coin.ta.BinanceService;

import org.springframework.stereotype.Component;

import com.coin.ta.MarketApiService;
import com.coin.ta.Util.WebClientService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BinanceApi implements MarketApiService {
    private final WebClientService webClientService;

    @Override
    public String getMarcketCandle(String symbol, String interval, String limit) {

        String urlString = "https://api.binance.com/api/v3/klines?symbol=" + symbol + "&interval=" + interval
                + "&limit=" + limit;
        try {
            return webClientService.ApiGet(urlString, String.class)
                    .block();
        } catch (Exception e) {
            // 오류 처리 로직 추가
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public String getMarcketTicker() {
        String urlString = "https://api.binance.com/api/v3/ticker/24hr";
        try {
            return webClientService.ApiGet(urlString, String.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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