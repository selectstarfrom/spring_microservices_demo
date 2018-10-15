package com.playground.springms.forexapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playground.springms.forexapp.jpaentity.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	ExchangeValue findByFromAndTo(String from, String to);
}