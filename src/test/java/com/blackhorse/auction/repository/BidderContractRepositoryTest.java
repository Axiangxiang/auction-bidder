package com.blackhorse.auction.repository;

import com.blackhorse.auction.configuration.TestBase;
import com.blackhorse.auction.repository.entity.BidderContract;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BidderContractRepositoryTest extends TestBase {
    @Autowired
    BidderContractRepository bidderContractRepository;

    @Test
    public void should_get_correct_bidder_contract_by_contract_no(){
        BidderContract bidderContract1 = BidderContract.builder()
                .contractNo("111")
                .finalAmount(new BigDecimal(1000))
                .auctionStorageContractNo("123")
                .build();
        BidderContract bidderContract2 = BidderContract.builder()
                .contractNo("222")
                .finalAmount(new BigDecimal(1000))
                .auctionStorageContractNo("456")
                .build();
        bidderContractRepository.saveAll(List.of(bidderContract1, bidderContract2));
        BidderContract searchedBidderContract = bidderContractRepository.findByContractNo("111");
        assertEquals(bidderContract1.getContractNo(), searchedBidderContract.getContractNo());
        assertEquals(bidderContract1.getFinalAmount().setScale(2), searchedBidderContract.getFinalAmount());
        assertEquals(bidderContract1.getAuctionStorageContractNo(), searchedBidderContract.getAuctionStorageContractNo());
    }
}
