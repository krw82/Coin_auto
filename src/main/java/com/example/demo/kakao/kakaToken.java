package com.example.demo.kakao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class kakaToken {
    public String getAccessToken(String authorize_code) {
        String access_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본 설정
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요한 파라미터를 OutputStream으로 넘김
            String paramStr = "grant_type=authorization_code&client_id=" + "e60529beba7c326ce54d1307631d7d7f"
                    + "&redirect_uri="
                    + "http://131.186.30.55/:8080/kakao/token2" + "&code=" + authorize_code;
            OutputStream os = conn.getOutputStream();
            os.write(paramStr.getBytes());
            os.flush();

            // 요청 코드가 200이면 정상 호출
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                String result = "";

                while ((line = br.readLine()) != null) {
                    result += line;
                }

                // 받아온 결과에서 access_token을 추출
                // JSON 파싱 라이브러리를 사용하면 더 쉽게 할 수 있습니다.
                access_Token = result; // 실제 구현시에는 JSON 파싱 후 "access_token" 항목의 값을 저장하게 됩니다.
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return access_Token;
    }
}
