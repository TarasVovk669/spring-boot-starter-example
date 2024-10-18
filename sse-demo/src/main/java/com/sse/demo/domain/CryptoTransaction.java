package com.sse.demo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Table("crypto_transaction")
public class CryptoTransaction {
    @Id
    private Long id;
    private TransactionType type;
    private Cryptocurrency cryptoFrom;
    private Cryptocurrency cryptoTo;
    private BigDecimal quantity;
    private LocalDateTime date;
}

