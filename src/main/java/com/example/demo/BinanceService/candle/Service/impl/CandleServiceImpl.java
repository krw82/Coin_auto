package com.example.demo.BinanceService.candle.Service.impl;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;

import com.example.demo.BinanceService.BinanceApi;
import com.example.demo.BinanceService.TA.Service.TaService;
import com.example.demo.BinanceService.TA.Vo.TaVo;
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
    private final TaService taService;

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
                List<AnalysisVo> result = this.analysisCandles(candleList);
                this.insertClac(result); // 산출결과물 저장
            });
        } catch (Exception e) {

        }

    }

    @Override
    public void insertClac(List<AnalysisVo> param) {

    }

    @Override
    public List<AnalysisVo> selectCalcList(Map<String, Object> params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectCalcList'");
    }

    @Override
    public List<AnalysisVo> analysisCandles(List<CandleVo> params) {
        BarSeries bars = loadCandlestickData(params);
        TaVo taVo = taService.analyze(bars);

        return null;
    }

    private BarSeries loadCandlestickData(List<CandleVo> params) {
        BarSeries series = new BaseBarSeriesBuilder().withName(params.get(0).getSymbol()).build();
        params.stream().forEach(candleVo -> series.addBar(
                Duration.ofDays(1),
                ZonedDateTime.now(),
                series.numOf(candleVo.getOpenPrice()), // 시가
                series.numOf(candleVo.getHighPrice()), // 고가
                series.numOf(candleVo.getLowPrice()), // 저가
                series.numOf(candleVo.getClosePrice()), // 종가
                series.numOf(candleVo.getVolume()) // 거래량
        ));
        return series;
    }

}