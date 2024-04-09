package com.example.demo.BinanceService.candle.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.BinanceService.BinanceApi;
import com.example.demo.BinanceService.candle.Service.CandleService;
import com.example.demo.BinanceService.candle.Vo.AnalysisVo;
import com.example.demo.BinanceService.candle.Vo.CandleVo;
import com.example.demo.BinanceService.ticker.Service.TickerService;
import com.example.demo.BinanceService.ticker.Vo.TickerVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandleServiceImpl implements CandleService {

    private final BinanceApi binanceApi;
    private final TickerService tickerService;

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
    public void calcCandle(String interval) {
        try {
            List<TickerVo> tickerVos = tickerService.getTickers();
            tickerVos.forEach(ticker -> {
                List<CandleVo> candleList = this.getApiCandle(ticker.getSymbol(), interval);
                // 여기서 candleList를 Python 서비스로 보내는 로직 구현
                // 예를 들어, Python 서비스 호출 결과를 AnalysisVo 객체로 변환하여 result에 추가
                List<AnalysisVo> result = this.sendApiPythonClac(candleList);
                this.insertClac(result); // 산출결과물 저장
            });
        } catch (Exception e) {

        }

    }

    @Override
    public void insertClac(List<AnalysisVo> param) {
        try {

        } catch (Exception e) {

        }
    }

    @Override
    public List<AnalysisVo> selectCalcList(Map<String, Object> params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectCalcList'");
    }

    @Override
    public List<AnalysisVo> sendApiPythonClac(List<CandleVo> params) {
        List<AnalysisVo> result = new ArrayList<>();
        return result;
    }

}