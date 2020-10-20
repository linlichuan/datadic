package com.llc.springcloud.codegen.tpl;

import com.llc.springcloud.web.exception.NoWriterException;
import com.llc.springcloud.web.exception.TemplateProcessException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TemplateUtil {
	private static final String TEMPLATE_DIR = "classpath:template/";
	private static final Logger log = LoggerFactory.getLogger(TemplateUtil.class);
	
	private static TemplateUtil INSTANCE;
	private Configuration config;
	private Writer writer;
	
	private TemplateUtil() {
		super();
	}
	
	public static TemplateUtil getInstance() {
		if (INSTANCE == null) {
			synchronized (TemplateUtil.class) {
				if (INSTANCE == null) {
					INSTANCE = new TemplateUtil();
					Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
					try {
						cfg.setDirectoryForTemplateLoading(ResourceUtils.getFile(TEMPLATE_DIR));
						cfg.setDefaultEncoding("utf-8");
						cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
					} catch (IOException e) {
						e.printStackTrace();
					}
					INSTANCE.setConfig(cfg);
				}
			}
		}
		return INSTANCE;
	}
	
	private void setConfig(Configuration cfg) {
		this.config = cfg;
	}
	
	public TemplateUtil out(Writer writer) {
		this.writer = writer;
		return this;
	}
	
	public void render(Object obj, String template) {
		if (writer == null) {
			throw new NoWriterException("没有writer");
		}
		try {
			Template tpl = this.config.getTemplate(template);
			tpl.process(obj, writer);
		} catch (IOException | TemplateException e) {
			throw new TemplateProcessException(e.getMessage());
		} finally {
			this.writer = null;
		}
	}
	
	public File renderToTempFile(Object obj, String template) {
		File file = null;
		if (writer == null) {
			try {
				file = File.createTempFile(template, "");
				writer = new FileWriter(file);
			} catch (IOException e) {
				throw new TemplateProcessException(e.getMessage());
			} finally {
				if (file != null) {
					file.deleteOnExit();
				}
			}
		}
		render(obj, template);
		return file;
	}
	
	public File renderToFile(Object obj, String template, File file) {
		if (file == null) {
			throw new TemplateProcessException("没有该文件");
		}
		if (writer == null) {
			try {
				writer = new FileWriter(file);
			} catch (IOException e) {
				throw new TemplateProcessException(e.getMessage());
			}
		}
		render(obj, template);
		return file;
	}
	
	public File renderToFile(Object obj, String template, String path) {
		File temp = null;
		if (writer == null) {
			try {
				temp = new File(path);
				writer = new FileWriter(temp);
			} catch (IOException e) {
				throw new TemplateProcessException(e.getMessage());
			}
		}
		return renderToFile(obj, template, temp);
	}
}
