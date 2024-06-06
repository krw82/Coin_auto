package com.coin.ta.BinanceService.candle.Service;

import java.util.List;

import com.coin.ta.BinanceService.candle.Entity.AnalysisEntity;
import com.coin.ta.BinanceService.candle.Vo.CandleVo;

public interface CandleService {
    final String API_LIMIT = "200";

    public List<CandleVo> getApiCandle(String symbol, String interval);

    public void calcCandle(String interval);

    public void insertClac(List<AnalysisEntity> param);

    public List<AnalysisEntity> selectNowCalcList();

    public AnalysisEntity analysisCandles(List<CandleVo> params);

}
