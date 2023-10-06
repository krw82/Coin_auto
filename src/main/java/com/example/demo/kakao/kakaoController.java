package com.example.demo.kakao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.view.RedirectView;
import com.example.demo.coin.comm.Util;

@Controller
@RequestMapping("/kakao")
public class kakaoController {
    @Autowired
    kakaToken kakaToken;

    @GetMapping("/token")
    public RedirectView test() throws IOException {

        String restApiKey = "";

        String redirectUri = "http://localhost:8080/kakao/token2";
        String url = "https://kauth.kakao.com/oauth/authorize?client_id=" + restApiKey + "&redirect_uri=" + redirectUri
                + "&response_type=code&scope=talk_message";
        return new RedirectView(url);
    }

    @GetMapping("/token2")
    public void test(@RequestParam String code) throws IOException {
        String access_token = Util.kakaoVo(kakaToken.getAccessToken(code)).getAccess_token();
        kakaoApi.accecs_token = access_token;

        return;
    }

}
