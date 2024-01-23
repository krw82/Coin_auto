package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@MapperScan("com.example.demo.coin.Mapper")
public class CoinAutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinAutoApplication.class, args);

	}

}
