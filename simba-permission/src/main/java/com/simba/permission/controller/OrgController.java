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
import org.springframework.web.bind.annotation.ResponseBody;

import com.simba.bootstrap.model.TreeViewData;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.constant.ConstantData;
import com.simba.permission.controller.vo.OrgVo;
import com.simba.permission.model.Org;
import com.simba.permission.model.OrgExt;
import com.simba.permission.model.OrgExtDesc;
import com.simba.permission.model.Role;
import com.simba.permission.service.OrgService;
import com.simba.permission.service.RoleService;

@Controller
@RequestMapping("/org")
public class OrgController {

	@Autowired
	private OrgService orgService;

	@Autowired
	private RoleService roleService;

	@RequestMapping("/list")
	public String list(Integer parentID, ModelMap model) {
		String parentName = null;
		if (parentID == null || parentID == ConstantData.TREE_ROOT_ID) {
			parentID = ConstantData.TREE_ROOT_ID;
			parentName = "机构树";
		} else {
			parentName = orgService.get(parentID).getText();
		}
		Map<String, String> desc = OrgExtDesc.getAllDesc();
		model.put("values", desc.values());
		model.put("parentID", parentID);
		model.put("parentName", parentName);
		return "permission/listOrg";
	}

	@ResponseBody
	@RequestMapping("/getOrgTree")
	public List<TreeViewData> getOrgTree() {
		TreeViewData root = new TreeViewData();
		root.setId(ConstantData.TREE_ROOT_ID);
		root.setText("机构树");
		root.setTags(ConstantData.TREE_ROOT_ID + "");
		List<Org> allOrgs = orgService.listAll();
		Map<Integer, TreeViewData> nodeMap = new HashMap<>();
		allOrgs.forEach((Org org) -> {
			TreeViewData node = new TreeViewData();
			node.setId(org.getId());
			node.setText(org.getText());
			node.setTags(org.getId() + "");
			node.setParentID(org.getParentID());
			nodeMap.put(org.getId(), node);
		});
		nodeMap.put(root.getId(), root);
		allOrgs.forEach((Org org) -> {
			TreeViewData node = nodeMap.get(org.getId());
			TreeViewData parentNode = nodeMap.get(org.getParentID());
			parentNode.addChildren(node);
		});
		List<TreeViewData> list = new ArrayList<>(1);
		list.add(root);
		return list;
	}

	@RequestMapping("/getOrgList")
	public String getOrgList(int parentID, ModelMap model) {
		List<Org> orgList = orgService.listBy("parentID", parentID);
		List<OrgVo> voList = new ArrayList<>(orgList.size());
		orgList.forEach((org) -> {
			OrgVo vo = new OrgVo();
			vo.setOrg(org);
			vo.setOrgExt(orgService.getOrgExt(org.getId()));
			voList.add(vo);
		});
		Map<String, String> desc = OrgExtDesc.getAllDesc();
		List<Map<String, Object>> mapList = new ArrayList<>(orgList.size());
		voList.forEach((vo) -> {
			Map<String, Object> map = new HashMap<>();
			map.put("id", vo.getOrg().getId());
			map.put("orderNo", vo.getOrg().getOrderNo());
			map.put("parentID", vo.getOrg().getParentID());
			map.put("text", vo.getOrg().getText());
			List<String> exts = new ArrayList<>();
			desc.keySet().forEach((String key) -> {
				exts.add(vo.getOrgExt().getExtMap().get(key));
			});
			map.put("exts", exts);
			mapList.add(map);
		});
		model.put("list", mapList);
		return "permission/orgTable";
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
		String parentName = null;
		if (parentID == null || parentID == ConstantData.TREE_ROOT_ID) {
			parentID = ConstantData.TREE_ROOT_ID;
			parentName = "机构树";
		} else {
			parentName = orgService.get(parentID).getText();
		}
		model.put("parentID", parentID);
		model.put("parentName", parentName);
		model.put("descs", descs);
		return "permission/addOrg";
	}

	@RequestMapping("/add")
	public String add(Org org, HttpServletRequest request) {
		OrgExt orgExt = new OrgExt();
		Map<String, String> extMap = new HashMap<>();
		orgExt.setExtMap(extMap);
		Map<String, String> descMap = OrgExtDesc.getAllDesc();
		descMap.keySet().forEach((key) -> {
			extMap.put(key, request.getParameter(key));
		});
		orgService.add(org, orgExt);
		return "redirect:/org/list?parentID=" + org.getParentID();
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
		String parentName = null;
		if (org.getParentID() == ConstantData.TREE_ROOT_ID) {
			parentName = "机构树";
		} else {
			parentName = orgService.get(org.getParentID()).getText();
		}
		model.put("descs", descs);
		model.put("org", org);
		model.put("parentName", parentName);
		return "permission/updateOrg";
	}

	@RequestMapping("/update")
	public String update(Org org, HttpServletRequest request) {
		OrgExt orgExt = new OrgExt();
		Map<String, String> extMap = new HashMap<>();
		orgExt.setExtMap(extMap);
		orgExt.setId(org.getId());
		Map<String, String> descMap = OrgExtDesc.getAllDesc();
		descMap.keySet().forEach((key) -> {
			extMap.put(key, request.getParameter(key));
		});
		orgService.update(org, orgExt);
		return "redirect:/org/list?parentID=" + org.getParentID();
	}

	@ResponseBody
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(Integer[] ids) {
		List<Integer> idList = Arrays.asList(ids);
		orgService.batchDelete(idList);
		return new JsonResult();
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(int id) {
		orgService.delete(id);
		return new JsonResult();
	}

	@RequestMapping("/toAssignRole")
	public String toAssignRole(int id, ModelMap model) {
		List<Role> allRoleList = roleService.listAll();
		List<Role> assignRoleList = orgService.listRoleByOrgID(id);
		model.put("orgID", id);
		model.put("allRoleList", allRoleList);
		model.put("assignRoleList", assignRoleList);
		return "permission/assignOrgRole";
	}

	@ResponseBody
	@RequestMapping("/assignRole")
	public JsonResult assignRole(String[] roleName, int orgID, ModelMap model) {
		if (roleName == null || roleName.length == 0) {
			throw new RuntimeException("角色不能为空");
		}
		orgService.assignRoles(orgID, Arrays.asList(roleName));
		return new JsonResult();
	}

	/**
	 * 清空角色
	 * 
	 * @param id
	 *            机构id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/clearRole")
	public JsonResult clearRole(int id) {
		orgService.clearRole(id);
		return new JsonResult();
	}

}
