package com.simba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.wx.menu.Menu;
import com.simba.model.wx.menu.MenuSearchResult;
import com.simba.util.send.MenuWxUtil;

/**
 * 用来提供给第三方平台的操作Controller
 * 
 * @author caozhejun
 *
 */
@Controller
@RequestMapping("/oper")
public class OperController {

	@Autowired
	private MenuWxUtil menuWxUtil;

	/**
	 * 查询菜单
	 * 
	 * @param model
	 */
	@RequestMapping("/search")
	public String search(ModelMap model) {
		MenuSearchResult result = menuWxUtil.search();
		model.put("message", FastJsonUtil.toJson(new JsonResult(result)));
		return "message";
	}

	/**
	 * 创建菜单
	 * 
	 * @param menuInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/create")
	public String create(String menuInfo, ModelMap model) {
		Menu menu = FastJsonUtil.toObject(menuInfo, Menu.class);
		menuWxUtil.create(menu);
		model.put("message", FastJsonUtil.toJson(new JsonResult()));
		return "message";
	}
}
