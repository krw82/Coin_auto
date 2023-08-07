import numpy as np
import talib
import json
import sys


data = json.loads(sys.argv[1])

# 캔들 데이터에서 종가만 추출하여 numpy 배열로 변환합니다.
close_prices = np.array([candle["closePrice"] for candle in data])

# TA-Lib를 사용하여 RSI를 계산합니다. 기간은 일반적으로 14일을 사용합니다.
rsi = talib.RSI(close_prices, timeperiod=14)

# 계산된 RSI 값을 출력합니다.
for value in rsi:
    print(value)
