package com.simba.permission.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simba.bootstrap.model.TreeViewData;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.constant.ConstantData;
import com.simba.permission.model.Permission;
import com.simba.permission.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/list")
	public String list(Integer parentID, ModelMap model) {
		if (parentID == null) {
			parentID = ConstantData.TREE_ROOT_ID;
		}
		String parentName = "权限树";
		if (parentID != ConstantData.TREE_ROOT_ID) {
			parentName = permissionService.get(parentID).getText();
		}
		model.put("parentID", parentID);
		model.put("parentName", parentName);
		return "permission/listPermission";
	}

	@ResponseBody
	@RequestMapping("/getPermissionTree")
	public List<TreeViewData> getPermissionTree() {
		TreeViewData root = new TreeViewData();
		root.setId(ConstantData.TREE_ROOT_ID);
		root.setText("权限树");
		root.setTags(ConstantData.TREE_ROOT_ID + "");
		List<Permission> allPermissions = permissionService.listAll();
		Map<Integer, TreeViewData> nodeMap = new HashMap<>();
		allPermissions.forEach((Permission permission) -> {
			TreeViewData node = new TreeViewData();
			node.setId(permission.getId());
			node.setText(permission.getText());
			node.setTags(permission.getId() + "");
			node.setParentID(permission.getParentID());
			nodeMap.put(permission.getId(), node);
		});
		nodeMap.put(root.getId(), root);
		allPermissions.forEach((Permission permission) -> {
			TreeViewData node = nodeMap.get(permission.getId());
			TreeViewData parentNode = nodeMap.get(permission.getParentID());
			parentNode.addChildren(node);
		});
		List<TreeViewData> list = new ArrayList<>(1);
		list.add(root);
		return list;
	}

	@RequestMapping("/getPermissionList")
	public String getPermissionList(int parentID, ModelMap model) {
		List<Permission> list = permissionService.listChildren(parentID);
		model.put("list", list);
		return "permission/permissionTable";
	}

	@RequestMapping("/toAdd")
	public String toAdd(Integer parentID, ModelMap model) {
		String parentName = "权限树";
		if (parentID != ConstantData.TREE_ROOT_ID) {
			parentName = permissionService.get(parentID).getText();
		}
		model.put("parentID", parentID);
		model.put("parentName", parentName);
		return "permission/addPermission";
	}

	@RequestMapping("/add")
	public String add(Permission permission) {
		permissionService.add(permission);
		return "redirect:/permission/list?parentID=" + permission.getParentID();
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(int id, ModelMap model) {
		Permission permission = permissionService.get(id);
		String parentName = "权限树";
		int parentID = permission.getParentID();
		if (parentID != ConstantData.TREE_ROOT_ID) {
			parentName = permissionService.get(parentID).getText();
		}
		model.put("parentID", parentID);
		model.put("parentName", parentName);
		model.put("permission", permission);
		return "permission/updatePermission";
	}

	@RequestMapping("/update")
	public String update(Permission permission) {
		permissionService.update(permission);
		return "redirect:/permission/list?parentID=" + permission.getParentID();
	}

	@ResponseBody
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(Integer[] ids) {
		List<Integer> idList = Arrays.asList(ids);
		permissionService.batchDelete(idList);
		return new JsonResult();
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(int id) {
		permissionService.delete(id);
		return new JsonResult();
	}

}
