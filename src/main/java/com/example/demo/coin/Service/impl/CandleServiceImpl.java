package com.example.demo.coin.Service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.python.antlr.PythonParser.decorator_return;
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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        List<CandleVo> CandelList = null;
        try {
            List<TickerVo> tickerList = TickerServic.selectTickerVos();
            for (TickerVo item : tickerList) {
                if (false) {
                    // 여기에 200개가 안될경우 del insert ? merge?
                    Util.PriceToVo(binanceBc.getCandle(item.getSymbol(), interval, 200));
                } else {
                    CandelList = Util.PriceToVo(binanceBc.getCandle(item.getSymbol(), interval, 1));
                }
                clacList = this.calcCandle(CandelList, item.getSymbol(), interval); // 산출
                this.insertClac(clacList);// 저장
            }
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
    public List<TickerAnalysisVo> selectCalcList(Map<String, Object> params) {
        return CandleMapper.selectCalcList(params);
    }

    @Override
    public Map<String, Object> selectCoinDetail(Map<String, Object> params) {

        throw new UnsupportedOperationException("Unimplemented method 'selectCoinDetail'");
    }

}