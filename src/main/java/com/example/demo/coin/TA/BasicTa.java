package com.example.demo.coin.TA;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.comm.Logic;


@Component
public class BasicTa {
    
    List<CandleVo> list;
    public BasicTa(List<CandleVo> list_i){
        
        this.list=list_i;

    }
    
    //이평선
    public double Ma(int leng,int close){
       return sma(leng,close);
    }
    
    
    // RSI 계산
    public double rsi(int leng,int close) {

                        
        double u= Math.max(list.get(close).getClosePrice()-list.get(close+1).getClosePrice(),0.0);
        double d= Math.max(list.get(close+1).getClosePrice()-list.get(close).getClosePrice(),0.0);   

        Logic <Integer> logic = (item1) ->   Math.max(list.get(item1+1).getClosePrice()-list.get(item1).getClosePrice(),0.0); 
        double test = sma(close,logic, leng);

        double rs = rma(leng,close,u)/rma(leng, close, d);   
        //double res = 100 - 100 / (1 + rs);
        return test;
        
        
    }

    public double rma(int leng,int close,double price){

       double alpha = 1/leng;
       double sum = 0.0;
       double src = price;
       sum = alpha * src + (1 - alpha) * sma(leng,close);
       return sum;


    }

    //sma
    public double sma (int leng,int close){
       return 0;
    }

     public static <T, R> Double sma(int close, Logic<T> logic,int leng) {

        double Sum =0.0;
        for(int i=close; i<close+leng; i++){
            Sum += logic.process(i);
        }
        return Sum/leng;
    }

           



    
    
}
