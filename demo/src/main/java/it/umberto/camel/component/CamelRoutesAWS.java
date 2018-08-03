package it.umberto.camel.component;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class CamelRoutesAWS extends RouteBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(CamelRoutesAWS.class);

	@Value("#{mapperProp['umberto.queue.aws.name']}")
	private String codaAmazon;

	@Override
	public void configure() throws Exception {
		LOG.info("UMBERTO invocata rotta della classe: {} e coda: {}", this.getClass().getName(),codaAmazon);

			
//		from("aws-sqs://{{umberto.queue.aws.name}}?accessKey={{umberto.queue.aws.accessKey}}&amp;secretKey=RAW({{umberto.queue.aws.secretKey}})&amp;amazonSQSEndpoint={{umberto.queue.aws.amazonSQSEndpoint}}&amp;concurrentConsumers=1&amp;maxMessagesPerPoll=1");

	}
}