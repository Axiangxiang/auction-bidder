package com.blackhorse.auction.repository;

import com.blackhorse.auction.repository.entity.BidderContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidderContractRepository extends JpaRepository<BidderContract,Long> {
    BidderContract findByContractNo(String contractNo);
}
