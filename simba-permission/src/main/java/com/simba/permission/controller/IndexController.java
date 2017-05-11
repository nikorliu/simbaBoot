package com.simba.permission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 入口Controller
 * 
 * @author caozj
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "redirect:/login/toLogin";
	}

}
