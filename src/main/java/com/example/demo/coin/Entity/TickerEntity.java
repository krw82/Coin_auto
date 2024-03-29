package com.example.demo.coin.Entity;

import java.util.Date;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class TickerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "coin_ticker_id_seq", allocationSize = 1)
    int id;
    @Column
    String symbol;
    @Column
    String pricechange;
    @Column
    String pricechangepercent;
    @Column
    String weightedavgprice;
    @Column
    String prevcloseprice;
    @Column
    String lastprice;
    @Column
    String lastqty;
    @Column
    String bidprice;
    @Column
    String bidqty;
    @Column
    String askprice;
    @Column
    String askqty;
    @Column
    String openprice;
    @Column
    String highprice;
    @Column
    String lowprice;
    @Column
    String volume;
    @Column
    String quotevolume;
    @Column
    int opentime;
    @Column
    int closetime;
    @Column
    int firstid;
    @Column
    int lastid;
    @Column
    int count;
    @Column
    Date regdtm;

}