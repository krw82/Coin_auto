package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.coin.Service.CandleService;
import com.example.demo.coin.Service.TickerServic;

@Component
public class scheduledTasks {

    @Autowired
    TickerServic TickerService;

    @Autowired
    CandleService CandleService;

    @Scheduled(cron = "0 58 * * * ?") // 매시간 58분
    public void runHourlyTask() {
        // 여기에 실행할 로직을 적습니다.
        CandleService.getCandel();
        System.out.println("시간당 작업이 실행되었습니다.");
    }

    @Scheduled(cron = "0 50 8 * * ?") // 매일 8시 50분 실행
    public void dailyTaskAtEightFifty() {
        TickerService.insertTicker();

    }

}
