package com.example.demo.coin.Controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.coin.Service.CandleService;
import com.example.demo.coin.Service.TickerServic;

@Controller
public class web {

    @Autowired
    TickerServic TickerService;

    @Autowired
    CandleService CandleService;

    @GetMapping("/home")
    public String test(Model model) throws IOException {
        System.out.println("sadsadsadsadasdasd");
        return "html5up/index";
    }

}
