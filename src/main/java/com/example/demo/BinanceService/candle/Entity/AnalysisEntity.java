package com.example.demo.BinanceService.candle.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coin_analysis")
public class AnalysisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PostgreSQL의 SERIAL과 호환
    private Long seqNo;

    private String symbol;
    private int rsiBet;
    private int mfiCg;
    private int maCross30;
    private int maCross180;
    private int candleDoji;
    private int checkDivergenceRsi14;
    private int checkDivergenceMfi14;
    private int candleHammer;
    private int candleInverseHammer;
    private int macdSignal;
    private int calc;
    private String interval;
    private int coinAnalyze;
}
