package com.example.demo.coin.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.coin.Service.CandleService;
import com.example.demo.coin.Service.TickerServic;
import com.example.demo.kakao.kakaoApi;

@Controller
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
    public void candles() throws IOException {
        System.out.println("calc Start");
        CandleService.getCandel();
        System.out.println("calc End");
        return;
    }

}
