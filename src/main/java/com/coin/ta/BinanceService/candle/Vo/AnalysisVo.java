package com.coin.ta.BinanceService.candle.Vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysisVo {
    private String symbol;
    private int rsiBet;
    private int mfiCg;
    private int maCrossOver_30;
    private int maCrossOver_180;
    private int maCrossUnder_30;
    private int maCrossUnder_180;
    private int candleDoji;
    private int CehckDivergence_rsi_14;
    private int CehckDivergence_mfi_14;
    private int candleHammer;
    private int candleInverseHammer;
    private int macdSignal;
    private int calc;
    private String interval;
    private Date regdtm;

}