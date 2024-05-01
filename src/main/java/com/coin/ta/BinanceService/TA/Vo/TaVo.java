package com.coin.ta.BinanceService.TA.Vo;

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
    private int checkDivergenceRsi14;
    private int checkDivergenceMfi14;
    private int candleHammer;
    private int candleInverseHammer;
    private int macdSignal;
    private boolean volumeAvg;
    private int calc;
    private String interval;
    private int coinAnalyze;

    public void setCalc() {

        this.calc = 5 + rsiBet + mfiCg + maCross30 + maCross180 + candleDoji + checkDivergenceRsi14
                + checkDivergenceMfi14
                + candleHammer + candleInverseHammer + macdSignal;
    }

}