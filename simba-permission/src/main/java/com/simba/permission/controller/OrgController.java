package com.simba.permission.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.json.FastJsonUtil;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.constant.ConstantData;
import com.simba.permission.controller.vo.OrgVo;
import com.simba.permission.model.Org;
import com.simba.permission.model.OrgExt;
import com.simba.permission.model.OrgExtDesc;
import com.simba.permission.service.OrgService;

@Controller
@RequestMapping("/org")
public class OrgController {

	@Autowired
	private OrgService orgService;

	@RequestMapping("/list")
	public String list(Integer parentID, ModelMap model) {
		Map<String, String> desc = OrgExtDesc.getAllDesc();
		List<String> keys = new ArrayList<>(desc.size());
		List<Map<String, String>> descs = new ArrayList<>(desc.size());
		desc.forEach((key, value) -> {
			keys.add(key);
			Map<String, String> m = new HashMap<>(2);
			m.put("key", key);
			m.put("value", value);
			descs.add(m);
		});
		if (parentID == null) {
			parentID = ConstantData.TREE_ROOT_ID;
		}
		String parentName = "机构树";
		if (parentID != ConstantData.TREE_ROOT_ID) {
			parentName = orgService.get(parentID).getText();
		}
		model.put("keys", keys);
		model.put("descs", descs);
		model.put("rootID", ConstantData.TREE_ROOT_ID);
		model.put("parentID", parentID);
		model.put("parentName", parentName);
		return "permission/listOrg";
	}

	/**
	 * 获取子机构数据
	 * 
	 * @param node
	 *            ext会使用这个参数来传递父节点id
	 * @param id
	 *            easyui会使用这个参数来传递父节点id
	 * @param showRoot
	 *            是否显示根节点
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/listChildrenOrg")
	public String listChildrenOrg(Integer node, Integer id, Boolean showRoot, ModelMap model,
			HttpServletRequest request) {
		int parentID = ConstantData.TREE_ROOT_ID;
		if (node != null) {
			parentID = node;
		} else if (id != null) {
			parentID = id;
		} else if (showRoot != null && showRoot) {
			Org root = buildRootOrg();
			// model.put("message", JsonUtil.toJson(Arrays.asList(root)));
			return "message";
		}
		List<Org> list = orgService.listChildren(parentID);
		model.put("message", FastJsonUtil.toJson(list));
		return "message";
	}

	@RequestMapping
	public String listChildrenFullOrg(Integer id, String forSimple, ModelMap model) {
		int parentID = ConstantData.TREE_ROOT_ID;
		if (id != null) {
			parentID = id;
		}
		List<Org> orgList = orgService.listBy("parentID", parentID);
		List<OrgVo> voList = new ArrayList<>(orgList.size());
		orgList.forEach((org) -> {
			OrgVo vo = new OrgVo();
			vo.setOrg(org);
			vo.setOrgExt(orgService.getOrgExt(org.getId()));
			voList.add(vo);
		});
		if ("true".equals(forSimple)) {
			List<Map<String, Object>> mapList = new ArrayList<>(orgList.size());
			voList.forEach((vo) -> {
				Map<String, Object> map = new HashMap<>();
				map.put("id", vo.getOrg().getId());
				map.put("orderNo", vo.getOrg().getOrderNo());
				map.put("parentID", vo.getOrg().getParentID());
				map.put("text", vo.getOrg().getText());
				map.putAll(vo.getOrgExt().getExtMap());
				mapList.add(map);
			});
			model.put("message", FastJsonUtil.toJson(mapList));
		} else {
			model.put("message", FastJsonUtil.toJson(voList));
		}
		return "message";
	}

	private Org buildRootOrg() {
		Org root = new Org();
		root.setId(ConstantData.TREE_ROOT_ID);
		root.setText("机构树");
		root.setState("closed");
		return root;
	}

	@RequestMapping("/listDataOfExt")
	public String listDataOfExt(ModelMap model, int start, int limit) {
		Pager page = new Pager(start, limit);
		List<Org> list = orgService.page(page);
		// String message = new JsonResult(new ExtPageGrid(list,
		// page.getTotalCount())).toJson();
		// model.put("message", message);
		return "message";
	}

	@RequestMapping("/listDataOfEasyUI")
	public String listDataOfEasyUI(ModelMap model) {
		// Pager page = new Pager((form.getPage() - 1) * form.getRows(),
		// form.getRows());
		// List<Org> list = orgService.page(page);
		// String message = JsonUtil.toJson(new PageGrid(page.getTotalCount(),
		// list));
		// model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd")
	public String toAdd(Integer parentID, ModelMap model) {
		Map<String, String> desc = OrgExtDesc.getAllDesc();
		List<Map<String, Object>> descs = new ArrayList<>(desc.size());
		desc.forEach((key, value) -> {
			Map<String, Object> m = new HashMap<>(2);
			m.put("key", key);
			m.put("name", value);
			m.put("required", key.endsWith("_r"));
			descs.add(m);
		});
		if (parentID == null) {
			parentID = ConstantData.TREE_ROOT_ID;
		}
		model.put("parentID", parentID);
		model.put("rootID", ConstantData.TREE_ROOT_ID);
		model.put("descs", descs);
		return "permission/addOrg";
	}

	@RequestMapping("/add")
	public String add(Org org, HttpServletRequest request, ModelMap model) {
		OrgExt orgExt = new OrgExt();
		Map<String, String> extMap = new HashMap<>();
		orgExt.setExtMap(extMap);
		Map<String, String> descMap = OrgExtDesc.getAllDesc();
		descMap.keySet().forEach((key) -> {
			extMap.put(key, request.getParameter(key));
		});
		orgService.add(org, orgExt);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(int id, ModelMap model) {
		Org org = orgService.get(id);
		OrgExt orgExt = orgService.getOrgExt(id);
		Map<String, String> desc = OrgExtDesc.getAllDesc();
		List<Map<String, Object>> descs = new ArrayList<>(desc.size());
		desc.forEach((key, value) -> {
			Map<String, Object> m = new HashMap<>(2);
			m.put("key", key);
			m.put("name", value);
			m.put("value", StringUtils.defaultString(orgExt.getExtMap().get(key)));
			m.put("required", key.endsWith("_r"));
			descs.add(m);
		});
		model.put("descs", descs);
		model.put("org", org);
		model.put("rootID", ConstantData.TREE_ROOT_ID);
		return "permission/updateOrg";
	}

	@RequestMapping("/update")
	public String update(Org org, HttpServletRequest request, ModelMap model) {
		OrgExt orgExt = new OrgExt();
		Map<String, String> extMap = new HashMap<>();
		orgExt.setExtMap(extMap);
		orgExt.setId(org.getId());
		Map<String, String> descMap = OrgExtDesc.getAllDesc();
		descMap.keySet().forEach((key) -> {
			extMap.put(key, request.getParameter(key));
		});
		orgService.update(org, orgExt);
		// model.put("message", FastJsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete")
	public String batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		orgService.batchDelete(idList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/delete")
	public String delete(int id, ModelMap model) {
		orgService.delete(id);
		// model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/show")
	public String show(int id, ModelMap model) {
		Org org = orgService.get(id);
		model.put("org", org);
		return "permission/showOrg";
	}

	@RequestMapping("/get")
	public String get(int id, ModelMap model) {
		Org org = orgService.get(id);
		model.put("message", new JsonResult(org).toJson());
		return "message";
	}

}
