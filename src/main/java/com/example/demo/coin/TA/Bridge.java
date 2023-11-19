package com.example.demo.coin.TA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.Vo.TickerAnalysisVo;
import com.example.demo.coin.comm.Util;
import com.google.gson.Gson;

public class Bridge {

    public static List<TickerAnalysisVo> PythonTa(List<CandleVo> list, String symbol, String interval) {

        String json = Util.gsonGetInstance().toJson(list);
        List<TickerAnalysisVo> VoList = new ArrayList<TickerAnalysisVo>();
        // String pythonPath =
        // "/Users/jeong-woncheol/eclipse-workspace_sec/demo/Coin_auto/venv/bin/python";
        // String pythonPath =
        // "/Users/jeong-woncheol/eclipse-workspace_sec/demo/Coin_auto/venv/bin/python";
        // // 개발
        String pythonPath = "/home/opc/my_venv/bin/python"; // 운영
        // String TaPath = "src/main/java/com/example/demo/coin/TA/BasicTa.py";// 개발
        String TaPath = "/home/opc/BasicTa.py"; // 운영

        String[] command = new String[] { pythonPath, TaPath,
                json, symbol, interval };

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

            Gson gson = Util.gsonGetInstance();

            while ((line = reader.readLine()) != null) {

                VoList.add(gson.fromJson(line, TickerAnalysisVo.class));
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return VoList;

    }

}
