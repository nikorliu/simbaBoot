package com.simba.permission.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simba.bootstrap.model.TreeViewData;
import com.simba.framework.util.freemarker.FreemarkerUtil;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.constant.ConstantData;
import com.simba.permission.controller.vo.OrgVo;
import com.simba.permission.dao.OrgExtDao;
import com.simba.permission.dao.UserExtDao;
import com.simba.permission.dao.UserRoleDao;
import com.simba.permission.model.Org;
import com.simba.permission.model.OrgExt;
import com.simba.permission.model.OrgExtDesc;
import com.simba.permission.model.OrgRole;
import com.simba.permission.model.Permission;
import com.simba.permission.model.Role;
import com.simba.permission.model.RolePermission;
import com.simba.permission.model.User;
import com.simba.permission.model.UserExt;
import com.simba.permission.model.UserExtDesc;
import com.simba.permission.model.UserOrg;
import com.simba.permission.model.UserRole;
import com.simba.permission.service.OrgRoleService;
import com.simba.permission.service.OrgService;
import com.simba.permission.service.PermissionService;
import com.simba.permission.service.RolePermissionService;
import com.simba.permission.service.RoleService;
import com.simba.permission.service.UserOrgService;
import com.simba.permission.service.UserService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Controller
@RequestMapping("/org")
public class OrgController {

	private static final Log logger = LogFactory.getLog(OrgController.class);

	@Autowired
	private OrgService orgService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserOrgService userOrgService;

	@Autowired
	private OrgExtDao orgExtDao;

	@Autowired
	private UserService userService;

	@Autowired
	private UserExtDao userExtDao;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private RolePermissionService rolePermissionService;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private OrgRoleService orgRoleService;

	/**
	 * 导出所有权限的sql脚本文件
	 * 
	 * @param response
	 * @throws TemplateException
	 * @throws IOException
	 * @throws ParseException
	 * @throws MalformedTemplateNameException
	 * @throws TemplateNotFoundException
	 */
	@RequestMapping("/exportAllPermission")
	public void exportAllPermission(HttpServletResponse response) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		List<Org> allOrgList = orgService.listAll();
		List<Role> allRoleList = roleService.listAll();
		List<UserOrg> userOrgList = userOrgService.listAll();
		List<OrgExt> orgExtList = orgExtDao.listAll();
		List<User> userList = userService.listAll();
		List<UserExt> userExtList = userExtDao.listAll();
		List<Permission> permissionList = permissionService.listAll();
		List<RolePermission> rolePermissionList = rolePermissionService.listAll();
		List<UserRole> userRoleList = userRoleDao.listAll();
		List<OrgRole> orgRoleList = orgRoleService.listAll();
		Map<String, Object> param = new HashMap<>();
		param.put("allOrgList", allOrgList);
		param.put("allRoleList", allRoleList);
		param.put("userOrgList", userOrgList);
		param.put("orgExtList", orgExtList);
		param.put("userList", userList);
		param.put("userExtList", userExtList);
		param.put("permissionList", permissionList);
		param.put("rolePermissionList", rolePermissionList);
		param.put("userRoleList", userRoleList);
		param.put("orgRoleList", orgRoleList);
		List<String> orgExtSQLs = buildOrgExtSqls(orgExtList);
		List<String> userExtSQLs = buildUserExtSqls(userExtList);
		param.put("orgExtSQLs", orgExtSQLs);
		param.put("userExtSQLs", userExtSQLs);
		String sql = FreemarkerUtil.parseFile("permission.ftl", param);
		OutputStream out = null;
		InputStream in = null;
		String fileName = "permission.sql";
		try {
			logger.info("下载文件:" + fileName);
			out = response.getOutputStream();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			in = IOUtils.toInputStream(sql);
			IOUtils.copy(in, out);
		} catch (Exception e) {
			logger.error("下载文件:[" + fileName + "]出现异常", e);
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(in);
		}
	}

	private List<String> buildUserExtSqls(List<UserExt> userExtList) {
		Map<String, String> userExtDesc = UserExtDesc.getAllDesc();
		String userExtBeforeSql = "insert into userExt(userAccount";
		for (String key : userExtDesc.keySet()) {
			userExtBeforeSql += "," + key;
		}
		userExtBeforeSql += ") ";
		List<String> userExtSQLs = new ArrayList<>();
		for (UserExt userExt : userExtList) {
			String sql = "values('" + userExt.getUserAccount() + "'";
			for (String key : userExtDesc.keySet()) {
				sql += ",'" + userExt.getExtMap().get(key) + "'";
			}
			sql = userExtBeforeSql + sql + ");";
			userExtSQLs.add(sql);
		}
		return userExtSQLs;
	}

	private List<String> buildOrgExtSqls(List<OrgExt> orgExtList) {
		Map<String, String> orgExtDesc = OrgExtDesc.getAllDesc();
		String orgExtBeforeSql = "insert into orgExt(id";
		for (String key : orgExtDesc.keySet()) {
			orgExtBeforeSql += "," + key;
		}
		orgExtBeforeSql += ") ";
		List<String> orgExtSQLs = new ArrayList<>();
		for (OrgExt orgExt : orgExtList) {
			String sql = "values(" + orgExt.getId();
			for (String key : orgExtDesc.keySet()) {
				sql += ",'" + orgExt.getExtMap().get(key) + "'";
			}
			sql = orgExtBeforeSql + sql + ");";
			orgExtSQLs.add(sql);
		}
		return orgExtSQLs;
	}

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
