package com.example.demo.coin.comm;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import com.example.demo.coin.Vo.CandleVo;
import com.google.gson.Gson;

@Component
public class Util {
	private static Gson gson;

	public static Gson gsonGetInstance() {
		if (gson == null)
			gson = new Gson();
		return gson;
	}

	public static List<CandleVo> JsonToVo(String json) throws IOException {
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

}
