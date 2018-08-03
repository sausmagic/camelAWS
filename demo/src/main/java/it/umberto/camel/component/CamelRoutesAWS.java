package it.umberto.camel.component;

import java.util.Map;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
		
		from("direct:testReadHeader")
		.log("leggo dall'exchange l'header inviato dal producer")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				
				Map<String, Object> headers = exchange.getIn().getHeaders();
				Set<String> keys = headers.keySet(); 
				
				for (String header : keys) {
					LOG.info("Header: key: {} value: {}",new Object[] {header, headers.get(header)});
				}
					
				LOG.info("Il body è: {}",exchange.getIn().getBody());
				
			}
		});


	}
}