package com.example.demo.coin.Controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.coin.Service.CandleService;
import com.example.demo.coin.Service.TickerServic;
import com.example.demo.coin.Vo.TickerAnalysisVo;
import com.example.demo.kakao.kakaoApi;

@RestController
@RequestMapping("/api")
public class coin {

    @Autowired
    TickerServic TickerService;

    @Autowired
    CandleService CandleService;

    @Autowired
    kakaoApi KakaoApi;

    @GetMapping("/ticker")
    public void test() throws IOException {

        TickerService.insertTicker();

        return;
    }

    @GetMapping("/calc")
    public void candles(@RequestParam String interval) throws IOException {
        System.out.println("calc Start");
        CandleService.getCandel(interval);
        System.out.println("calc End");
        return;
    }

    @GetMapping("/getAceecs")
    public void getAceecs() throws IOException {
        System.out.println(KakaoApi.accecs_token);
        return;
    }

}
