package com.blackhorse.auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuctionBidderApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionBidderApplication.class, args);
	}

}
