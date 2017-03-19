package com.ilievsoft.javaxml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.ilievsoft.note.Note;
	
@SpringBootApplication
public class JavaXmlApplication implements CommandLineRunner {

	public static final Logger log = LoggerFactory.getLogger(JavaXmlApplication.class);
	
	@Autowired
	Jaxb2Marshaller marshaller;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaXmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Note note = new Note();
		note.setTo("alina");
		note.setFrom("ico");
		note.setHeader("hallo");
		note.setBody("body text");
		
		StringWriter writer = new StringWriter();
		marshaller.marshal(note, new StreamResult(writer));
		
		String xml = writer.toString();
		log.info("XML: {}", xml);
		
		Note note2 = (Note) marshaller.unmarshal(new StreamSource(new StringReader(xml)));
		log.info("Note: from {}, to {}, header {}, body {}", note2.getFrom(), 
				note2.getTo(), 
				note2.getHeader(), 
				note2.getBody());
		
	}
	
	
}
