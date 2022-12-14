package com.blackhorse.auction.service;

import com.blackhorse.auction.client.AuctionStorageClient;
import com.blackhorse.auction.client.PayClient;
import com.blackhorse.auction.controller.DTO.FinalPayRequestDTO;
import com.blackhorse.auction.enums.PayType;
import com.blackhorse.auction.exception.BusinessException;
import com.blackhorse.auction.message.TakeGoodsMessageSender;
import com.blackhorse.auction.model.PayRequest;
import com.blackhorse.auction.model.TakeGoodsMessage;
import com.blackhorse.auction.repository.BidderContractRepository;
import com.blackhorse.auction.repository.BidderPayRecordRepository;
import com.blackhorse.auction.repository.entity.BidderContract;
import com.blackhorse.auction.repository.entity.BidderPayRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class FinalPayService {
    private final BidderContractRepository bidderContractRepository;
    private final BidderPayRecordRepository bidderPayRecordRepository;
    private final PayClient payClient;
    private final TakeGoodsMessageSender takeGoodsMessageSender;
    private final AuctionStorageClient auctionStorageClient;

    public void payFinalAmount(String bid, FinalPayRequestDTO finalPayRequestDTO) {
        BidderContract bidderContract = bidderContractRepository.findByContractNo(bid);
        if (bidderContract != null) {
            if (bidderContract.getFinalAmount().compareTo(finalPayRequestDTO.getFinalAmount()) != 0) {
                throw new BusinessException("amount not match");
            }
            pay(finalPayRequestDTO.getFinalAmount());
            savePayRecord(PayType.FINAL, finalPayRequestDTO.getFinalAmount(), bid);
            takeGoods(bidderContract.getContractNo());
        }
    }

    private void pay(BigDecimal amount) {
        PayRequest payRequest = new PayRequest();
        payRequest.setType("wechat");
        payRequest.setAmount(amount);
        payClient.pay(payRequest);
    }

    private void savePayRecord(PayType payType, BigDecimal amount, String contractNo) {
        BidderPayRecord bidderPayRecord = BidderPayRecord.builder()
                .payType(payType)
                .amount(amount)
                .contractNo(contractNo)
                .build();
        bidderPayRecordRepository.save(bidderPayRecord);
    }

    private void takeGoods(String auctionStorageContractNo) {
        try {
            auctionStorageClient.takeGoods(auctionStorageContractNo);
        } catch (Exception e) {
            log.error("call take goods api error: {}", e.getMessage(), e);
            takeGoodsMessageSender.send(new TakeGoodsMessage(auctionStorageContractNo));
        }
    }
}
