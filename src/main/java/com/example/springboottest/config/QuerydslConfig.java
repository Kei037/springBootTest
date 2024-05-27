package com.example.springboottest.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QuerydslConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() { return new JPAQueryFactory(entityManager); }

}
