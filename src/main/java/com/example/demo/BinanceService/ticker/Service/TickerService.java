package com.example.demo.BinanceService.ticker.Service;

import java.util.List;

import com.example.demo.BinanceService.ticker.Vo.TickerVo;

public interface TickerService {
    public void insertTicker(); // 티커 저장

    public List<TickerVo> getTickers(); // 데이터 베이스 티커 가져오기

    public List<TickerVo> getApiTicker(); // 거래소 api활용하여 티커명 가져오기

}
