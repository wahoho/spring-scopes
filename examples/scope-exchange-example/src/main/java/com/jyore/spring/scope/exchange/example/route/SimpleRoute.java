package com.jyore.spring.scope.exchange.example.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.exchange.example.processor.ValueChecker;
import com.jyore.spring.scope.exchange.example.processor.ValueSetter;


@Component
public class SimpleRoute extends RouteBuilder {

	@Autowired
	private ValueSetter valueSetter;
	
	@Autowired
	private ValueChecker valueChecker;
	
	@Override
	public void configure() throws Exception {
		from("timer://simple?fixedRate=true&period=5000")
			.bean(valueSetter,"process")
			.bean(valueChecker,"process")
		;
	}
}
