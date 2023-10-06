package com.example.demo.coin.comm;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.Vo.TickerVo;
import com.example.demo.kakao.KakaoAccessTokenResponse;
import com.google.gson.Gson;

@Component
public class Util {
	private static Gson gson;

	public Util() {
		gsonGetInstance();
	}

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
			data.setKlineCloseTime(item.getLong(6));
			data.setQuoteAssetVolume(Double.parseDouble(item.getString(7)));
			data.setNumberOfTrades(item.getInt(8));
			data.setTakerBuyBaseAssetVolume(Double.parseDouble(item.getString(9)));
			data.setTakerBuyQuoteAssetVolume(Double.parseDouble(item.getString(10)));
			data.setUnusedField(item.getString(11));

			list.add(data);
		}
		// list.sort(Comparator.comparingLong(CandleVo::getKlineOpenTime).reversed());
		return list;

	}

	public static List<TickerVo> TickerToVo(String json) throws IOException {
		ArrayList<TickerVo> list = new ArrayList<TickerVo>();

		JSONArray ja = new JSONArray(json);

		for (int i = 0; i < ja.length(); i++) {
			JSONObject item = ja.getJSONObject(i);

			TickerVo tickerVo = new TickerVo();

			tickerVo.setSymbol(item.getString("symbol"));
			tickerVo.setPriceChange(item.getString("priceChange"));
			tickerVo.setPriceChangePercent(item.getString("priceChangePercent"));
			tickerVo.setWeightedAvgPrice(item.getString("weightedAvgPrice"));
			tickerVo.setPrevClosePrice(item.getString("prevClosePrice"));
			tickerVo.setLastPrice(item.getString("lastPrice"));
			tickerVo.setLastQty(item.getString("lastQty"));
			tickerVo.setBidPrice(item.getString("bidPrice"));
			tickerVo.setBidQty(item.getString("bidQty"));
			tickerVo.setAskPrice(item.getString("askPrice"));
			tickerVo.setAskQty(item.getString("askQty"));
			tickerVo.setOpenPrice(item.getString("openPrice"));
			tickerVo.setHighPrice(item.getString("highPrice"));
			tickerVo.setLowPrice(item.getString("lowPrice"));
			tickerVo.setVolume(item.getString("volume"));
			tickerVo.setQuoteVolume(item.getString("quoteVolume"));
			tickerVo.setOpenTime(item.getLong("openTime"));
			tickerVo.setCloseTime(item.getLong("closeTime"));
			tickerVo.setFirstId(item.getLong("firstId"));
			tickerVo.setLastId(item.getLong("lastId"));
			tickerVo.setCount(item.getLong("count"));

			list.add(tickerVo);
		}
		// list.sort(Comparator.comparingLong(CandleVo::getKlineOpenTime).reversed());
		// return list;

		// Filter for symbols ending with "USDT"
		List<TickerVo> filteredList = list.stream()
				.filter(tickerVo -> tickerVo.getSymbol().endsWith("USDT"))
				.collect(Collectors.toList());

		// Sort in descending order by volume
		/*
		 * filteredList
		 * .sort((a, b) -> Double.compare(
		 * Double.parseDouble(b.getVolume()) *
		 * Double.parseDouble(b.getWeightedAvgPrice()),
		 * Double.parseDouble(a.getVolume()) *
		 * Double.parseDouble(a.getWeightedAvgPrice())));
		 */

		// Return only the top 10
		return filteredList;

	}

	public static KakaoAccessTokenResponse kakaoVo(String json) throws IOException {

		KakaoAccessTokenResponse result = gson.fromJson(json, KakaoAccessTokenResponse.class);

		// list.sort(Comparator.comparingLong(CandleVo::getKlineOpenTime).reversed());
		return result;

	}

}
