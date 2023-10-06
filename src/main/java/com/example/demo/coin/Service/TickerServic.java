package com.example.demo.coin.Service;

import java.util.List;

import com.example.demo.coin.Vo.TickerVo;

public interface TickerServic {
    public void insertTicker();

    public List<TickerVo> selectTickerVos();

}
