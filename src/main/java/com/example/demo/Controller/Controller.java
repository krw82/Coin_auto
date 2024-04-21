package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.BinanceService.candle.Service.CandleService;
import com.example.demo.BinanceService.ticker.Service.TickerService;

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
    public ResponseEntity<String> test2() {
        tickerService.insertTicker();

        return ResponseEntity.ok("Success");
    }

}
