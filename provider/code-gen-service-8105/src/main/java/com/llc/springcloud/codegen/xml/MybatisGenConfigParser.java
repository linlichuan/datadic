package com.llc.springcloud.codegen.xml;

import com.alibaba.fastjson.JSONObject;
import com.llc.springcloud.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class MybatisGenConfigParser {
	
	public static final String DEFAULT_CONFIG_TMP_PATH = "gen-config.ftl";
	private final static Logger log = LoggerFactory.getLogger(MybatisGenConfigParser.class);
	private InputStream in;
	private static MybatisGenConfigParser parser;
	private boolean hasResource = false;
	private String path;
	private XMLStreamReader reader;
	
	private MybatisGenConfigParser() {
		super();
	}
	
	public JSONObject parse(String path) {
		if (StringUtil.isBlank(path)) {
			this.path = DEFAULT_CONFIG_TMP_PATH;
			getResource(false);
		} else {
			this.path = path;
			getResource(true);
		}
		JSONObject result = new JSONObject();
		XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
		try {
			if (!hasResource || in == null) {
				return result;
			}
			reader = xmlInputFactory.createXMLStreamReader(in);
			getXmlElement(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!result.isEmpty()) {
			log.info(result.toJSONString());
		}
		return result;
	}
	
	public static void writeXml() {
		try {
//			String tmpFile = FileUtil.copyFileToTmpDir(in);
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
			XMLEventWriter writer = xmlOutputFactory.createXMLEventWriter(new FileOutputStream("F:\\1.xml"));
			XMLEventFactory xmlEventFactory = XMLEventFactory.newFactory();
			writer.add(xmlEventFactory.createStartDocument());
			writer.add(xmlEventFactory.createStartElement("", "namespaceUri", "localName"));
			writer.add(xmlEventFactory.createAttribute("localName", "value"));
			writer.add(xmlEventFactory.createEndElement("", "namespaceUri", "localName"));
			writer.add(xmlEventFactory.createEndDocument());
			writer.flush();
		} catch (XMLStreamException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void getXmlElement(JSONObject object) {
		String element = "";
		try {
			int type;
			while (reader.hasNext()) {
				type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					JSONObject json = new JSONObject();
					element = reader.getName().getLocalPart();
					int count = reader.getAttributeCount();
					if (count == 0) {
						continue;
					}
					for (int i = 0; i < count; i++) {
						json.put(reader.getAttributeName(i).getLocalPart(), reader.getAttributeValue(i));
					}
					object.put(element, json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getResource(boolean isAbsolutePath) {
		try {
			if (isAbsolutePath) {
				if (StringUtil.isBlank(path)) {
					hasResource = false;
				} else {
					in = new FileInputStream(path);
					hasResource = true;
				}
			} else {
				if(StringUtil.isBlank(path)) {
					path = DEFAULT_CONFIG_TMP_PATH;
				}
				URL url = MybatisGenConfigParser.class.getClassLoader().getResource(path);
				if (url != null) {
					in = url.openStream();
					hasResource = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			hasResource = false;
		}
	}
	
	public static MybatisGenConfigParser getInstance() {
		if (parser == null) {
			synchronized (MybatisGenConfigParser.class) {
				if (parser == null) {
					parser = new MybatisGenConfigParser();
				}
			}
		}
		return parser;
	}
	
	public static void main(String[] args) {
		System.out.println(MybatisGenConfigParser.getInstance().parse(null).toJSONString());
	}
}
