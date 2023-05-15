package com.example.demo.coin;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;


@Component
public class UpbitApi {
	
	@Bean
	public String getApi() throws IOException {
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
				  .url("https://api.upbit.com/v1/candles/minutes/15?market=KRW-BTC&count=200")
				  .get()
				  .addHeader("accept", "application/json")
				  .build();
		
		Response response = client.newCall(request).execute();
		
	
		 
		return response.body().string();
		
	}
	
	
	
	
	
	
	

}
