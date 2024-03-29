package com.example.demo.coin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.coin.Entity.TickerEntity;

public interface TickerRepository extends JpaRepository<TickerEntity, Long> {

}
