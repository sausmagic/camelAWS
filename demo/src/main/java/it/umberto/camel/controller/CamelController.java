package it.umberto.camel.controller;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camelRest")
public class CamelController {

	private static final Logger LOG = LoggerFactory.getLogger(CamelController.class);
	
	@Autowired
	ProducerTemplate producerTemplate;
	
	@Autowired
	ConsumerTemplate consumerTemplate; 

	CamelContext camelContext;
	
	@Autowired
	public CamelController(CamelContext camelContext) {
		this.camelContext = camelContext;
		this.camelContext.setTracing(true);
	}
	
	@RequestMapping(value = "/")
	public void startCamel() {
		producerTemplate.sendBody("direct:firstRoute", "Invoco la rotta camel mediante chiamata rievuta da un servizio Rest esposto");	
	}
	
	@RequestMapping(value = "/awsRead")
	@ResponseBody
	public String startCamelAWSRead() {
		
		Exchange e1 =consumerTemplate.receive("aws-sqs://{{umberto.queue.aws.name}}?accessKey={{umberto.queue.aws.accessKey}}&secretKey=RAW({{umberto.queue.aws.secretKey}})&amazonSQSEndpoint={{umberto.queue.aws.amazonSQSEndpoint}}&concurrentConsumers=1&maxMessagesPerPoll=1");
		String bodyMessage = (String)e1.getIn().getBody();
		LOG.info("Il body presente nel messaggio del Exchange sulla rotta è: {} ",e1.getIn().getBody());
		e1.getIn().setHeader("testHeader", "testHeader");
		producerTemplate.send("direct:testReadHeader",e1);
		consumerTemplate.doneUoW(e1);
		
//		Exchange e =consumerTemplate.receiveNoWait("aws-sqs://{{umberto.queue.aws.name}}?accessKey={{umberto.queue.aws.accessKey}}&secretKey=RAW({{umberto.queue.aws.secretKey}})&amazonSQSEndpoint={{umberto.queue.aws.amazonSQSEndpoint}}&concurrentConsumers=1&maxMessagesPerPoll=1");
//		LOG.info("Il body presente nel messaggio del Exchange sulla rotta è: {} ",e.getIn().getBody());		
		
		return "Figa hai letto "+bodyMessage+" dalla coda di Amazon SQS";
			
	}
	
	@RequestMapping(value = "/awsWrite")
	public void startCamelAWSWrite() {
		producerTemplate.sendBody("direct:awsRouteWrite", "Invoco la rotta camel per le code SQS Amazon Scrittura");	
	}
}
