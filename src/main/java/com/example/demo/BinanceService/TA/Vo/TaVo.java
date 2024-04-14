package com.example.demo.BinanceService.TA.Vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaVo {
    private String symbol;
    private int rsiBet;
    private int mfiCg;
    private int maCrossOver30;
    private int maCrossOver180;
    private int maCrossUnder30;
    private int maCrossUnder180;
    private int candleDoji;
    private int CehckDivergenceRsi14;
    private int CehckDivergenceMfi14;
    private int candleHammer;
    private int candleInverseHammer;
    private int macdSignal;
    private int calc;
    private String interval;
}