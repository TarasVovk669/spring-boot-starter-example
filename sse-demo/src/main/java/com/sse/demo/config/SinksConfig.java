package com.sse.demo.config;

import com.sse.demo.dto.CryptoTransactionDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class SinksConfig {

    @Bean
    public Sinks.Many<CryptoTransactionDTO> sink() {
        return Sinks.many().replay().limit(1);
    }
}
