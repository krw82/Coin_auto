package com.example.demo.coin.Bc;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.demo.coin.UpbitApi;
import com.example.demo.coin.Vo.CandleVo;

@Component
public  class Technical {
	private static List<CandleVo> list;
	
    public Technical(List<CandleVo> listt) {
    	
        list=listt;
    }
	public boolean Volume(CandleVo candle) { //이전 열개봉의 평균을 내서 배 이상일경우 true
		if(candle.getCandle_acc_trade_volumel() > candle.getCandle_acc_trade_volumel() *3) {
			return true;
		}
		return false;
		
	}
	  public static double calculateRSI( int n) {
	        double[] gain = new double[n];
	        double[] loss = new double[n];

	        for (int i = 1; i < n; i++) {
	            double diff = list.get(i).getTrade_price() - list.get(i-1).getTrade_price();
	            if (diff > 0) {
	                gain[i] = diff;
	            } else {
	                loss[i] = -diff;
	            }
	        }

	        double avgGain = 0;
	        double avgLoss = 0;

	        for (int i = 1; i < n; i++) {
	            avgGain += gain[i];
	            avgLoss += loss[i];
	        }

	        avgGain /= n - 1;
	        avgLoss /= n - 1;

	        double rs = avgGain / avgLoss;
	        double rsi = 100 - (100 / (1 + rs));

	        return rsi;
	    }
	
	
	public boolean Ma() {// 캔들터치가 있어야함.
		return true;
	}

	public boolean CandleTouch() {
		
		return true;
	}
	
	
	
	
	

}
