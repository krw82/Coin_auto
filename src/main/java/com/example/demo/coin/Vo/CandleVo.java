package com.example.demo.coin.Vo;


public class CandleVo {
   
    private long klineOpenTime;                 // Kline open time (캔들 시작 시간)
    private Double openPrice;                   // Open price (시가)
    private double highPrice;                   // High price (고가)
    private double lowPrice;                    // Low price (저가)
    private double closePrice;                  // Close price (종가)
    private double volume;                      // Volume (거래량)
    private long klineCloseTime;                // Kline Close time (캔들 종료 시간)
    private double quoteAssetVolume;            // Quote asset volume (거래 대상 자산의 거래량)
    private int numberOfTrades;                 // Number of trades (거래 횟수)
    private double takerBuyBaseAssetVolume;     // Taker buy base asset volume (매수 주체의 거래량)
    private double takerBuyQuoteAssetVolume;    // Taker buy quote asset volume (매수 주체의 거래 대상 자산 거래량)
    private String unusedField;        // Unused field, ignore. (사용되지 않는 필드, 무시)
    public long getKlineOpenTime() {
        return klineOpenTime;
    }

    public void setKlineOpenTime(long klineOpenTime) {
        this.klineOpenTime = klineOpenTime;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public long getKlineCloseTime() {
        return klineCloseTime;
    }

    public void setKlineCloseTime(long klineCloseTime) {
        this.klineCloseTime = klineCloseTime;
    }

    public double getQuoteAssetVolume() {
        return quoteAssetVolume;
    }

    public void setQuoteAssetVolume(double quoteAssetVolume) {
        this.quoteAssetVolume = quoteAssetVolume;
    }

    public int getNumberOfTrades() {
        return numberOfTrades;
    }

    public void setNumberOfTrades(int numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }

    public double getTakerBuyBaseAssetVolume() {
        return takerBuyBaseAssetVolume;
    }

    public void setTakerBuyBaseAssetVolume(double takerBuyBaseAssetVolume) {
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
    }

    public double getTakerBuyQuoteAssetVolume() {
        return takerBuyQuoteAssetVolume;
    }

    public void setTakerBuyQuoteAssetVolume(double takerBuyQuoteAssetVolume) {
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

