package com.example.demo.BinanceService.TA.Service.Impl;

import java.time.ZonedDateTime;
import java.util.List;

import com.example.demo.BinanceService.TA.Service.TaService;
import com.example.demo.BinanceService.TA.Vo.TaVo;
import com.example.demo.BinanceService.candle.Vo.CandleVo;

import lombok.extern.log4j.Log4j;

import org.python.jline.internal.Log;
import org.springframework.stereotype.Service;
import org.ta4j.core.*;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.VolumeIndicator;

import java.time.Duration;

import org.ta4j.core.num.Num;

@Service
public class TaServiceImplV1 implements TaService {

    @Override
    public TaVo analyze(List<CandleVo> params) {
        BarSeries series = this.loadCandlestickData(params);
        ClosePriceIndicator closePrices = new ClosePriceIndicator(series); // 종가 인디케이터
        VolumeIndicator volume = new VolumeIndicator(series, 14); // 거래량 인디케이터
        RSIIndicator rsi14 = new RSIIndicator(closePrices, 14); // rsi는 종가로만 계산됨.
        EMAIndicator Ema30 = new EMAIndicator(closePrices, 30);
        EMAIndicator Ema180 = new EMAIndicator(closePrices, 180);

        throw new UnsupportedOperationException("Unimplemented method 'analyze'");

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
