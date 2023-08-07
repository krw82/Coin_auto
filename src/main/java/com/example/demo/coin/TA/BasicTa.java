package com.example.demo.coin.TA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.coin.Vo.CandleVo;



@Component
public class BasicTa {
    
    List<CandleVo> list;
    double average_volume;
    public BasicTa(List<CandleVo> list_i){
        
        this.list=list_i;  
    }

    public void setBasic(){

        double sum=0;
        for(int i=0; i<14; i++){
            sum+=list.get(i).getVolume();
        }
        average_volume=sum/14;
        System.out.println(list.get(0).getVolume());
        

    }
    
    //이평선
    public double Ma(int leng,int close){
       return sma(leng,close);
    }
    
    
     // RSI 계산
    /* public double rsi(int leng,int close) {

                        
       /*  double u= Math.max(list.get(close).getClosePrice()-list.get(close+1).getClosePrice(),0.0);
        double d= Math.max(list.get(close+1).getClosePrice()-list.get(close).getClosePrice(),0.0);   

        Logic <Integer> logic = (item1) ->   Math.max(list.get(item1+1).getClosePrice()-list.get(item1).getClosePrice(),0.0); 
        double test = sma(close,logic, leng);

        double rs = rma(leng,close,u)/rma(leng, close, d);   
        //double res = 100 - 100 / (1 + rs);
        return test;*/
        
       // RSI를 구하기 위한 공식 적용
       /* 
       ArrayList<Double> delta = new ArrayList<>(); // 가격 변화량을 저장할 리스트
       ArrayList<Double> up = new ArrayList<>(); // 상승분을 저장할 리스트
       ArrayList<Double> down = new ArrayList<>(); // 하락분을 저장할 리스트
       ArrayList<Double> AU = new ArrayList<>(); // 상승분의 평균값을 저장할 리스트
       ArrayList<Double> AD = new ArrayList<>(); // 하락분의 평균값을 저장할 리스트
       ArrayList<Double> RS = new ArrayList<>(); // 상대강도를 저장할 리스트
       ArrayList<Double> RSI = new ArrayList<>(); // RSI를 저장할 리스트
        
       for (int i = list.size()-1 ; i >= 0; i--) {
            int j= list.size()-i-1;

           if (i == list.size()-1) { // 첫 번째 데이터는 변화량이 없으므로 0으로 설정
               delta.add(0.0);
               up.add(0.0);
               down.add(0.0);
           } else { // 두 번째 데이터부터는 전일 가격과의 차이를 구함
               delta.add( list.get(i).getClosePrice() - list.get(i +1).getClosePrice());
               if (delta.get(j) > 0) { // 상승한 경우
                   up.add(delta.get(j));
                   down.add(0.0);
               } else { // 하락한 경우
                   up.add(0.0);
                   down.add(-delta.get(j));
               }
           }

           if (i > list.size()-14) { // 첫 번째 AU/AD 계산은 14일 동안의 상승/하락분의 합 / 14로 구함
               if (i == list.size()-1) {
                   AU.add(up.get(j));
                   AD.add(down.get(j));
               } else {
                   AU.add(AU.get(j-1) * i / 14 + up.get(j) / 14);
                   AD.add(AD.get(j-1) * i / 14 + down.get(j) / 14);
               }
           } else { // 이후의 AU/AD 계산은 [13 * 이전 AU + 현재 상승분] / 14, [13 * 이전 AD + 현재 하락분] / 14로 구함
               AU.add(AU.get(j-1 ) * 0.8667 + up.get(j) * 0.1333);
               AD.add(AD.get(j-1) * 0.8667 + down.get(j) * 0.1333);
           }

           // RS와 RSI 계산
           RS.add(AU.get(j) / AD.get(j));
           RSI.add(RS.get(j) / (1 + RS.get(j)) * 100);
          
       }

       return RSI.get();
       
        
        
    } */

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

    

           



    
    
}
