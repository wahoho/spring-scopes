package com.jyore.spring.scope.exchange.example.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.exchange.example.processor.ExchangeScopeLoggingProcessor;
import com.jyore.spring.scope.exchange.example.processor.ExchangeScopeValueSetter;
import com.jyore.spring.scope.exchange.example.processor.StringSplitterGenerator;


@Component
public class SplittingRoute extends RouteBuilder {

	@Autowired
	private ExchangeScopeValueSetter valueSetter;
	
	@Autowired
	private ExchangeScopeLoggingProcessor loggingProcessor;
	
	@Autowired
	private StringSplitterGenerator splitgen;
	
	
	@Override
	public void configure() throws Exception {
		
		from("timer://splitter?fixedRate=true&period=15000")
			.bean(valueSetter,"process")
			.bean(splitgen,"process")
			.split(body().tokenize(",")).parallelProcessing()
				.bean(loggingProcessor,"process")
		;
	}

	
}