package com.example.demo.BinanceService.TA.Service;

import java.util.List;

import org.ta4j.core.BarSeries;

import com.example.demo.BinanceService.TA.Vo.TaVo;
import com.example.demo.BinanceService.candle.Vo.CandleVo;

public interface TaService {
    public TaVo analyze(BarSeries series);
}
