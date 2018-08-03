package it.umberto.camel.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

	private static final Logger logger = LoggerFactory.getLogger(Config.class);
	
	@Bean(name = "mapperProp")
	public PropertiesFactoryBean mapper() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		
		bean.setLocation(new ClassPathResource("application.properties"));
		
		logger.info("Bean con file di properties caricato....");
		return bean;
	}
	
	
}
