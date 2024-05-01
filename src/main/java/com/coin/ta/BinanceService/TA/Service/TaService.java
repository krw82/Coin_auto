package com.coin.ta.BinanceService.TA.Service;

import org.ta4j.core.BarSeries;

import com.coin.ta.BinanceService.TA.Vo.TaVo;

public interface TaService {
    public TaVo analyze(BarSeries series);
}
