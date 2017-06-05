package com.simba.framework.util.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import com.simba.model.constant.ConstantData;

import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * freemarker工具类
 * 
 * @author caozj
 *
 */
public class FreemarkerUtil {

	/**
	 * 所有的freemarker模板文件存放目录
	 */
	private static final String freemarkerFolder = "ftl";

	/**
	 * 解析freemarker模板，返回解析后的字符串
	 * 
	 * @param fileName
	 *            文件名，在目录resources/ftl下
	 * @param param
	 *            参数对象
	 * @return
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String parseFile(String fileName, Map<String, Object> param) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Configuration cfg = buildConfiguration();
		Template temp = cfg.getTemplate(fileName);
		Writer out = new StringWriter();
		temp.process(param, out);
		return out.toString();
	}

	/**
	 * 构造配置对象
	 * 
	 * @return
	 * @throws IOException
	 */
	private static Configuration buildConfiguration() throws IOException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setNumberFormat("0.##");
		cfg.setLocale(Locale.CHINA);
		cfg.setDefaultEncoding(ConstantData.DEFAULT_CHARSET);
		cfg.setClassicCompatible(true);// 处理空值为空字符串
		cfg.setClassForTemplateLoading(FreemarkerUtil.class, "/" + freemarkerFolder);
		return cfg;
	}

	/**
	 * 解析freemarker模板，返回解析后的字符串
	 * 
	 * @param content
	 *            freemarker模板內容
	 * @param param
	 *            參數對象
	 * @return
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String parseContent(String content, Map<String, Object> param) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setNumberFormat("0.##");
		cfg.setLocale(Locale.CHINA);
		cfg.setDefaultEncoding(ConstantData.DEFAULT_CHARSET);
		cfg.setClassicCompatible(true);// 处理空值为空字符串
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate(freemarkerFolder, content);
		cfg.setTemplateLoader(stringLoader);
		Template template = cfg.getTemplate(freemarkerFolder);
		Writer out = new StringWriter();
		template.process(param, out);
		return out.toString();
	}

}
