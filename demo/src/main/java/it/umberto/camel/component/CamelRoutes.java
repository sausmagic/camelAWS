package it.umberto.camel.component;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {
	
	private static final Logger LOG = LoggerFactory.getLogger(CamelRoutes.class);
	@Override
	public void configure() throws Exception {
		from("direct:firstRoute").log("Camel body: ${body}");
		LOG.info("UMBERTO invocata rotta direct:firstRoute");
	}
}