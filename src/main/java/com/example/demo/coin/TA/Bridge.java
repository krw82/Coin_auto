package com.example.demo.coin.TA;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.coin.Vo.CandleVo;
import com.example.demo.coin.Vo.TickerAnalysisVo;
import com.example.demo.coin.comm.Util;
import com.google.gson.Gson;

public class Bridge {

    public static void PythonTa(List<CandleVo> list, String symbol) {

        String json = Util.gsonGetInstance().toJson(list);
        String jsonFilename = "data.json";
        String pythonPath = "C:\\Users\\AgencyPro_112\\Desktop\\krw_82\\Coin_auto\\venv\\Scripts\\python";
        // String pythonPath =
        // "/Users/jeong-woncheol/eclipse-workspace_sec/demo/Coin_auto/venv/bin/python";

        String[] command = new String[] { pythonPath, "src/main/java/com/example/demo/coin/TA/BasicTa.py",
                jsonFilename, symbol };

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        try {
            FileWriter file = new FileWriter(jsonFilename);
            file.write(json);
            file.flush();
            file.close();

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorBr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;// 오류 스트림에서의 메시지 가져오기 (에러가 발생한 경우)

            while ((line = errorBr.readLine()) != null) {
                System.err.println(line);
                // throw new Exception();
            }

            Gson gson = Util.gsonGetInstance();
            List<TickerAnalysisVo> VoList = new ArrayList<TickerAnalysisVo>();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                VoList.add(gson.fromJson(line, TickerAnalysisVo.class));
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;

    }

}
