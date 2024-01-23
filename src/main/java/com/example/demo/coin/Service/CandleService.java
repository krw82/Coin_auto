package com.example.demo.coin.Service;

import java.util.List;
import java.util.Map;

import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.Vo.TickerAnalysisVo;

public interface CandleService {
    // 캔들가져와야함.
    // 캔들 산출.
    // 캔들저장.
    public List<List<CandleVo>> getCandel(String interval);

    public List<TickerAnalysisVo> calcCandle(List<CandleVo> param, String param2, String interval);

    public void insertClac(List<TickerAnalysisVo> param);

    public List<TickerAnalysisVo> selectCalcList(String params);

}
