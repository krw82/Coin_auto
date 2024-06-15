package com.coin.ta.BinanceService.candle.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coin.ta.BinanceService.candle.Entity.AnalysisEntity;

@Repository
public interface AnalysisRepository extends JpaRepository<AnalysisEntity, Long> {
    // @Query("SELECT a FROM AnalysisEntity a WHERE EXTRACT(HOUR FROM a.regDtm) =
    // :hour AND a.coinAnalyze!=0")
    // List<AnalysisEntity> findByHour(@Param("hour") int hour);

}
