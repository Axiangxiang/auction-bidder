package com.blackhorse.auction.client;


import com.blackhorse.auction.model.PayRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${feign.pay.name}", url = "${feign.pay.url}", primary = false)
public interface PayClient {

    @PostMapping("/pay")
    void pay(@RequestBody PayRequest payRequest);
}
