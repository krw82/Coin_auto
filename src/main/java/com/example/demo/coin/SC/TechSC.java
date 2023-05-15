package com.example.demo.coin.SC;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.coin.UpbitApi;
import com.example.demo.coin.Bc.Technical;
import com.example.demo.coin.Bc.Util;
import com.example.demo.coin.Vo.CandleVo;

@Controller
public class TechSC {
	@GetMapping("/ex06")
	public  void ex06() throws IOException {
		
		UpbitApi u = new UpbitApi();
		List<CandleVo> list =Util.JsonToVo(u.getApi());
		
		
		Technical tech = new Technical(list);
		System.out.println(tech.calculateRSI(7));
		
		
		return;
	}

}
