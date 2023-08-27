package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.coin.BinanceApi.BinanceBc;

import com.example.demo.coin.TA.Bridge;
import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.Vo.TickerVo;
import com.example.demo.coin.comm.Util;

@Controller
@RequestMapping("/api")
public class coin {

    @Autowired
    BinanceBc bC;

    @GetMapping("/kim")
    public String test() throws IOException {
        int j = 0;
        List<TickerVo> TickerList = Util.TickerToVo(bC.getTicker());

        for (TickerVo i : TickerList) {
            j++;
            List<CandleVo> CandleList = Util.PriceToVo(bC.getCandle(i.getSymbol(), "4h", 200));
            Bridge.PythonTa(CandleList);
            if (j == 10) {
                break;

            }
        }

        return "candles";
    }

}
