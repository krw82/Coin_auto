package com.coin.ta.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coin.ta.BinanceService.candle.Entity.AnalysisEntity;
import com.coin.ta.BinanceService.candle.Service.CandleService;
import com.coin.ta.BinanceService.ticker.Entity.TickerEntity;
import com.coin.ta.BinanceService.ticker.Service.TickerService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController

@RequiredArgsConstructor
public class Controller {
    private final CandleService candleService;
    private final TickerService tickerService;

    @GetMapping("/")
    public Mono<Object> selectNowCalcList() {
        return candleService.selectNowCalcList();
    }

    @GetMapping("/v2")
    public void test2() {
        tickerService.getApiTicker();
    }

    @GetMapping("/v3")
    public List<TickerEntity> test3() {
        return tickerService.responseTicker();

    }

}
