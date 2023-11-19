package com.example.demo.coin.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.coin.BinanceApi.BinanceBc;
import com.example.demo.coin.Mapper.CandleMapper;
import com.example.demo.coin.Service.CandleService;
import com.example.demo.coin.Service.TickerServic;
import com.example.demo.coin.TA.Bridge;
import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.Vo.TickerAnalysisVo;
import com.example.demo.coin.Vo.TickerVo;
import com.example.demo.coin.comm.Util;
import com.example.demo.kakao.kakaoApi;

@Service
public class CandleServiceImpl implements CandleService {

    @Autowired
    BinanceBc binanceBc;

    @Autowired
    TickerServic TickerServic;

    @Autowired
    CandleMapper CandleMapper;

    @Autowired
    kakaoApi KakaoApi;

    @Override
    public synchronized List<List<CandleVo>> getCandel(String interval) {
        List<List<CandleVo>> list = new ArrayList<>();
        List<TickerAnalysisVo> clacList = new ArrayList<>();
        try {
            List<TickerVo> tickerList = TickerServic.selectTickerVos();
            for (TickerVo item : tickerList) {
                List<CandleVo> CandelList = Util.PriceToVo(binanceBc.getCandle(item.getSymbol(), interval, 200));
                clacList = this.calcCandle(CandelList, item.getSymbol(), interval); // 산출
                this.insertClac(clacList);// 저장

            }
            // List<TickerAnalysisVo> calcLsit = CandleMapper.selectcalc();
            // if (clacList.size() != 0) {
            // KakaoApi.sendMessage(kakaoApi.accecs_token, calcLsit);
            // }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<TickerAnalysisVo> calcCandle(List<CandleVo> parma, String symbol, String interval) {
        return Bridge.PythonTa(parma, symbol, interval);
    }

    @Override
    public void insertClac(List<TickerAnalysisVo> param) {

        CandleMapper.insertCalc(param.get(0));

    }

    @Override
    public List<TickerAnalysisVo> selectCalcList() {
        return CandleMapper.selectCalcList();
    }

}