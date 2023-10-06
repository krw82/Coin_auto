package com.example.demo.kakao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.coin.Vo.TickerAnalysisVo;

@Component
public class kakaoApi {
    public static String accecs_token = "";

    public void sendMessage(String accecs_token, List<TickerAnalysisVo> calcLsit) {
        HttpURLConnection con = null;
        try {
            if (accecs_token.equals("")) {
                throw new Exception("This is a checked exception.");
            }

            // API URL 설정
            URL url = new URL("https://kapi.kakao.com/v2/api/talk/memo/default/send");
            con = (HttpURLConnection) url.openConnection();

            // HTTP 메소드 설정
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + accecs_token);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String message = "";
            for (TickerAnalysisVo item : calcLsit) {
                message += item.getSymbol() + "  calc" + item.getCalc() + "\\n";
            }
            // 요청 본문 (payload) 설정
            String payload = "template_object=";
            payload += "{\"object_type\":\"text\",\"text\":\"" + message
                    + "\",\"link\":{\"web_url\":\"https://google.com\"}}";
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 응답 받기
            int code = con.getResponseCode();

            StringBuilder response = new StringBuilder();
            InputStream inputStream;

            if (code == 200) { // 성공
                inputStream = con.getInputStream();
            } else { // 실패
                inputStream = con.getErrorStream();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            br.close();

            if (code == 200) {
                System.out.println("Message sent: " + response.toString());
            } else {
                System.out.println("Failed to send message: " + code);
                System.out.println("Response Message: " + response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

}
