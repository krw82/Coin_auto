package com.example.demo.BinanceService.TA.Service.Impl;

import java.time.ZonedDateTime;
import java.util.List;

import com.example.demo.BinanceService.TA.TaUtil;
import com.example.demo.BinanceService.TA.Service.TaService;
import com.example.demo.BinanceService.TA.Vo.TaVo;
import com.example.demo.BinanceService.candle.Vo.CandleVo;
import org.springframework.stereotype.Service;
import org.ta4j.core.*;

import java.time.Duration;

@Service
public class TaServiceImplV1 implements TaService {

    @Override
    public TaVo analyze(List<CandleVo> params) {
        BarSeries series = this.loadCandlestickData(params);
        TaVo vo = new TaVo();
        vo.setRsiBet(TaUtil.rsiBet(series, 7, 14, 0));
        vo.setCandleDoji(TaUtil.candleDoji(series, 14, 0));
        vo.setCandleHammer(TaUtil.candleHammer(series, 0));
        vo.setCandleInverseHammer(TaUtil.candleInverseHammer(series, 0));
        vo.setMaCross180(TaUtil.emaCheckCross(series, 180, 0));
        vo.setMaCross30(TaUtil.emaCheckCross(series, 30, 0));
        vo.setMacdSignal(TaUtil.Macd(series, 0));
        vo.setCehckDivergenceMfi14(TaUtil.getDivMfi(series, 0));
        vo.setCehckDivergenceRsi14(TaUtil.getDivRsi(series, 0));
        vo.setMfiCg(TaUtil.mfiCg(series, 0));
        vo.setFirstCalc();
        if (TaUtil.checkVolumeAvg(series, 2, 0) && vo.getCalc() != 5) {
            if (vo.getCalc() > 5) {
                vo.setCalc(vo.getCalc() + 1);
            } else {
                vo.setCalc(vo.getCalc() - 1);
            }
        }

        return vo;

    }

    private BarSeries loadCandlestickData(List<CandleVo> params) {
        BarSeries series = new BaseBarSeriesBuilder().withName("candlestick_data").build();
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
