package com.example.demo.coin.VO;

public class CandleVo {
   
    private long klineOpenTime;                 // Kline open time (캔들 시작 시간)
    private String openPrice;                   // Open price (시가)
    private String highPrice;                   // High price (고가)
    private String lowPrice;                    // Low price (저가)
    private String closePrice;                  // Close price (종가)
    private String volume;                      // Volume (거래량)
    private long klineCloseTime;                // Kline Close time (캔들 종료 시간)
    private String quoteAssetVolume;            // Quote asset volume (거래 대상 자산의 거래량)
    private int numberOfTrades;                 // Number of trades (거래 횟수)
    private String takerBuyBaseAssetVolume;     // Taker buy base asset volume (매수 주체의 거래량)
    private String takerBuyQuoteAssetVolume;    // Taker buy quote asset volume (매수 주체의 거래 대상 자산 거래량)
    private String unusedField;        // Unused field, ignore. (사용되지 않는 필드, 무시)
    public long getKlineOpenTime() {
        return klineOpenTime;
    }

    public void setKlineOpenTime(long klineOpenTime) {
        this.klineOpenTime = klineOpenTime;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public long getKlineCloseTime() {
        return klineCloseTime;
    }

    public void setKlineCloseTime(long klineCloseTime) {
        this.klineCloseTime = klineCloseTime;
    }

    public String getQuoteAssetVolume() {
        return quoteAssetVolume;
    }

    public void setQuoteAssetVolume(String quoteAssetVolume) {
        this.quoteAssetVolume = quoteAssetVolume;
    }

    public int getNumberOfTrades() {
        return numberOfTrades;
    }

    public void setNumberOfTrades(int numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }

    public String getTakerBuyBaseAssetVolume() {
        return takerBuyBaseAssetVolume;
    }

    public void setTakerBuyBaseAssetVolume(String takerBuyBaseAssetVolume) {
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
    }

    public String getTakerBuyQuoteAssetVolume() {
        return takerBuyQuoteAssetVolume;
    }

    public void setTakerBuyQuoteAssetVolume(String takerBuyQuoteAssetVolume) {
        this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
    }

    public String getUnusedField() {
        return unusedField;
    }

    public void setUnusedField(String unusedField) {
        this.unusedField = unusedField;
    }


        // 생성자, Getter, Setter, toString 등의 필요한 메소드를 구현합니다.
    
        // 주석
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

