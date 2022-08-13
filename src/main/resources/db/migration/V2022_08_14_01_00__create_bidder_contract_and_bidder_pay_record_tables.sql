CREATE TABLE IF NOT EXISTS bidder_contract
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    contract_no VARCHAR(50) NOT NULL,
    auction_storage_contract_no VARCHAR(50) NOT NULL,
    final_amount decimal(10, 2),
    bail_amount decimal(10, 2),
    created_at TIMESTAMP default current_timestamp,
);

CREATE TABLE IF NOT EXISTS bidder_pay_record
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    contract_no VARCHAR(50) NOT NULL,
    pay_type VARCHAR(10),
    amount decimal(10, 2)
    created_at TIMESTAMP default current_timestamp,
    );
