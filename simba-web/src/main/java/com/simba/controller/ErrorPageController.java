package com.simba.controller;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误处理Controller
 * 
 * @author caozhejun
 *
 */
@Controller
public class ErrorPageController {

	@Value("${page.error}")
	private String errorPage;

	@Value("${page.forbid}")
	private String forbidPage;

	@Value("${page.no}")
	private String noPage;

	@PostConstruct
	private void init() {
		noPage = StringUtils.defaultIfEmpty(noPage, "error/nopage");
		errorPage = StringUtils.defaultIfEmpty(errorPage, "error/error");
		forbidPage = StringUtils.defaultIfEmpty(forbidPage, "error/forbid");
	}

	@RequestMapping("/myerror")
	public String error() {
		return errorPage;
	}

	@RequestMapping("/myforbid")
	public String forbid() {
		return forbidPage;
	}

	@RequestMapping("/mynopage")
	public String nopage() {
		return noPage;
	}

}
