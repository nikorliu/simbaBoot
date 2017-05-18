package com.simba.buss.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simba.buss.model.Buss;
import com.simba.buss.service.BussService;
import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.constant.ConstantData;

/**
 * 调用业务系统Controller
 * 
 * @author caozj
 * 
 */
@Controller
@RequestMapping("/buss")
public class BussController {

	@Autowired
	private BussService bussService;

	@ResponseBody
	@RequestMapping("/execute")
	public JsonResult execute(HttpServletRequest request, String scriptName) {
		Map<String, String[]> params = request.getParameterMap();
		Object object = bussService.execute(params, scriptName);
		return new JsonResult(object != null ? object.toString() : StringUtils.EMPTY);
	}

	@RequestMapping("/list")
	public String list() {
		return "buss/list";
	}

	@RequestMapping("/getList")
	public String getList(Pager pager, ModelMap model) {
		List<Buss> list = bussService.page(pager);
		model.put("list", list);
		return "buss/table";
	}

	@ResponseBody
	@RequestMapping("/count")
	public JsonResult count() {
		int count = bussService.count();
		return new JsonResult(count, "", 200);
	}

	@RequestMapping("/toAdd")
	public String toAdd() {
		return "buss/add";
	}

	@RequestMapping("/add")
	public String add(Buss buss) {
		bussService.add(buss);
		return "redirect:/buss/list";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(String name, ModelMap model) throws UnsupportedEncodingException {
		name = URLDecoder.decode(name, ConstantData.DEFAULT_CHARSET);
		Buss buss = bussService.get(name);
		model.put("buss", buss);
		return "buss/update";
	}

	@RequestMapping("/update")
	public String update(Buss buss) {
		bussService.update(buss);
		return "redirect:/buss/list";
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(String name) {
		bussService.delete(name);
		return new JsonResult();
	}

	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(String[] names) {
		bussService.batchDelete(Arrays.asList(names));
		return new JsonResult();
	}

	@RequestMapping("/showScript")
	public String showScript(String name, ModelMap model) {
		Buss buss = bussService.get(name);
		model.put("buss", buss);
		return "buss/showScript";
	}

	@RequestMapping("/testScript")
	public String testScript(String name, ModelMap model) {
		Buss buss = bussService.get(name);
		model.put("buss", buss);
		return "buss/testScript";
	}

}
