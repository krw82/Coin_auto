package com.example.demo.BinanceService.TA.Service;

import java.util.List;

import org.python.modules.itertools.product;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.num.Num;

import com.example.demo.BinanceService.TA.Vo.TaVo;
import com.example.demo.BinanceService.candle.Vo.CandleVo;

public interface TaService {
    public TaVo analyze(List<CandleVo> params);
}
