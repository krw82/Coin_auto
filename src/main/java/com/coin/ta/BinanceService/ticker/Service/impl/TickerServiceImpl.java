package com.coin.ta.BinanceService.ticker.Service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.coin.ta.BinanceService.BinanceApi;
import com.coin.ta.BinanceService.ticker.Entity.TickerEntity;
import com.coin.ta.BinanceService.ticker.Repository.TickerRepository;
import com.coin.ta.BinanceService.ticker.Service.TickerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class TickerServiceImpl implements TickerService {

    private final BinanceApi binanceApi;

    private final TickerRepository tickerRepository;

    private final ObjectMapper objectMapper;

    @Override
    public List<TickerEntity> getTickers() {
        List<TickerEntity> list = tickerRepository.findTop40ByOrderByVolumeDesc();
        return list;
    }

    @Override
    public void getApiTicker() {

        try {

            String response = binanceApi.getMarcketTicker();
            // Api티커 받는거 넣기
            if (response != null) {
                List<TickerEntity> tickers = objectMapper.readValue(response, new TypeReference<List<TickerEntity>>() {
                });
                tickerRepository.saveAll(tickers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TickerEntity> responseTicker() {
        return tickerRepository.findAll();
    }

}
