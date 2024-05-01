package com.coin.ta.BinanceService.ticker.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coin.ta.BinanceService.ticker.Entity.TickerEntity;

public interface TickerRepository extends JpaRepository<TickerEntity, String> {
    List<TickerEntity> findBySymbol(String symbol);

    List<TickerEntity> findTop40ByOrderByVolumeDesc();

}