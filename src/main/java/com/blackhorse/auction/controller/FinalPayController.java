package com.blackhorse.auction.controller;

import com.blackhorse.auction.controller.DTO.FinalPayRequestDTO;
import com.blackhorse.auction.controller.DTO.FinalPayResponseDTO;
import com.blackhorse.auction.service.FinalPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bidder-contract")
public class FinalPayController {
    @Autowired
    private FinalPayService finalPayService;

    @PostMapping("/{bid}/final-payment-request/confirmation")
    public ResponseEntity<FinalPayResponseDTO<String>> finalPay(@PathVariable String bid, @RequestBody FinalPayRequestDTO finalPayRequestDTO) {
        finalPayService.finalPay(bid, finalPayRequestDTO);
        return ResponseEntity.ok().body(new FinalPayResponseDTO<String>(200, ""));
    }
}
