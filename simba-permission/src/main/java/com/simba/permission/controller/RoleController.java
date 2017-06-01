package com.simba.permission.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simba.framework.util.collection.ListUtil;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.constant.ConstantData;
import com.simba.permission.model.Permission;
import com.simba.permission.model.Role;
import com.simba.permission.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/list")
	public String list(ModelMap model) {
		List<Role> list = roleService.listAll();
		model.put("list", list);
		return "permission/listRole";
	}

	@RequestMapping("/toAdd")
	public String toAdd() {
		return "permission/addRole";
	}

	@RequestMapping("/add")
	public String add(Role role) {
		roleService.add(role);
		return "redirect:/role/list";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(String name, ModelMap model) throws UnsupportedEncodingException {
		name = URLDecoder.decode(name, ConstantData.DEFAULT_CHARSET);
		Role role = roleService.get(name);
		model.put("role", role);
		return "permission/updateRole";
	}

	@RequestMapping("/update")
	public String update(Role role) {
		roleService.update(role);
		return "redirect:/role/list";
	}

	@ResponseBody
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(String[] roleNames) {
		roleService.batchDelete(Arrays.asList(roleNames));
		return new JsonResult();
	}

	@RequestMapping("/toAssignPermission")
	public String toAssignPermission(String name, ModelMap model) {
		List<Permission> list = roleService.listByRole(name);
		List<String> ids = new ArrayList<>(list.size());
		List<String> texts = new ArrayList<>(list.size());
		list.forEach((Permission permission) -> {
			ids.add(permission.getId() + "");
			texts.add(permission.getText());
		});
		model.put("ids", ListUtil.join(ids));
		model.put("texts", ListUtil.join(texts));
		model.put("roleName", name);
		return "permission/assignPermission";
	}

	@ResponseBody
	@RequestMapping("/assignPermission")
	public JsonResult assignPermission(Integer[] permissionID, String roleName) {
		if (permissionID.length == 0) {
			throw new RuntimeException("权限不能为空");
		}
		roleService.assignPermission(roleName, Arrays.asList(permissionID));
		return new JsonResult();
	}

	/**
	 * 清空权限
	 * 
	 * @param roleName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/clearPermission")
	public JsonResult clearPermission(String roleName) {
		roleService.clearPermission(roleName);
		return new JsonResult();
	}

}
