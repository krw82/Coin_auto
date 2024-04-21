package com.example.demo.BinanceService.TA.Service;

import org.ta4j.core.BarSeries;

import com.example.demo.BinanceService.TA.Vo.TaVo;

public interface TaService {
    public TaVo analyze(BarSeries series);
}
