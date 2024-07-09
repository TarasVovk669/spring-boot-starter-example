package com.tour.aggregator.touraggregator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {

    private final Integer threadCount;

    public ThreadPoolConfig(@Value("${app.thread-count:5}") Integer threadCount) {
        this.threadCount = threadCount;
    }

    @Bean(name = "tourOperatorTaskExecutor")
    public Executor tourOperatorTaskExecutor() {
        return Executors.newFixedThreadPool(threadCount);
    }
}
