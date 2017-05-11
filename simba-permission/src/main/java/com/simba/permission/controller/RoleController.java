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

import com.simba.framework.util.json.FastJsonUtil;
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
		model.put("rootID", ConstantData.TREE_ROOT_ID);
		return "permission/listRole";
	}

	@RequestMapping
	public String listDataOfEasyUI(ModelMap model) {
		// Pager pager = new Pager((form.getPage() - 1) * form.getRows(),
		// form.getRows());
		// List<Role> list = roleService.page(pager);
		//// String message = FastJsonUtil.toJson(new
		// PageGrid(pager.getTotalCount(), list));
		// model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd")
	public String toAdd() {
		return "permission/addRole";
	}

	@RequestMapping("/add")
	public String add(Role role, ModelMap model) {
		roleService.add(role);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(String name, ModelMap model) throws UnsupportedEncodingException {
		name = URLDecoder.decode(name, ConstantData.DEFAULT_CHARSET);
		Role role = roleService.get(name);
		model.put("role", role);
		return "permission/updateRole";
	}

	@RequestMapping("/update")
	public String update(Role role, ModelMap model) {
		roleService.update(role);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/batchDelete")
	public String batchDelete(String[] roleNames, ModelMap model) {
		roleService.batchDelete(Arrays.asList(roleNames));
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/assignPermission")
	public String assignPermission(Integer[] permissionID, String roleName, ModelMap model) {
		if (permissionID.length == 0) {
			throw new RuntimeException("权限不能为空");
		}
		roleService.assignPermission(roleName, Arrays.asList(permissionID));
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/getPermissionByRoleName")
	public String getPermissionByRoleName(String roleName, ModelMap model) {
		List<Permission> list = roleService.listByRole(roleName);
		List<Integer> permissionIDList = new ArrayList<Integer>(list.size());
		list.forEach((p) -> {
			permissionIDList.add(p.getId());
		});
		model.put("message", FastJsonUtil.toJson(permissionIDList));
		return "message";
	}
}
