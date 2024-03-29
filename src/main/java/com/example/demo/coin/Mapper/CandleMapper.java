package com.example.demo.coin.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.coin.Vo.TickerAnalysisVo;

@Mapper
public interface CandleMapper {
    public void insertCalc(TickerAnalysisVo param);

    public List<TickerAnalysisVo> selectcalc();

    public List<TickerAnalysisVo> selectCalcList(Map<String, Object> params);

    public List<TickerAnalysisVo> selectCoinDetail(Map<String, Object> params);
}
