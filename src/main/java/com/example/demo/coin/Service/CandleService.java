package com.example.demo.coin.Service;

import java.util.List;

import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.Vo.TickerAnalysisVo;

public interface CandleService {
    // 캔들가져와야함.
    // 캔들 산출.
    // 캔들저장.
    public List<List<CandleVo>> getCandel();

    public List<TickerAnalysisVo> calcCandle(List<CandleVo> param, String param2);

    public void insertClac(List<TickerAnalysisVo> param);

}
