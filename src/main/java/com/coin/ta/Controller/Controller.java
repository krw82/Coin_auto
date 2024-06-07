package com.coin.ta.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coin.ta.BinanceService.candle.Entity.AnalysisEntity;
import com.coin.ta.BinanceService.candle.Service.CandleService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController

@RequiredArgsConstructor
public class Controller {
    private final CandleService candleService;

    @GetMapping("/")
    public Mono<Object> selectNowCalcList() {
        return candleService.selectNowCalcList();
    }

    @GetMapping("/v2")
    public void test2() {
        candleService.calcCandle("1h");
    }

    @GetMapping("/v3")
    public void test3() {
        candleService.test();
    }

}
