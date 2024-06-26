package com.coin.ta.BinanceService.ticker.Service;

import java.util.List;

import com.coin.ta.BinanceService.ticker.Entity.TickerEntity;
import com.coin.ta.BinanceService.ticker.Vo.TickerVo;

public interface TickerService {

    public List<TickerEntity> getTickers(); // 데이터 베이스 티커 가져오기

    public void getApiTicker(); // 거래소 api활용하여 티커명 가져오기

    public List<TickerEntity> responseTicker();

}
