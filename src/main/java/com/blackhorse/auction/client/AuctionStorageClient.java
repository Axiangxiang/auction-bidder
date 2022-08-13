package com.blackhorse.auction.client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${feign.auction-storage.name}", url = "${feign.auction-storage.url}", primary = false)
public interface AuctionStorageClient {

}
