package com.sse.demo.dto;

import com.sse.demo.domain.Cryptocurrency;
import com.sse.demo.domain.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CryptoTransactionDTO {

    private TransactionType type;
    private Cryptocurrency cryptoFrom;
    private Cryptocurrency cryptoTo;
    private BigDecimal quantity;
    private LocalDateTime date;
}
