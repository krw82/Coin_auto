package com.example.demo.BinanceService.TA;

import org.ta4j.core.BarSeries;

import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.HighestValueIndicator;
import org.ta4j.core.indicators.helpers.LowestValueIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;

public class TaUtil {

    public int emaCheckCross(EMAIndicator indicator, BarSeries series, int index) { // 이평 크로스 체크
        Num emaValue = indicator.getValue(index);
        Num openPrice = series.getBar(index).getOpenPrice();
        Num closePrice = series.getBar(index).getClosePrice();
        Num lowerPrice = series.getBar(index).getLowPrice();
        Num highPrice = series.getBar(index).getHighPrice();

        // 시가와 종가가 EMA보다 높은 경우(터치)
        if (openPrice.isGreaterThan(emaValue) && closePrice.isGreaterThan(emaValue)) {
            return lowerPrice.isLessThan(emaValue) ? 1 : 0;
        }

        // 시가와 종가가 EMA보다 낮은 경우(터치)
        if (openPrice.isLessThan(emaValue) && closePrice.isLessThan(emaValue)) {
            return highPrice.isGreaterThan(emaValue) ? -1 : 0;
        }

        // 종가가 EMA를 초과하는 경우(크로스 오버) => 시가와 종가가 ema기준으로 위치가 다를때
        return closePrice.isGreaterThan(emaValue) ? 1 : -1;
    }

    public boolean candleDoji(BarSeries series, int index) { // 도지캔들
        Num openPrice = series.getBar(index).getOpenPrice();
        Num closePrice = series.getBar(index).getClosePrice();
        Num lowerPrice = series.getBar(index).getLowPrice();
        Num highPrice = series.getBar(index).getHighPrice();

        Num multiplier = series.numOf(0.1);

        if (closePrice.minus(openPrice).abs().isLessThan(highPrice.minus(lowerPrice).multipliedBy(multiplier))) {
            return true;
        } else {
            return false;
        }

    }

    public boolean candleHammer(BarSeries series, int index) { // 망치형캔들
        Num openPrice = series.getBar(index).getOpenPrice();
        Num closePrice = series.getBar(index).getClosePrice();
        Num lowerPrice = series.getBar(index).getLowPrice();

        Num bodyLowerEnd = openPrice.isLessThan(closePrice) ? openPrice : closePrice;
        Num lowerShadow = bodyLowerEnd.minus(lowerPrice); // 아래꼬리 길이

        Num multiplier = series.numOf(0.002);

        if (lowerShadow.isGreaterThan(closePrice.multipliedBy(multiplier))) {
            return true;
        }
        return false;
    }

    public boolean candleInverseHammer(BarSeries series, int index) { // 역망치형 캔들
        Num openPrice = series.getBar(index).getOpenPrice();
        Num closePrice = series.getBar(index).getClosePrice();
        Num highPrice = series.getBar(index).getHighPrice();

        Num bodyHigerEnd = openPrice.isGreaterThan(closePrice) ? openPrice : closePrice;
        Num HigerShadow = highPrice.minus(bodyHigerEnd);

        Num multiplier = series.numOf(0.002);

        if (HigerShadow.isGreaterThan(closePrice.multipliedBy(multiplier))) {
            return false;
        }
        return true;

    }

    public int rsiBet(RSIIndicator shortRsi, RSIIndicator longRsi, int index) { // 장단기rsi 차이 전략

        Num rsiBetValue = shortRsi.getValue(index).dividedBy(longRsi.getValue(index));

        if (rsiBetValue.doubleValue() < 0.8 && shortRsi.getValue(index).doubleValue() < 30) {
            return 1;
        }

        if (rsiBetValue.doubleValue() > 1.2 && shortRsi.getValue(index).doubleValue() > 70) {
            return -1;
        }
        return 0;
    }

    private int checkDivergence(CachedIndicator<Num> indicator1, CachedIndicator<Num> indicator2, int index,
            int barCount) {
        // 1이 최저인데 2는 아닐때 다이버전스 발동.
        LowestValueIndicator lowestIndicator1 = new LowestValueIndicator(indicator1,
                barCount);
        LowestValueIndicator lowestIndicator2 = new LowestValueIndicator(indicator2,
                barCount);
        HighestValueIndicator highestIndicator1 = new HighestValueIndicator(indicator1,
                barCount);
        HighestValueIndicator highestIndicator2 = new HighestValueIndicator(indicator1,
                barCount);

        int lowestIndicator1Index = this.getValueIndex(lowestIndicator1, lowestIndicator1.getValue(index));
        int lowestIndicator2Index = this.getValueIndex(lowestIndicator2, lowestIndicator2.getValue(index));
        int highestIndicator1Index = this.getValueIndex(highestIndicator1, highestIndicator1.getValue(index));
        int highestIndicator2Index = this.getValueIndex(highestIndicator2, highestIndicator2.getValue(index));

        if (lowestIndicator1Index != lowestIndicator2Index
                && lowestIndicator1.getValue(index).isEqual(indicator1.getValue(index))) {
            return 1;
        }

        if (highestIndicator1Index != highestIndicator2Index
                && highestIndicator1.getValue(index).isEqual(indicator1.getValue(index))) {
            return -1;
        }

        return 0;
    }

    private int getValueIndex(CachedIndicator<Num> indicator, Num value) {

        for (int i = 0; i < indicator.getBarSeries().getBarCount(); i++) {
            if (indicator.getValue(i).isEqual(value)) {
                return i;
            }
        }
        return -1;
    }

    public int Macd(BarSeries series, int index) {

        // Initialize indicators
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        MACDIndicator macd = new MACDIndicator(closePrice, 12, 26);
        EMAIndicator signalLine = new EMAIndicator(macd, 9);

        // Check for MACD line crossovers
        boolean macdCrossAboveSignal = new CrossedUpIndicatorRule(macd, signalLine).isSatisfied(index);
        boolean macdCrossBelowSignal = new CrossedDownIndicatorRule(macd, signalLine).isSatisfied(index);

        if (macdCrossAboveSignal) {
            return 1;
        }
        if (macdCrossBelowSignal) {
            return -1;
        }
        return 0;
    }

    public int getDivRsi(BarSeries series, int index) {
        ClosePriceIndicator closes = new ClosePriceIndicator(series);
        RSIIndicator rsi = new RSIIndicator(closes, 14);

        return this.checkDivergence(closes, rsi, index, 70);

    }

    public int getDivMfi(BarSeries series, int index) {
        ClosePriceIndicator closes = new ClosePriceIndicator(series);
        MFIIndicator mfi = new MFIIndicator(series, 14);

        return this.checkDivergence(closes, mfi, index, 70);

    }

}
