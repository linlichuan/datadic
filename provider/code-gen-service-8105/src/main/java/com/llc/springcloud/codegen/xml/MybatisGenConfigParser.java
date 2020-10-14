package com.llc.springcloud.codegen.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.net.URL;

@Component
public class MybatisGenConfigParser {
	
	private final static Logger log = LoggerFactory.getLogger(MybatisGenConfigParser.class);
	
	public void parse(String path) {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
		InputStream in;
		try {
			URL url = MybatisGenConfigParser.class.getClassLoader().getResource(path);
			if (url == null) {
				return;
			}
			in = url.openStream();
			XMLStreamReader xmlReader = xmlInputFactory.createXMLStreamReader(in);
			int type;
			while (xmlReader.hasNext()) {
				type = xmlReader.next();
				log.info("type is {}", type);
				switch (type) {
					case XMLStreamConstants.START_ELEMENT:
					case XMLStreamConstants.END_ELEMENT:
						log.info(xmlReader.getName().toString());
						break;
					case XMLStreamConstants.CHARACTERS:
						log.info(xmlReader.getText());
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
