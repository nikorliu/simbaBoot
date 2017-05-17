package com.simba.registry.controller;

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
import com.simba.registry.model.RegistryType;
import com.simba.registry.service.RegistryTypeService;

/**
 * 注册类型控制器
 * 
 * @author caozj
 * 
 */
@Controller
@RequestMapping("/registryType")
public class RegistryTypeController {

	@Autowired
	private RegistryTypeService registryTypeService;

	@RequestMapping("/list")
	public String list(Integer parentID, ModelMap model) {
		if (parentID == null) {
			parentID = ConstantData.TREE_ROOT_ID;
		}
		String parentName = "注册类型树";
		if (parentID != ConstantData.TREE_ROOT_ID) {
			parentName = registryTypeService.get(parentID).getText();
		}
		model.put("parentID", parentID);
		model.put("parentName", parentName);
		return "registryType/list";
	}

	@ResponseBody
	@RequestMapping("/getRegistryTypeTree")
	public List<TreeViewData> getRegistryTypeTree() {
		TreeViewData root = new TreeViewData();
		root.setId(ConstantData.TREE_ROOT_ID);
		root.setText("注册类型树");
		root.setTags(ConstantData.TREE_ROOT_ID + "");
		List<RegistryType> allRegistryTypes = registryTypeService.listAll();
		Map<Integer, TreeViewData> nodeMap = new HashMap<>();
		allRegistryTypes.forEach((RegistryType registryType) -> {
			TreeViewData node = new TreeViewData();
			node.setId(registryType.getId());
			node.setText(registryType.getText());
			node.setTags(registryType.getId() + "");
			node.setParentID(registryType.getParentID());
			nodeMap.put(registryType.getId(), node);
		});
		nodeMap.put(root.getId(), root);
		allRegistryTypes.forEach((RegistryType registryType) -> {
			TreeViewData node = nodeMap.get(registryType.getId());
			TreeViewData parentNode = nodeMap.get(registryType.getParentID());
			parentNode.addChildren(node);
		});
		List<TreeViewData> list = new ArrayList<>(1);
		list.add(root);
		return list;
	}

	@RequestMapping("/getRegistryTypeList")
	public String getRegistryTypeList(int parentID, ModelMap model) {
		List<RegistryType> list = registryTypeService.listBy("parentID", parentID);
		model.put("list", list);
		return "registryType/table";
	}

	/**
	 * 新增注册类型
	 * 
	 * @param registryType
	 * @return
	 */
	@RequestMapping("/add")
	public String add(RegistryType registryType) {
		registryTypeService.add(registryType);
		return "redirect:/registryType/list?parentID=" + registryType.getParentID();
	}

	@RequestMapping("/toAdd")
	public String toAdd(int parentID, ModelMap model) {
		String parentName = "注册类型树";
		if (parentID != ConstantData.TREE_ROOT_ID) {
			parentName = registryTypeService.get(parentID).getText();
		}
		model.put("parentID", parentID);
		model.put("parentName", parentName);
		return "registryType/add";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(int id, ModelMap model) {
		RegistryType registryType = registryTypeService.get(id);
		String parentName = "注册类型树";
		int parentID = registryType.getParentID();
		if (parentID != ConstantData.TREE_ROOT_ID) {
			parentName = registryTypeService.get(parentID).getText();
		}
		model.put("registryType", registryType);
		model.put("parentName", parentName);
		return "registryType/update";
	}

	@RequestMapping("/update")
	public String update(RegistryType registryType) {
		registryTypeService.update(registryType);
		return "redirect:/registryType/list?parentID=" + registryType.getParentID();
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(int id, ModelMap model) {
		registryTypeService.delete(id);
		return new JsonResult();
	}

	@ResponseBody
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(Integer[] id, ModelMap model) {
		registryTypeService.batchDelete(Arrays.asList(id));
		return new JsonResult();
	}
}
