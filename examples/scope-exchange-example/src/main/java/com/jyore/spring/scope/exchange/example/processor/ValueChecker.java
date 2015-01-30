package com.jyore.spring.scope.exchange.example.processor;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.exchange.example.beans.ExchangeScopedTest;


@Component
public class ValueChecker {

	private static final Logger log = LoggerFactory.getLogger(ValueChecker.class);
	
	@Autowired
	private ExchangeScopedTest bean;
	
	public void process(Exchange exchange) {
		log.info("Bean Value is {}", (Integer) bean.get());
	}
}
