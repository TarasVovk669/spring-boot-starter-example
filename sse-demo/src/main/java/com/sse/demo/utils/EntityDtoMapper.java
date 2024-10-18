package com.sse.demo.utils;

import com.sse.demo.domain.CryptoTransaction;
import com.sse.demo.dto.CryptoTransactionDTO;

public class EntityDtoMapper {

    public static CryptoTransaction toEntity(CryptoTransactionDTO dto) {
        return CryptoTransaction.builder()
                .type(dto.getType())
                .cryptoFrom(dto.getCryptoFrom())
                .cryptoTo(dto.getCryptoTo())
                .date(dto.getDate())
                .quantity(dto.getQuantity())
                .build();
    }

    public static CryptoTransactionDTO toDto(CryptoTransaction entity) {
        return CryptoTransactionDTO.builder()
                .type(entity.getType())
                .cryptoFrom(entity.getCryptoFrom())
                .cryptoTo(entity.getCryptoTo())
                .date(entity.getDate())
                .quantity(entity.getQuantity())
                .build();
    }
}
