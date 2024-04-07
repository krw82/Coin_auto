package com.example.demo.BinanceService.candle.Service;

import java.util.List;
import java.util.Map;

import com.example.demo.BinanceService.candle.Vo.AnalysisVo;
import com.example.demo.BinanceService.candle.Vo.CandleVo;

public interface CandleService {
    final String API_LIMIT = "200";

    public List<CandleVo> getApiCandle(String symbol, String interval);

    public List<AnalysisVo> calcCandle(List<CandleVo> param, String param2, String interval);

    public void insertClac(List<AnalysisVo> param);

    public List<AnalysisVo> selectCalcList(Map<String, Object> params);

}
