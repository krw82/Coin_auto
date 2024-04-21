package com.example.demo.BinanceService.candle.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.BinanceService.candle.Entity.AnalysisEntity;

public interface AnalysisRepository extends JpaRepository<AnalysisEntity, Long> {

}
