package com.simba.menu.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.framework.util.json.JsonResult;
import com.simba.menu.model.Menu;
import com.simba.menu.service.MenuService;
import com.simba.model.constant.ConstantData;
import com.simba.util.SessionUtil;

/**
 * 菜单控制器
 * 
 * @author caozj
 * 
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping("/list")
	public String list(Integer parentID, ModelMap model) {
		if (parentID == null) {
			parentID = ConstantData.TREE_ROOT_ID;
		}
		String parentName = "菜单树";
		if (parentID != ConstantData.TREE_ROOT_ID) {
			parentName = menuService.get(parentID).getText();
		}
		model.put("parentID", parentID);
		model.put("parentName", parentName);
		model.put("rootID", ConstantData.TREE_ROOT_ID);
		return "menu/list";
	}

	/**
	 * 获取子菜单数据
	 * 
	 * @param node
	 *            ext会使用这个参数来传递父节点id
	 * @param id
	 *            easyui会使用这个参数来传递父节点id
	 * @param dealMenu
	 *            是否对菜单数据进行处理(对url处理，对权限过滤)
	 * @param showRoot
	 *            是否显示根节点
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/listChildrenMenu")
	public String listChildrenMenu(Integer node, Integer id, Boolean dealMenu, Boolean showRoot, ModelMap model,
			HttpServletRequest request) {
		int parentID = ConstantData.TREE_ROOT_ID;
		if (node != null) {
			parentID = node;
		} else if (id != null) {
			parentID = id;
		} else if (showRoot != null && showRoot) {
			Menu root = buildRootMenu();
			model.put("message", FastJsonUtil.toJson(Arrays.asList(root)));
			return "message";
		}
		if (dealMenu == null) {
			dealMenu = true;
		}
		List<Menu> list = menuService.listChildren(parentID);
		if (dealMenu) {
			dealMenus(request, list);
		}
		model.put("message", FastJsonUtil.toJson(list));
		return "message";
	}

	private void dealMenus(HttpServletRequest request, List<Menu> list) {
		String contextPath = request.getContextPath();
		for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext();) {
			Menu menu = (Menu) iterator.next();
			boolean hasPermission = hasPermission(menu, request.getSession());
			if (hasPermission) {
				menu.setUrl(dealUrl(menu.getUrl(), contextPath));
			} else {
				iterator.remove();
			}
		}
	}

	private boolean hasPermission(Menu menu, HttpSession session) {
		boolean hasPermission = SessionUtil.hasPermission(session, menu.getUrl());
		if (hasPermission) {
			return true;
		}
		List<Menu> childrenMenus = menuService.listChildren(menu.getId());
		for (Menu childrenMenu : childrenMenus) {
			hasPermission = hasPermission(childrenMenu, session);
			if (hasPermission) {
				return true;
			}
		}
		return false;
	}

	private Menu buildRootMenu() {
		Menu menu = new Menu();
		menu.setId(ConstantData.TREE_ROOT_ID);
		menu.setText("菜单树");
		menu.setState("closed");
		return menu;
	}

	private String dealUrl(String url, String contextPath) {
		if (StringUtils.isBlank(url)) {
			return StringUtils.EMPTY;
		}
		if (url.startsWith("http:") || url.startsWith("https:")) {
			return url;
		}
		return contextPath + url;
	}

	/**
	 * 新增菜单
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Menu menu, ModelMap model) {
		menuService.add(menu);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toAdd")
	public String toAdd(Integer parentID, ModelMap model) {
		if (parentID == null) {
			parentID = ConstantData.TREE_ROOT_ID;
		}
		model.put("parentID", parentID);
		model.put("rootID", ConstantData.TREE_ROOT_ID);
		return "menu/add";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(int id, ModelMap model) {
		Menu menu = menuService.get(id);
		model.put("menu", menu);
		model.put("rootID", ConstantData.TREE_ROOT_ID);
		return "menu/update";
	}

	@RequestMapping("/update")
	public String update(Menu menu, ModelMap model) {
		menuService.update(menu);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/delete")
	public String delete(int id, ModelMap model) {
		menuService.delete(id);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/batchDelete")
	public String batchDelete(Integer[] id, ModelMap model) {
		menuService.batchDelete(Arrays.asList(id));
		model.put("message", new JsonResult().toJson());
		return "message";
	}
}
