package com.example.demo.coin.Service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.coin.Mapper.CandleMapper;
import com.example.demo.coin.Service.CandleService;

import com.example.demo.coin.TA.Bridge;
import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.Vo.TickerAnalysisVo;

import com.example.demo.coin.comm.Util;

@Service
public class CandleServiceImpl implements CandleService {

    @Autowired
    CandleMapper CandleMapper;

    /*
     * ≈ @Override
     * public synchronized List<List<CandleVo>> getCandel(String interval) {
     * List<List<CandleVo>> list = new ArrayList<>();
     * List<TickerAnalysisVo> clacList = new ArrayList<>();
     * List<CandleVo> CandelList = null;
     * try {
     * List<TickerVo> tickerList = TickerService.selectTickerVos();
     * for (TickerVo item : tickerList) {
     * if (false) {
     * // 여기에 200개가 안될경우 del insert ? merge?
     * Util.PriceToVo(binanceBc.getCandle(item.getSymbol(), interval, 200));
     * } else {
     * CandelList = Util.PriceToVo(binanceBc.getCandle(item.getSymbol(), interval,
     * 1));
     * }
     * clacList = this.calcCandle(CandelList, item.getSymbol(), interval); // 산출
     * this.insertClac(clacList);// 저장
     * }
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     *
     * return list;
     * }
     */

    @Override
    public List<TickerAnalysisVo> calcCandle(List<CandleVo> parma, String symbol, String interval) {
        return Bridge.PythonTa(parma, symbol, interval);
    }

    @Override
    public void insertClac(List<TickerAnalysisVo> param) {

        CandleMapper.insertCalc(param.get(0));

    }

    @Override
    public List<TickerAnalysisVo> selectCalcList(Map<String, Object> params) {
        return CandleMapper.selectCalcList(params);
    }

    @Override
    public List<TickerAnalysisVo> selectCoinDetail(Map<String, Object> params) {

        return CandleMapper.selectCoinDetail(params);
    }

    @Override
    public List<List<CandleVo>> getCandel(String interval) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCandel'");
    }

}