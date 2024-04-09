package com.example.demo.BinanceService.ticker.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import com.example.demo.BinanceService.BinanceApi;
import com.example.demo.BinanceService.ticker.Entity.TickerEntity;
import com.example.demo.BinanceService.ticker.Repository.TickerRepository;
import com.example.demo.BinanceService.ticker.Service.TickerService;
import com.example.demo.BinanceService.ticker.Vo.TickerVo;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.core.type.TypeReference;

@Service
@RequiredArgsConstructor
public class TickerServiceImpl implements TickerService {

    private final BinanceApi binanceApi;

    private final TickerRepository tickerRepository;

    private final ModelMapper modelMapper;

    @Override

    public void insertTicker() {

        try {
            List<TickerVo> list = this.getApiTicker();
            // ModelMapper를 사용하여 VO 리스트를 Entity 리스트로 변환
            List<TickerEntity> entities = list.stream().map(tickerVo -> modelMapper.map(tickerVo, TickerEntity.class))
                    .collect(Collectors.toList());
            tickerRepository.saveAll(entities);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TickerVo> getTickers() {
        List<TickerEntity> list = tickerRepository.findTop40ByOrderByVolumeDesc();
        List<TickerVo> vos = list.stream().map(entities -> modelMapper.map(entities, TickerVo.class))
                .collect(Collectors.toList());
        return vos;
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
