package com.example.demo.coin.Vo;

public class TickerAnalysisVo {
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRsiBet() {
        return rsiBet;
    }

    public void setRsiBet(int rsiBet) {
        this.rsiBet = rsiBet;
    }

    public int getMfiCg() {
        return mfiCg;
    }

    public void setMfiCg(int mfiCg) {
        this.mfiCg = mfiCg;
    }

    public int getMaCrossOver_30() {
        return maCrossOver_30;
    }

    public void setMaCrossOver_30(int maCrossOver_30) {
        this.maCrossOver_30 = maCrossOver_30;
    }

    public int getMaCrossOver_180() {
        return maCrossOver_180;
    }

    public void setMaCrossOver_180(int maCrossOver_180) {
        this.maCrossOver_180 = maCrossOver_180;
    }

    public int getMaCrossUnder_30() {
        return maCrossUnder_30;
    }

    public void setMaCrossUnder_30(int maCrossUnder_30) {
        this.maCrossUnder_30 = maCrossUnder_30;
    }

    public int getMaCrossUnder_180() {
        return maCrossUnder_180;
    }

    public void setMaCrossUnder_180(int maCrossUnder_180) {
        this.maCrossUnder_180 = maCrossUnder_180;
    }

    public int getCandleDoji() {
        return candleDoji;
    }

    public void setCandleDoji(int candleDoji) {
        this.candleDoji = candleDoji;
    }

    public int getCehckDivergence_rsi_14() {
        return CehckDivergence_rsi_14;
    }

    public void setCehckDivergence_rsi_14(int cehckDivergence_rsi_14) {
        CehckDivergence_rsi_14 = cehckDivergence_rsi_14;
    }

    public int getCehckDivergence_mfi_14() {
        return CehckDivergence_mfi_14;
    }

    public void setCehckDivergence_mfi_14(int cehckDivergence_mfi_14) {
        CehckDivergence_mfi_14 = cehckDivergence_mfi_14;
    }

    public int getCandleHammer() {
        return candleHammer;
    }

    public void setCandleHammer(int candleHammer) {
        this.candleHammer = candleHammer;
    }

    public int getCandleInverseHammer() {
        return candleInverseHammer;
    }

    public void setCandleInverseHammer(int candleInverseHammer) {
        this.candleInverseHammer = candleInverseHammer;
    }

}
