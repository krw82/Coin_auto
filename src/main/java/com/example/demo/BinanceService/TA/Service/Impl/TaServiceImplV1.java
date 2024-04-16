package com.example.demo.BinanceService.TA.Service.Impl;

import com.example.demo.BinanceService.TA.TaUtil;
import com.example.demo.BinanceService.TA.Service.TaService;
import com.example.demo.BinanceService.TA.Vo.TaVo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.ta4j.core.*;

import java.lang.reflect.Field;

@Service
@Primary
public class TaServiceImplV1 implements TaService {

    @Override
    public TaVo analyze(BarSeries series) {

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

        return vo;

    }

    private int getCalc(TaVo vo) {
        Field[] fields = this.getClass().getDeclaredFields();
        int sum = 0;

        return 0;
    }

}
