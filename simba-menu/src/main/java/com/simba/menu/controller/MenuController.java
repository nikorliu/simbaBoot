package com.simba.menu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simba.bootstrap.model.TreeViewData;
import com.simba.framework.util.freemarker.FreemarkerUtil;
import com.simba.framework.util.json.JsonResult;
import com.simba.menu.model.Menu;
import com.simba.menu.service.MenuService;
import com.simba.model.constant.ConstantData;
import com.simba.util.SessionUtil;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

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

	private String[] icons = new String[] { "fa-dashboard", "fa-files-o", "fa-th", "fa-pie-chart", "fa-laptop",
			"fa-edit", "fa-table", "fa-calendar", "fa-envelope", "fa-folder", "fa-share" };

	@ResponseBody
	@RequestMapping("/showAllMenus")
	public String showAllMenus(HttpServletRequest request, ModelMap model) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		int iconSize = icons.length;
		int index = 0;
		List<Menu> list = menuService.listChildren(ConstantData.TREE_ROOT_ID);
		dealMenus(request, list);
		String html = "";
		for (Menu menu : list) {
			menu.setIcon(icons[index]);
			index = (index + 1) % iconSize;
			if (menu.getState().equals("open")) {
				Map<String, Object> param = new HashMap<>();
				param.put("menu", menu);
				html += FreemarkerUtil.parseFile("onelevelmenu.ftl", param);
			} else {
				Map<String, Object> param = new HashMap<>();
				param.put("menu", menu);
				param.put("subMenus", dealSubMenuHtml(request, menu));
				html += FreemarkerUtil.parseFile("multilevelmenu.ftl", param);
			}
		}
		return html;
	}

	private String dealSubMenuHtml(HttpServletRequest request, Menu menu) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String html = "";
		List<Menu> list = menuService.listChildren(menu.getId());
		dealMenus(request, list);
		for (Menu childMenu : list) {
			if (childMenu.getState().equals("open")) {
				Map<String, Object> param = new HashMap<>();
				param.put("menu", childMenu);
				html += FreemarkerUtil.parseFile("leafmenu.ftl", param);
			} else {
				Map<String, Object> param = new HashMap<>();
				param.put("menu", childMenu);
				param.put("subMenus", dealSubMenuHtml(request, childMenu));
				html += FreemarkerUtil.parseFile("nonleafmenu.ftl", param);
			}
		}
		return html;
	}

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
		return "menu/list";
	}

	@ResponseBody
	@RequestMapping("/getMenuTree")
	public List<TreeViewData> getMenuTree() {
		TreeViewData root = new TreeViewData();
		root.setId(ConstantData.TREE_ROOT_ID);
		root.setText("菜单树");
		root.setTags(ConstantData.TREE_ROOT_ID + "");
		List<Menu> allMenus = menuService.listAll();
		Map<Integer, TreeViewData> nodeMap = new HashMap<>();
		allMenus.forEach((Menu menu) -> {
			TreeViewData node = new TreeViewData();
			node.setId(menu.getId());
			node.setText(menu.getText());
			node.setTags(menu.getId() + "");
			node.setParentID(menu.getParentID());
			nodeMap.put(menu.getId(), node);
		});
		nodeMap.put(root.getId(), root);
		allMenus.forEach((Menu menu) -> {
			TreeViewData node = nodeMap.get(menu.getId());
			TreeViewData parentNode = nodeMap.get(menu.getParentID());
			parentNode.addChildren(node);
		});
		List<TreeViewData> list = new ArrayList<>(1);
		list.add(root);
		return list;
	}

	@RequestMapping("/getMenuList")
	public String getMenuList(int parentID, ModelMap model) {
		List<Menu> list = menuService.listChildren(parentID);
		model.put("list", list);
		return "menu/menuTable";
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
	public String add(Menu menu) {
		menuService.add(menu);
		return "redirect:/menu/list?parentID=" + menu.getParentID();
	}

	@RequestMapping("/toAdd")
	public String toAdd(int parentID, ModelMap model) {
		String parentName = "菜单树";
		if (parentID != ConstantData.TREE_ROOT_ID) {
			parentName = menuService.get(parentID).getText();
		}
		model.put("parentID", parentID);
		model.put("parentName", parentName);
		return "menu/add";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(int id, ModelMap model) {
		Menu menu = menuService.get(id);
		String parentName = "菜单树";
		int parentID = menu.getParentID();
		if (parentID != ConstantData.TREE_ROOT_ID) {
			parentName = menuService.get(parentID).getText();
		}
		model.put("menu", menu);
		model.put("parentName", parentName);
		return "menu/update";
	}

	@RequestMapping("/update")
	public String update(Menu menu) {
		menuService.update(menu);
		return "redirect:/menu/list?parentID=" + menu.getParentID();
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(int id, ModelMap model) {
		menuService.delete(id);
		return new JsonResult();
	}

	@ResponseBody
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(Integer[] id, ModelMap model) {
		menuService.batchDelete(Arrays.asList(id));
		return new JsonResult();
	}
}
