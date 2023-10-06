package com.example.demo.coin.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.coin.Vo.TickerVo;

@Mapper
public interface TickerMapper {

    void insertTicker(List<TickerVo> param); // 티커 데이터베이스에 넣기

    void deleteTicker(); // 티커 데이터베이스에 넣기

    List<TickerVo> selectTickerVos();
}
