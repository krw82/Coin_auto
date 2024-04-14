package com.example.demo.BinanceService.TA.Vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaVo {
    private String symbol;
    private int rsiBet;
    private int mfiCg;
    private int maCross30;
    private int maCross180;
    private int candleDoji;
    private int CehckDivergenceRsi14;
    private int CehckDivergenceMfi14;
    private int candleHammer;
    private int candleInverseHammer;
    private int macdSignal;
    private int volumeAvg;
    private int calc;
    private String interval;
    private int analyze;

    public void setFirstCalc() {
        calc = 5 + rsiBet + mfiCg + maCross180 + maCross30 + candleDoji + CehckDivergenceRsi14 + CehckDivergenceMfi14
                + candleHammer + candleInverseHammer + macdSignal;
    }

}