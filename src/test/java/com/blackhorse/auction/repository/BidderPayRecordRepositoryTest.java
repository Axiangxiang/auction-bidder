package com.blackhorse.auction.repository;

import com.blackhorse.auction.configuration.TestBase;
import com.blackhorse.auction.enums.PayType;
import com.blackhorse.auction.repository.entity.BidderContract;
import com.blackhorse.auction.repository.entity.BidderPayRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BidderPayRecordRepositoryTest extends TestBase {
    @Autowired
    BidderPayRecordRepository bidderPayRecordRepository;

    @Test
    public void should_save_correct_bidder_pay_record(){
        BidderPayRecord bidderPayRecord = BidderPayRecord.builder()
                .contractNo("111")
                .amount(new BigDecimal(1000))
                .payType(PayType.FINAL)
                .build();
        bidderPayRecordRepository.save(bidderPayRecord);
        List<BidderPayRecord> bidderPayRecords = bidderPayRecordRepository.findAll();
        assertEquals(1, bidderPayRecords.size());
        assertEquals(bidderPayRecord.getContractNo(), bidderPayRecords.get(0).getContractNo());
        assertEquals(bidderPayRecord.getAmount().setScale(2), bidderPayRecords.get(0).getAmount());
        assertEquals(PayType.FINAL, bidderPayRecords.get(0).getPayType());
    }
}
