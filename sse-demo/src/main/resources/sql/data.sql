CREATE TABLE CRYPTO_TRANSACTION
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    type        VARCHAR(4),
    crypto_from VARCHAR(10),
    crypto_to   VARCHAR(10),
    quantity    DECIMAL(19, 4) NOT NULL,
    date        TIMESTAMP
);