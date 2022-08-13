package com.blackhorse.auction.service;

import com.blackhorse.auction.client.PayClient;
import com.blackhorse.auction.controller.DTO.FinalPayRequestDTO;
import com.blackhorse.auction.message.TakeGoodsMessageSender;
import com.blackhorse.auction.repository.BidderContractRepository;
import com.blackhorse.auction.repository.BidderPayRecordRepository;
import com.blackhorse.auction.repository.entity.BidderContract;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FinalPayServiceTest {

    @Test
    void should_success_to_pay_final_amount_when_pay_final_amount_given_correct_amount() {
        BidderContractRepository bidderContractRepository = Mockito.mock(BidderContractRepository.class);
        BidderPayRecordRepository bidderPayRecordRepository = Mockito.mock(BidderPayRecordRepository.class);
        PayClient payClient = Mockito.mock(PayClient.class);
        TakeGoodsMessageSender takeGoodsMessageSender = Mockito.mock(TakeGoodsMessageSender.class);
        FinalPayService finalPayService = new FinalPayService(bidderContractRepository, bidderPayRecordRepository, payClient, takeGoodsMessageSender);
        BidderContract bidderContract = BidderContract.builder()
                .contractNo("111")
                .finalAmount(new BigDecimal(50000))
                .auctionStorageContractNo("222")
                .build();
        Mockito.when(bidderContractRepository.findByContractNo(any())).thenReturn(bidderContract);
        finalPayService.payFinalAmount(bidderContract.getContractNo(), FinalPayRequestDTO.builder().finalAmount(bidderContract.getFinalAmount()).build());
        verify(bidderPayRecordRepository, times(1)).save(any());
        verify(takeGoodsMessageSender, times(1)).send(any());
    }
}
