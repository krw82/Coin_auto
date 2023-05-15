package com.example.demo.coin.Bc;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.demo.coin.Vo.CandleVo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.Response;


public  class Util {
	private static Gson gson;
	public static  Gson getInstance() {
        if (gson == null) gson = new Gson();
        return gson;
    }
	
	
	

	public static List<CandleVo> JsonToVo(String json) throws IOException {
		ArrayList<CandleVo> list = new ArrayList<CandleVo>();
		System.out.println();
		JSONArray ja = new JSONArray(json);
		
		for (int i = 0; i < ja.length(); i++){
			JSONObject order = ja.getJSONObject(i);
			Gson gson = new Gson ( );
			CandleVo data = gson.fromJson(order.toString (),CandleVo.class);

			list.add (data);
		}
		Collections.sort(list, (o1, o2) -> Util.compareByCandleDateTime(o1, o2));
		return list;
		
	}
	
	// Comparator 인터페이스를 구현하는 메소드를 정의
	public static int compareByCandleDateTime(CandleVo o1, CandleVo o2) {
	  // candle_date_time_utc을 Date 타입으로 변환
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	  Date date1 = null;
	  Date date2 = null;
	  try {
	    date1 = sdf.parse(o1.getCandle_date_time_utc());
	    date2 = sdf.parse(o2.getCandle_date_time_utc());
	  } catch (ParseException e) {
	    e.printStackTrace();
	  }
	  // date1이 date2보다 최신이면 음수, 같으면 0, 오래되면 양수를 반환
	  return date2.compareTo(date1);
	}

}
