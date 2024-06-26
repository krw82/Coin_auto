package com.coin.ta.BinanceService.candle.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "analysis")
@EntityListeners(AuditingEntityListener.class)
public class AnalysisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coin_analysis_seq")
    @SequenceGenerator(name = "coin_analysis_seq", sequenceName = "coin_analysis_seqno_seq", allocationSize = 1)
    @Column(name = "seq_no", nullable = false)
    private Integer seqNo;

    @Column(name = "symbol", nullable = false, length = 255)
    private String symbol;

    @Column(name = "rsi_bet")
    private Integer rsiBet;

    @Column(name = "mfi_cg")
    private Integer mfiCg;

    @Column(name = "ma_cross30")
    private Integer maCross30;

    @Column(name = "ma_cross180")
    private Integer maCross180;

    @Column(name = "candle_doji")
    private Integer candleDoji;

    @Column(name = "check_divergence_rsi14")
    private Integer checkDivergenceRsi14;

    @Column(name = "check_divergence_mfi14")
    private Integer checkDivergenceMfi14;

    @Column(name = "candle_hammer")
    private Integer candleHammer;

    @Column(name = "candle_inverse_hammer")
    private Integer candleInverseHammer;

    @Column(name = "macd_signal")
    private Integer macdSignal;

    @Column(name = "calc")
    private Integer calc;

    @Column(name = "chart_frame", length = 50)
    private String interval;

    @Column(name = "coin_analyze")
    private Integer coinAnalyze;

    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime lastModifiedDate;
}
