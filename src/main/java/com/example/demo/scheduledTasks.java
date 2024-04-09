package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.BinanceService.candle.Service.CandleService;
import com.example.demo.BinanceService.ticker.Service.TickerService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class scheduledTasks {

    private final CandleService candleService;
    private final TickerService tickerService;

    @Scheduled(cron = "0 01 * * * ?") // 매시간 58분
    public void runHourlyTask() {
        // 여기에 실행할 로직을 적습니다.
        candleService.calcCandle("1h");
        System.out.println("시간당 작업이 실행되었습니다.");
    }

    @Scheduled(cron = "0 50 8 * * ?") // 매일 8시 50분 실행
    public void dailyTaskAtEightFifty() {
        tickerService.insertTicker();

    }

    @Scheduled(cron = "0 01 1,5,9,13,17,21 * * ?") // 4시간
    public void FourunHourlyTask() {
        // 여기에 실행할 로직을 적습니다.
        candleService.calcCandle("4h");
        System.out.println("4시간당 작업이 실행되었습니다.");
    }

}
