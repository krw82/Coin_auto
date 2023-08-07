package com.example.demo.coin.TA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.comm.Util;

public class Bridge {

    public static void PythonTa(List<CandleVo> list) {

        String json = Util.gsonGetInstance().toJson(list);

        String pythonPath = "/Users/jeong-woncheol/eclipse-workspace_sec/demo/Coin_auto/venv/bin/python";
        String[] command = new String[] { pythonPath, "src/main/java/com/example/demo/coin/TA/BasicTa.py", json };

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorBr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;// 오류 스트림에서의 메시지 가져오기 (에러가 발생한 경우)

            while ((line = errorBr.readLine()) != null) {
                System.err.println(line);
                // throw new Exception();
            }
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;

    }

}
