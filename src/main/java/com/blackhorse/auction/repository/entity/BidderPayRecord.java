package com.blackhorse.auction.repository.entity;

import com.blackhorse.auction.enums.PayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "bidder_pay_record")
public class BidderPayRecord {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String contractNo;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PayType payType;
}
