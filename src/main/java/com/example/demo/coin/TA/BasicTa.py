import numpy as np
import talib
import json
import sys


data = json.loads(sys.argv[1])


# with open(sys.argv[1], "r") as f:
#   data = json.load(f)


close_prices = np.array([candle["closePrice"] for candle in data])
high_prices = np.array([candle["highPrice"] for candle in data])
low_prices = np.array([candle["lowPrice"] for candle in data])
volume = np.array([candle["volume"] for candle in data])
open_prices = np.array([candle["openPrice"] for candle in data])


average_volume = np.mean(volume[-14:])
current_volume = volume[-1]
rsi_14 = talib.RSI(close_prices, timeperiod=14)
mfi_14 = talib.MFI(high_prices, low_prices, close_prices, volume, timeperiod=14)


def fn_rsiBet():  # tripleRsi의 이격을 보는 로직.
    key = 0
    rsi_7 = talib.RSI(close_prices, timeperiod=7)
    rsi_bet = rsi_7[-1] / rsi_14[-1]

    if rsi_bet < 0.8 and rsi_7[-1] < 30:
        key += 1

    if rsi_bet > 1.2 and rsi_7[-1] > 70:
        key -= 1

    return key


def fn_mfiCg():  # MFI 과매수,과매도 측정
    mfi = mfi_14[-1]
    key = 0
    if mfi < 10:
        key += 1

    if mfi > 90:
        key -= 1

    return key


def fn_maCrossOver(period):  # 이평 크로스오버
    ma = talib.SMA(close_prices, timeperiod=period)[-1] * 0.9999
    key = 0
    if (high_prices[-1] > ma) and open_prices[-1] < ma:
        if close_prices[-1] > ma:
            key += 1
        else:
            key -= 1
    return key


def fn_maCrossUnder(period):  # 이평 크로스언더
    ma = talib.SMA(close_prices, timeperiod=period)[-1] * 1.0001
    key = 0
    if low_prices[-1] < ma and open_prices[-1] > ma:
        if close_prices[-1] < ma:
            key -= 1
        else:
            key += 1
    return key


def fn_candleDoji(rsi_low, rsi_high):  # 도지캔들
    key = 0
    if (
        current_volume > average_volume * 1.1
        and abs(close_prices[-1] - open_prices[-1])
        <= (high_prices[-1] - low_prices[-1]) * 0.1
    ):
        if rsi_14[-1] <= rsi_low:
            key += 1

        if rsi_14[-1] >= rsi_high:
            key -= 1

    return key


def fn_CehckDivergence(price, indicator, period):  # 다이버전스체크
    lowest_price_index = np.argmin(price[-period:])
    highest_price_index = np.argmax(price[-period:])

    lowest_indicator_index = np.argmin(indicator[-period:])
    highest_indicator_index = np.argmax(indicator[-period:])

    key = 0

    if (
        lowest_price_index != lowest_indicator_index
        and lowest_price_index == len(price) - 1
        and current_volume > average_volume * 1.2
    ):
        key += 1

    elif (
        highest_price_index != highest_indicator_index
        and highest_price_index == len(price) - 1
        and current_volume > average_volume * 1.2
    ):
        key -= 1

    return key


def fn_candleHammer(c, o, h, l):  # 망치형캔들
    lowerShadow = min(o, c) - l

    key = 0

    if c * 0.002 < lowerShadow and current_volume > average_volume * 2:
        key += 1

    return key


def fn_candleInverseHammer(c, o, h, l):  # 역망치캔들
    upperShadow = h - max(o, c)
    key = 0

    if c * 0.002 < upperShadow and current_volume > average_volume * 2:
        key -= 1

    return key


def fn_macd(macdSource, macdShort, macdLong, macdSignal):
    macdLine, signalLine, _ = talib.MACD(
        macdSource, fastperiod=macdShort, slowperiod=macdLong, signalperiod=macdSignal
    )
    lowest_macd = talib.MIN(macdLine, timeperiod=70)
    highest_macd = talib.MAX(macdLine, timeperiod=70)

    bullishDivergence = fn_CehckDivergence(macdSource, lowest_macd, 70)
    bearDivergence = fn_CehckDivergence(macdSource, highest_macd, 70)
    macdCrossAboveSignal = (
        macdLine[-1] > signalLine[-1] and macdLine[-2] < signalLine[-2]
    )
    macdCrossBelowSignal = (
        macdLine[-1] < signalLine[-1] and macdLine[-2] > signalLine[-2]
    )
    key = 0
    if macdCrossAboveSignal:
        key += 1
    if macdCrossBelowSignal:
        key -= 1
    key += bullishDivergence + bearDivergence
    return key


rsiBet = fn_rsiBet()
mfiCg = fn_mfiCg()
maCrossOver_30 = fn_maCrossOver(30)
maCrossOver_180 = fn_maCrossOver(180)
maCrossUnder_30 = fn_maCrossUnder(30)
maCrossUnder_180 = fn_maCrossUnder(180)
candleDoji = fn_candleDoji(30, 70)
CehckDivergence_rsi_14 = fn_CehckDivergence(close_prices, rsi_14, 70)
CehckDivergence_mfi_14 = fn_CehckDivergence(close_prices, mfi_14, 70)
candleHammer = fn_candleHammer(
    close_prices[-1], open_prices[-1], high_prices[-1], low_prices[-1]
)
candleInverseHammer = fn_candleInverseHammer(
    close_prices[-1], open_prices[-1], high_prices[-1], low_prices[-1]
)
macdSignal = fn_macd(close_prices, 8, 17, 9)

key = (
    sum(
        [
            rsiBet,
            mfiCg,
            maCrossOver_30,
            maCrossOver_180,
            maCrossUnder_30,
            maCrossUnder_180,
            candleDoji,
            CehckDivergence_rsi_14,
            CehckDivergence_mfi_14,
            candleHammer,
            candleInverseHammer,
            macdSignal,
        ]
    )
    + 5
)
if current_volume > average_volume * 2:
    if key > 5:
        key += 1

    elif key < 5:
        key -= 1

buy = key >= 8
sell = key <= 2 or CehckDivergence_rsi_14 == -1 or CehckDivergence_mfi_14 == -1

calc = 0

if buy:
    calc = 1
elif sell:
    calc = -1


results = {
    "symbol": sys.argv[2],
    "rsiBet": rsiBet,
    "mfiCg": mfiCg,
    "maCrossOver_30": maCrossOver_30,
    "maCrossOver_180": maCrossOver_180,
    "maCrossUnder_30": maCrossUnder_30,
    "maCrossUnder_180": maCrossUnder_180,
    "candleDoji": candleDoji,
    "CehckDivergence_rsi_14": CehckDivergence_rsi_14,
    "CehckDivergence_mfi_14": CehckDivergence_mfi_14,
    "candleHammer": candleHammer,
    "candleInverseHammer": candleInverseHammer,
    "macdSignal": macdSignal,
    "calc": calc,
}

print(json.dumps(results))
