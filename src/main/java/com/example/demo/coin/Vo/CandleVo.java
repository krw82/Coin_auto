package com.example.demo.coin.Vo;

public class CandleVo { 
	
	String market;
	String candle_date_time_utc;
	String candle_date_time_kst;
	Double opening_price;
	Double high_price;
	Double low_price;
	Double trade_price;
	Double candle_acc_trade_volumel;
	int unit;
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getCandle_date_time_utc() {
		return candle_date_time_utc;
	}
	public void setCandle_date_time_utc(String candle_date_time_utc) {
		this.candle_date_time_utc = candle_date_time_utc;
	}
	public String getcandle_date_time_kst() {
		return candle_date_time_kst;
	}
	public void setcandle_date_time_kst(String candle_date_time_kst) {
		this.candle_date_time_kst = candle_date_time_kst;
	}
	public Double getOpening_price() {
		return opening_price;
	}
	public void setOpening_price(Double opening_price) {
		this.opening_price = opening_price;
	}
	public Double getHigh_price() {
		return high_price;
	}
	public void setHigh_price(Double high_price) {
		this.high_price = high_price;
	}
	public Double getLow_price() {
		return low_price;
	}
	public void setLow_price(Double low_price) {
		this.low_price = low_price;
	}
	public Double getTrade_price() {
		return trade_price;
	}
	public void setTrade_price(Double trade_price) {
		this.trade_price = trade_price;
	}
	public Double getCandle_acc_trade_volumel() {
		return candle_acc_trade_volumel;
	}
	public void setCandle_acc_trade_volumel(Double candle_acc_trade_volumel) {
		this.candle_acc_trade_volumel = candle_acc_trade_volumel;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}

}
/*
market	마켓명	String
candle_date_time_utc	캔들 기준 시각(UTC 기준)
포맷: yyyy-MM-dd'T'HH:mm:ss	String
candle_date_time_kst	캔들 기준 시각(KST 기준)
포맷: yyyy-MM-dd'T'HH:mm:ss	String
opening_price	시가	Double
high_price	고가	Double
low_price	저가	Double
trade_price	종가	Double
timestamp	해당 캔들에서 마지막 틱이 저장된 시각	Long
candle_acc_trade_price	누적 거래 금액	Double
candle_acc_trade_volume	누적 거래량	Double
unit	분 단위(유닛)	Integer
*/
