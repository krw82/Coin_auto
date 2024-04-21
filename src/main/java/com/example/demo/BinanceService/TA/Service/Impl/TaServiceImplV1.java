package com.example.demo.BinanceService.TA.Service.Impl;

import com.example.demo.BinanceService.TA.TaUtil;
import com.example.demo.BinanceService.TA.Service.TaService;
import com.example.demo.BinanceService.TA.Vo.TaVo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.ta4j.core.*;

@Service
@Primary
public class TaServiceImplV1 implements TaService {

    @Override
    public TaVo analyze(BarSeries series) {

        TaVo vo = new TaVo();
        int index = series.getBarCount() - 1;
        vo.setRsiBet(TaUtil.rsiBet(series, 7, 14, index));
        vo.setCandleDoji(TaUtil.candleDoji(series, 14, index));
        vo.setCandleHammer(TaUtil.candleHammer(series, index));
        vo.setCandleInverseHammer(TaUtil.candleInverseHammer(series, index));
        vo.setMaCross180(TaUtil.emaCheckCross(series, 180, index));
        vo.setMaCross180(TaUtil.emaCheckCross(series, 180, index));
        vo.setMaCross30(TaUtil.emaCheckCross(series, 30, index));
        vo.setMacdSignal(TaUtil.Macd(series, index));
        vo.setCheckDivergenceMfi14(TaUtil.getDivMfi(series, index));
        vo.setCheckDivergenceRsi14(TaUtil.getDivRsi(series, index));
        vo.setMfiCg(TaUtil.mfiCg(series, 0));
        vo.setVolumeAvg(TaUtil.checkVolumeAvg(series, 2, index));
        vo.setCalc();

        vo = this.getCalc(vo);

        return vo;

    }

    private TaVo getCalc(TaVo vo) {
        int temp = vo.getCalc();
        if (vo.isVolumeAvg()) {
            if (temp > 5) {
                temp += 1;
            } else {
                temp -= 1;
            }
        }
        vo.setCalc(temp);

        if (vo.getCheckDivergenceMfi14() < 0
                || vo.getCheckDivergenceRsi14() < 0
                || temp <= 2) {
            vo.setCoinAnalyze(-1);
            return vo;
        } else if (temp >= 8) {
            vo.setCoinAnalyze(1);
            return vo;
        }
        vo.setCoinAnalyze(0);

        return vo;
    }

}
