package com.ilievsoft.javaxml.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class JaxbConfig {

	@Bean
	public Jaxb2Marshaller createJaxb2Marshaller(@Value("${context.path}") final String contextPath,
								@Value("${schema.location}") final Resource schemaResource) { 
		Jaxb2Marshaller marschaller = new Jaxb2Marshaller();
		marschaller.setContextPath(contextPath);
		marschaller.setSchema(schemaResource);
		
		Map<String, Object> prop = new HashMap<>();
		prop.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		marschaller.setMarshallerProperties(prop);
		return marschaller;
	}
}
