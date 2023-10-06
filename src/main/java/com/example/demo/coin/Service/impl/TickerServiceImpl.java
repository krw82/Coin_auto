package com.example.demo.coin.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.coin.BinanceApi.BinanceBc;
import com.example.demo.coin.Mapper.TickerMapper;
import com.example.demo.coin.Service.TickerServic;
import com.example.demo.coin.Vo.TickerVo;
import com.example.demo.coin.comm.Util;

@Service
public class TickerServiceImpl implements TickerServic {

    @Autowired
    protected TickerMapper tickerMapper;

    @Autowired
    BinanceBc binanceBc;

    @Override

    public void insertTicker() {
        List<TickerVo> list;
        try {
            tickerMapper.deleteTicker();
            list = Util.TickerToVo(binanceBc.getTicker());
            tickerMapper.insertTicker(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TickerVo> selectTickerVos() {
        return tickerMapper.selectTickerVos();
    }

}
