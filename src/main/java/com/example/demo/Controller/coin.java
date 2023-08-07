package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.coin.BinanceApi.BinanceCandles;
import com.example.demo.coin.TA.BasicTa;
import com.example.demo.coin.TA.Bridge;
import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.comm.Util;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;

@Controller
@RequestMapping("/api")
public class coin {

    @Autowired
    BinanceCandles bC;

    @GetMapping("/kim")
    public String test() throws IOException {
        System.out.println("sad");

        List<CandleVo> list = Util.JsonToVo(bC.getCandles("BTCUSDT", "1h", 200));
        BasicTa ta = new BasicTa(list);

        System.out.println(ta.Ma(14, 0));
        Bridge.PythonTa(list);
        // System.out.println(list.get(0).getClosePrice());

        return "candles";
    }

}
