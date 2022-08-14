package com.blackhorse.auction.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${feign.auction-storage.name}", url = "${feign.auction-storage.url}", primary = false)
public interface AuctionStorageClient {
    @PostMapping("/auction-storage-contract/{sid}/take-out-request")
    void takeGoods(@PathVariable("sid") String sid);
}
