package com.simba.registry.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.constant.ConstantData;
import com.simba.registry.model.RegistryTable;
import com.simba.registry.service.RegistryTableService;
import com.simba.registry.service.RegistryTypeService;

/**
 * 注册表 Controller
 * 
 * @author caozj
 * 
 */
@Controller
@RequestMapping("/registryTable")
public class RegistryTableController {

	@Autowired
	private RegistryTableService registryTableService;

	@Autowired
	private RegistryTypeService registryTypeService;

	@RequestMapping("/list")
	public String list(Integer typeID, ModelMap model) {
		String typeName = "注册类型树";
		if (typeID == null) {
			typeID = ConstantData.TREE_ROOT_ID;
		}
		if (typeID != ConstantData.TREE_ROOT_ID) {
			typeName = registryTypeService.get(typeID).getText();
		}
		model.put("typeID", typeID);
		model.put("typeName", typeName);
		return "registryTable/list";
	}

	@RequestMapping("/getList")
	public String getList(int typeID, Pager pager, ModelMap model) {
		model.put("list", registryTableService.pageBy("typeID", typeID, pager));
		return "registryTable/table";
	}

	@ResponseBody
	@RequestMapping("/count")
	public JsonResult count(int typeID) {
		int count = registryTableService.countBy("typeID", typeID);
		return new JsonResult(count, "", 200);
	}

	@RequestMapping("/toAdd")
	public String toAdd(int typeID, ModelMap model) {
		String typeName = "注册类型树";
		if (typeID != ConstantData.TREE_ROOT_ID) {
			typeName = registryTypeService.get(typeID).getText();
		}
		model.put("typeID", typeID);
		model.put("typeName", typeName);
		return "registryTable/add";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(int id, ModelMap model) {
		RegistryTable registryTable = registryTableService.get(id);
		String typeName = "注册类型树";
		if (registryTable.getTypeID() != ConstantData.TREE_ROOT_ID) {
			typeName = registryTypeService.get(registryTable.getTypeID()).getText();
		}
		model.put("registryTable", registryTable);
		model.put("typeName", typeName);
		return "registryTable/update";
	}

	@RequestMapping("/add")
	public String add(RegistryTable registryTable, ModelMap model) {
		registryTableService.add(registryTable);
		return "redirect:/registryTable/list?typeID=" + registryTable.getTypeID();
	}

	@RequestMapping("/update")
	public String update(RegistryTable registryTable, ModelMap model) {
		registryTableService.update(registryTable);
		return "redirect:/registryTable/list?typeID=" + registryTable.getTypeID();
	}

	@ResponseBody
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		registryTableService.batchDelete(idList);
		return new JsonResult();
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(int id, ModelMap model) {
		registryTableService.delete(id);
		return new JsonResult();
	}

}
