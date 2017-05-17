package com.simba.permission.controller;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

	@Value("${pate.home}")
	private String homePage;

	@PostConstruct
	private void init() {
		homePage = StringUtils.defaultIfEmpty(homePage, "home");
	}

	@RequestMapping("/home")
	public String home() {
		return homePage;
	}
}
