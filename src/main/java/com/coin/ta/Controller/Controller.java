package com.coin.ta.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coin.ta.BinanceService.candle.Entity.AnalysisEntity;
import com.coin.ta.BinanceService.candle.Service.CandleService;

import lombok.RequiredArgsConstructor;

@RestController

@RequiredArgsConstructor
public class Controller {
    private final CandleService candleService;

    @GetMapping("/")
    public List<AnalysisEntity> selectNowCalcList() {
        return candleService.selectNowCalcList();
    }

    @GetMapping("/v2")
    public List<AnalysisEntity> test2() {
        return candleService.selectNowCalcList();
    }

}
