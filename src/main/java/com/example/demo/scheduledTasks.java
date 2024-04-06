package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.coin.Service.CandleService;

@Component
public class scheduledTasks {

    @Autowired
    CandleService CandleService;

    @Scheduled(cron = "0 01 * * * ?") // 매시간 58분
    public void runHourlyTask() {
        // 여기에 실행할 로직을 적습니다.
        CandleService.getCandel("1h");
        System.out.println("시간당 작업이 실행되었습니다.");
    }

    @Scheduled(cron = "0 50 8 * * ?") // 매일 8시 50분 실행
    public void dailyTaskAtEightFifty() {
        // TickerService.insertTicker();

    }

    @Scheduled(cron = "0 01 1,5,9,13,17,21 * * ?") // 4시간
    public void FourunHourlyTask() {
        // 여기에 실행할 로직을 적습니다.
        CandleService.getCandel("4h");
        System.out.println("4시간당 작업이 실행되었습니다.");
    }

}
