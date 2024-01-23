package com.example.demo.coin.Controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.coin.Service.CandleService;
import com.example.demo.coin.Service.TickerServic;

import okhttp3.Request;

@Controller
public class web {

    @Autowired
    CandleService CandleService;

    @GetMapping("/")
    public String tes2(Model model, @RequestParam String params) throws IOException {
        model.addAttribute("list", CandleService.selectCalcList(params));
        return "html5up/index";
    }

}
