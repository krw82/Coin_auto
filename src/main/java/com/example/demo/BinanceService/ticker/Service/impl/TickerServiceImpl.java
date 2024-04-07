package com.example.demo.BinanceService.ticker.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.BinanceService.BinanceApi;
import com.example.demo.BinanceService.ticker.Entity.TickerEntity;
import com.example.demo.BinanceService.ticker.Repository.TickerRepository;
import com.example.demo.BinanceService.ticker.Service.TickerService;
import com.example.demo.BinanceService.ticker.Vo.TickerVo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class TickerServiceImpl implements TickerService {

    @Autowired
    BinanceApi binanceApi;

    @Autowired
    TickerRepository tickerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override

    public void insertTicker() {

        try {
            List<TickerVo> list = getApiTicker();
            List<TickerEntity> entities = new ArrayList<>();
            // ModelMapper를 사용하여 VO 리스트를 Entity 리스트로 변환
            for (TickerVo vo : list) {
                TickerEntity entity = modelMapper.map(vo, TickerEntity.class);
                entities.add(entity);
            }
            tickerRepository.saveAll(entities);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TickerEntity> getTickersBySymbol(String symbol) {
        return tickerRepository.findBySymbol(symbol);
    }

    @Override
    public List<TickerVo> getApiTicker() {
        List<TickerVo> voList = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            voList = objectMapper.readValue(binanceApi.getMarcketTicker(),
                    new TypeReference<List<TickerVo>>() {
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return voList;
    }

}
