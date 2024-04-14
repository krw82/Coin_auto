package com.example.demo.BinanceService.TA;

import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.indicators.helpers.TypicalPriceIndicator;
import org.ta4j.core.num.Num;

public class MFIIndicator extends CachedIndicator<Num> {

    private final TypicalPriceIndicator typicalPriceIndicator;
    private final int barCount;

    protected MFIIndicator(BarSeries series, int barCount) {
        super(series);
        this.typicalPriceIndicator = new TypicalPriceIndicator(series);
        this.barCount = barCount;

    }

    @Override
    protected Num calculate(int index) {
        if (index < barCount - 1) {
            return numOf(50);
        }
        Num positiveFlow = numOf(0);
        Num negativeFlow = numOf(0);

        for (int i = index - barCount + 1; i <= index; i++) {
            Num currentMoneyFlow = getMoneyFlow(i);

            if (i == 0) {
                continue;
            }

            Num previousMoneyFlow = getMoneyFlow(i - 1);
            if (currentMoneyFlow.isGreaterThan(previousMoneyFlow)) {
                positiveFlow = positiveFlow.plus(currentMoneyFlow);
            } else {
                negativeFlow = negativeFlow.plus(currentMoneyFlow);
            }
        }

        Num moneyFlowRatio = positiveFlow.dividedBy(negativeFlow);
        return numOf(100).minus(numOf(100).dividedBy(numOf(1).plus(moneyFlowRatio)));
    }

    private Num getMoneyFlow(int i) {
        Num typicalPrice = typicalPriceIndicator.getValue(i);
        Num volume = getBarSeries().getBar(i).getVolume();
        return typicalPrice.multipliedBy(volume);
    }

}
