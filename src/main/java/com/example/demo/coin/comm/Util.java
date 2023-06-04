package com.example.demo.coin.comm;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import com.example.demo.coin.VO.CandleVo;
import com.google.gson.Gson;

@Component
public  class Util {
	private static Gson gson;
	public static  Gson getInstance() {
        if (gson == null) gson = new Gson();
        return gson;
    }

	
	
	
	
	

	public static List<CandleVo> JsonToVo(String json) throws IOException {
		ArrayList<CandleVo> list = new ArrayList<CandleVo>();
		
		JSONArray ja = new JSONArray(json);
		
		for (int i = 0; i < ja.length(); i++) {
			JSONArray item = ja.getJSONArray(i);
			CandleVo data = new CandleVo();
			
			data.setKlineOpenTime(item.getLong(0));
			data.setOpenPrice(item.getString(1));
			data.setHighPrice(item.getString(2));
			data.setLowPrice(item.getString(3));
			data.setClosePrice(item.getString(4));
			data.setVolume(item.getString(5));
			data.setKlineCloseTime(item.getLong(5));
			data.setQuoteAssetVolume(item.getString(5));
			data.setNumberOfTrades(item.getInt(5));
			data.setTakerBuyBaseAssetVolume(item.getString(5));
			data.setTakerBuyQuoteAssetVolume(item.getString(5));
			data.setUnusedField(item.getString(5));
	
			list.add(data);
		}
		Collections.sort(list, (o1, o2) -> compareByCandleDateTime(o1, o2));
		return list;
	
	}

	

	
 // Comparator 인터페이스를 구현하는 메소드를 정의
 
	public static int compareByCandleDateTime(CandleVo o1, CandleVo o2) {
	
	  // date1이 date2보다 최신이면 음수, 같으면 0, 오래되면 양수를 반환
	  return Long.compare(o2.getKlineOpenTime(), o1.getKlineOpenTime());
	}

}
