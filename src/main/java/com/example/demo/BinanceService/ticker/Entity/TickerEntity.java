
package com.example.demo.BinanceService.ticker.Entity;

import java.util.Date;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Table(name = "coin_ticker")
public class TickerEntity {

    @Id
    private String symbol;

    private String priceChange;
    private String priceChangePercent;
    private String weightedAvgPrice;
    private String prevClosePrice;
    private String lastPrice;
    private String lastQty;
    private String bidPrice;
    private String bidQty;

    private String askPrice;
    private String askQty;
    private String openPrice;
    private String highPrice;
    private String lowPrice;
    private String volume;
    private String quoteVolume;

    private long openTime;
    private long closeTime;

    private long firstId;
    private long lastId;
    private long count;

}