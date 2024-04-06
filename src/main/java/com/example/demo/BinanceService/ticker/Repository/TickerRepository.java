package com.example.demo.BinanceService.ticker.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.BinanceService.ticker.Entity.TickerEntity;

public interface TickerRepository extends JpaRepository<TickerEntity, String> {
    List<TickerEntity> findBySymbol(String symbol);

}