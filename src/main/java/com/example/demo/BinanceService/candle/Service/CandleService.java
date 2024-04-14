package com.example.demo.BinanceService.candle.Service;

import java.util.List;
import java.util.Map;

import com.example.demo.BinanceService.candle.Vo.AnalysisVo;
import com.example.demo.BinanceService.candle.Vo.CandleVo;

public interface CandleService {
    final String API_LIMIT = "200";

    public List<CandleVo> getApiCandle(String symbol, String interval);

    public void calcCandle(String interval);

    public void insertClac(List<AnalysisVo> param);

    public List<AnalysisVo> selectCalcList(Map<String, Object> params);

    public List<AnalysisVo> sendApiPythonClac(List<CandleVo> params);

}