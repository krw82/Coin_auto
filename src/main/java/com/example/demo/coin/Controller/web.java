package com.example.demo.coin.Controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.coin.Service.CandleService;

import okhttp3.Request;

@Controller
public class web {

    @Autowired
    CandleService CandleService;

    @GetMapping("/")
    public String tes2(Model model, @RequestParam Map<String, Object> interval) throws IOException {
        model.addAttribute("list", CandleService.selectCalcList(interval));
        return "html5up/index";
    }

    @GetMapping("/detail")
    public String test2(Model model, @RequestParam Map<String, Object> params) throws IOException {
        model.addAttribute("vo", CandleService.selectCoinDetail(params));
        return "html5up/detail";
    }

}
