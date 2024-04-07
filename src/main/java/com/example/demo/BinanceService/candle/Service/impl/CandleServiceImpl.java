package com.example.demo.BinanceService.candle.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.BinanceService.BinanceApi;
import com.example.demo.BinanceService.candle.Service.CandleService;
import com.example.demo.BinanceService.candle.Vo.AnalysisVo;
import com.example.demo.BinanceService.candle.Vo.CandleVo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CandleServiceImpl implements CandleService {

    @Autowired
    BinanceApi binanceApi;

    @Override
    public List<CandleVo> getApiCandle(String symbol, String interval) {
        List<CandleVo> voList = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            voList = objectMapper.readValue(binanceApi.getMarcketCandle(symbol, interval, API_LIMIT),
                    new TypeReference<List<CandleVo>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return voList;
    }

    @Override
    public List<AnalysisVo> calcCandle(List<CandleVo> param, String param2, String interval) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcCandle'");
    }

    @Override
    public void insertClac(List<AnalysisVo> param) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertClac'");
    }

    @Override
    public List<AnalysisVo> selectCalcList(Map<String, Object> params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectCalcList'");
    }

}