package com.example.demo.coin.comm;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.Vo.TickerVo;
import com.google.gson.Gson;

@Component
public class Util {
	private static Gson gson;

	public static Gson gsonGetInstance() {
		if (gson == null)
			gson = new Gson();
		return gson;
	}

	public static List<CandleVo> PriceToVo(String json) throws IOException {
		ArrayList<CandleVo> list = new ArrayList<CandleVo>();

		JSONArray ja = new JSONArray(json);

		for (int i = 0; i < ja.length(); i++) {
			JSONArray item = ja.getJSONArray(i);
			CandleVo data = new CandleVo();

			data.setKlineOpenTime(item.getLong(0));
			data.setOpenPrice(Double.parseDouble(item.getString(1)));
			data.setHighPrice(Double.parseDouble(item.getString(2)));
			data.setLowPrice(Double.parseDouble(item.getString(3)));
			data.setClosePrice(Double.parseDouble(item.getString(4)));
			data.setVolume(Double.parseDouble(item.getString(5)));
			data.setKlineCloseTime(item.getLong(5));
			data.setQuoteAssetVolume(Double.parseDouble(item.getString(5)));
			data.setNumberOfTrades(item.getInt(5));
			data.setTakerBuyBaseAssetVolume(Double.parseDouble(item.getString(5)));
			data.setTakerBuyQuoteAssetVolume(Double.parseDouble(item.getString(5)));
			data.setUnusedField(item.getString(5));

			list.add(data);
		}
		// list.sort(Comparator.comparingLong(CandleVo::getKlineOpenTime).reversed());
		return list;

	}

	public static List<TickerVo> TickerToVo(String json) throws IOException {
		ArrayList<TickerVo> list = new ArrayList<TickerVo>();

		JSONArray ja = new JSONArray(json);

		for (int i = 0; i < ja.length(); i++) {
			JSONArray item = ja.getJSONArray(i);
			TickerVo tickerVo = new TickerVo();

			tickerVo.setSymbol(item.getString(0));
			tickerVo.setPriceChange(item.getString(1));
			tickerVo.setPriceChangePercent(item.getString(2));
			tickerVo.setWeightedAvgPrice(item.getString(3));
			tickerVo.setPrevClosePrice(item.getString(4));
			tickerVo.setLastPrice(item.getString(5));
			tickerVo.setLastQty(item.getString(6));
			tickerVo.setBidPrice(item.getString(7));
			tickerVo.setBidQty(item.getString(8));
			tickerVo.setAskPrice(item.getString(9));
			tickerVo.setAskQty(item.getString(10));
			tickerVo.setOpenPrice(item.getString(11));
			tickerVo.setHighPrice(item.getString(12));
			tickerVo.setLowPrice(item.getString(13));
			tickerVo.setVolume(item.getString(14));
			tickerVo.setQuoteVolume(item.getString(15));
			tickerVo.setOpenTime(item.getLong(16));
			tickerVo.setCloseTime(item.getLong(17));
			tickerVo.setFirstId(item.getLong(18));
			tickerVo.setLastId(item.getLong(19));
			tickerVo.setCount(item.getLong(20));

			list.add(tickerVo);
		}
		// list.sort(Comparator.comparingLong(CandleVo::getKlineOpenTime).reversed());
		return list;

	}

}
