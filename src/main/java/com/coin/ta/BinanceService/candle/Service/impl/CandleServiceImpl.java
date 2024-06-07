package com.coin.ta.BinanceService.candle.Service.impl;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;

import com.coin.ta.BinanceService.BinanceApi;
import com.coin.ta.BinanceService.TA.Service.TaService;
import com.coin.ta.BinanceService.TA.Vo.TaVo;
import com.coin.ta.BinanceService.candle.Entity.AnalysisEntity;
import com.coin.ta.BinanceService.candle.Repository.AnalysisRepository;
import com.coin.ta.BinanceService.candle.Service.CandleService;
import com.coin.ta.BinanceService.candle.Vo.CandleVo;
import com.coin.ta.BinanceService.ticker.Service.TickerService;
import com.coin.ta.BinanceService.ticker.Vo.TickerVo;
import com.coin.ta.Redis.RedisService;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandleServiceImpl implements CandleService {

    private final BinanceApi binanceApi;
    private final TickerService tickerService;
    private final TaService taService;
    private final ModelMapper modelMapper;
    private final AnalysisRepository analysisRepository;
    private final RedisService redisService;

    @Override
    public List<CandleVo> getApiCandle(String symbol, String interval) {
        return this.parseData(binanceApi.getMarcketCandle(symbol, interval, API_LIMIT));
    }

    @Override
    public void calcCandle(String interval) {
        try {
            List<TickerVo> tickerVos = tickerService.getTickers();
            List<AnalysisEntity> list = new ArrayList<>();
            tickerVos.forEach(ticker -> {
                List<CandleVo> candleList = this.getApiCandle(ticker.getSymbol(), interval);
                AnalysisEntity result = this.analysisCandles(candleList);
                result.setSymbol(ticker.getSymbol());
                result.setInterval(interval);
                list.add(result);
            });
            this.insertClac(list);
            redisService.setValue("calc", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertClac(List<AnalysisEntity> param) {
        analysisRepository.saveAll(param);
    }

    @Override
    public List<AnalysisEntity> selectNowCalcList() {

        return (List<AnalysisEntity>) redisService.getValue("calc");

    }

    @Override
    public AnalysisEntity analysisCandles(List<CandleVo> params) {
        BarSeries bars = loadCandlestickData(params);
        TaVo taVo = taService.analyze(bars);
        return modelMapper.map(taVo, AnalysisEntity.class);

    }

    private BarSeries loadCandlestickData(List<CandleVo> params) {
        BarSeries series = new BaseBarSeriesBuilder().withName(params.get(0).getSymbol()).build();
        params.stream().forEach(candleVo -> series.addBar(
                Duration.ofDays(1),
                ZonedDateTime.ofInstant(Instant.ofEpochMilli(candleVo.getKlineCloseTime()), ZoneId.systemDefault()),
                series.numOf(candleVo.getOpenPrice()), // 시가
                series.numOf(candleVo.getHighPrice()), // 고가
                series.numOf(candleVo.getLowPrice()), // 저가
                series.numOf(candleVo.getClosePrice()), // 종가
                series.numOf(candleVo.getVolume()) // 거래량

        ));
        return series;
    }

    private List<CandleVo> parseData(String StrData) {
        Gson gson = new Gson();
        List<List<Object>> tempList = gson.fromJson(StrData, new TypeToken<List<List<Object>>>() {
        }.getType());
        List<CandleVo> list = new ArrayList<>();

        for (List<Object> rowData : tempList) {
            CandleVo vo = new CandleVo();
            vo.setKlineOpenTime(((Number) rowData.get(0)).longValue());
            vo.setOpenPrice(Double.parseDouble(rowData.get(1).toString()));
            vo.setHighPrice(Double.parseDouble(rowData.get(2).toString()));
            vo.setLowPrice((Double.parseDouble(rowData.get(3).toString())));
            vo.setClosePrice(Double.parseDouble(rowData.get(4).toString()));
            vo.setVolume(Double.parseDouble(rowData.get(5).toString()));
            vo.setKlineCloseTime(((Number) rowData.get(6)).longValue());
            vo.setQuoteAssetVolume(Double.parseDouble(rowData.get(7).toString()));
            vo.setNumberOfTrades(Double.parseDouble(rowData.get(8).toString()));
            vo.setTakerBuyBaseAssetVolume(Double.parseDouble(rowData.get(9).toString()));
            vo.setTakerBuyQuoteAssetVolume(Double.parseDouble(rowData.get(10).toString()));
            list.add(vo);
        }
        return list;

    }

}