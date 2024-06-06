package com.coin.ta.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coin.ta.BinanceService.candle.Entity.AnalysisEntity;
import com.coin.ta.BinanceService.candle.Service.CandleService;
import com.coin.ta.BinanceService.ticker.Service.TickerService;

import lombok.RequiredArgsConstructor;

@RestController

@RequiredArgsConstructor
public class Controller {
    private final CandleService candleService;
    private final TickerService tickerService;

    @GetMapping("/v1")
    public ResponseEntity<String> test() {
        candleService.calcCandle("1h");

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/v2")
    public List<AnalysisEntity> test2() {
        return candleService.selectNowCalcList();

    }

}
