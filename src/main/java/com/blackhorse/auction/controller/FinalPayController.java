package com.blackhorse.auction.controller;

import com.blackhorse.auction.controller.DTO.FinalPayRequestDTO;
import com.blackhorse.auction.controller.DTO.FinalPayResponseDTO;
import com.blackhorse.auction.controller.DTO.ResponseDTO;
import com.blackhorse.auction.exception.BusinessException;
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
    public ResponseEntity<ResponseDTO<FinalPayResponseDTO>> finalPay(@PathVariable String bid, @RequestBody FinalPayRequestDTO finalPayRequestDTO) {
        FinalPayResponseDTO finalPayResponseDTO = new FinalPayResponseDTO();
        try {
            finalPayService.payFinalAmount(bid, finalPayRequestDTO);
        } catch (BusinessException e) {
            finalPayResponseDTO.setCode("fail");
            finalPayResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDTO<FinalPayResponseDTO>(400, finalPayResponseDTO));
        } catch (Exception e) {
            finalPayResponseDTO.setCode("fail");
            finalPayResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseDTO<FinalPayResponseDTO>(500, finalPayResponseDTO));
        }
        finalPayResponseDTO.setCode("success");
        finalPayResponseDTO.setMessage("");
        return ResponseEntity.ok().body(new ResponseDTO<FinalPayResponseDTO>(200, finalPayResponseDTO));
    }
}
