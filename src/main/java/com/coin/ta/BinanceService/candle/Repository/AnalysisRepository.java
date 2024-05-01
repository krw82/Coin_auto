package com.coin.ta.BinanceService.candle.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coin.ta.BinanceService.candle.Entity.AnalysisEntity;

public interface AnalysisRepository extends JpaRepository<AnalysisEntity, Long> {

}
