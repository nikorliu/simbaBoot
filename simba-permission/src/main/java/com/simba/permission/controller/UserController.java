package com.simba.permission.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

import com.simba.framework.util.collection.ListUtil;
import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.constant.ConstantData;
import com.simba.permission.controller.vo.UserVo;
import com.simba.permission.model.Role;
import com.simba.permission.model.User;
import com.simba.permission.model.UserExt;
import com.simba.permission.model.UserExtDesc;
import com.simba.permission.model.UserOrg;
import com.simba.permission.service.OrgService;
import com.simba.permission.service.RoleService;
import com.simba.permission.service.UserOrgService;
import com.simba.permission.service.UserService;
import com.simba.util.SessionUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserOrgService userOrgService;

	@Autowired
	private OrgService orgService;

	@RequestMapping("/list")
	public String list(Integer orgID, ModelMap model) {
		String orgName = "机构树";
		if (orgID == null || orgID == ConstantData.TREE_ROOT_ID) {
			orgID = ConstantData.TREE_ROOT_ID;
		} else {
			orgName = orgService.get(orgID).getText();
		}
		Map<String, String> desc = UserExtDesc.getAllDesc();
		model.put("parentID", orgID);
		model.put("parentName", orgName);
		model.put("values", desc.values());
		return "permission/listUser";
	}

	@RequestMapping("/getUserList")
	public String getUserList(int orgID, Pager pager, ModelMap model) {
		List<UserOrg> userOrgList = userOrgService.pageBy("orgID", orgID, pager);
		List<User> userList = new ArrayList<User>(userOrgList.size());
		List<UserVo> voList = new ArrayList<>(userOrgList.size());
		for (UserOrg userOrg : userOrgList) {
			User user = userService.get(userOrg.getUserAccount());
			userList.add(user);
		}
		userList.forEach((user) -> {
			UserVo vo = new UserVo();
			vo.setUser(user);
			vo.setUserExt(userService.getUserExt(user.getAccount()));
			voList.add(vo);
		});
		Map<String, String> desc = UserExtDesc.getAllDesc();
		List<Map<String, Object>> mapList = new ArrayList<>(userOrgList.size());
		voList.forEach((vo) -> {
			Map<String, Object> map = new HashMap<>();
			map.put("account", vo.getUser().getAccount());
			map.put("name", vo.getUser().getName());
			List<String> exts = new ArrayList<>();
			desc.keySet().forEach((String key) -> {
				exts.add(vo.getUserExt().getExtMap().get(key));
			});
			map.put("exts", exts);
			mapList.add(map);
		});
		model.put("list", mapList);
		return "permission/userTable";
	}

	@ResponseBody
	@RequestMapping("/countUser")
	public JsonResult countUser(int orgID) {
		int count = userOrgService.countBy("orgID", orgID);
		return new JsonResult(count, "", 200);
	}

	@RequestMapping("/toAdd")
	public String toAdd(Integer orgID, ModelMap model) {
		Map<String, String> desc = UserExtDesc.getAllDesc();
		List<Map<String, Object>> descs = new ArrayList<>(desc.size());
		desc.forEach((key, value) -> {
			Map<String, Object> m = new HashMap<>(2);
			m.put("key", key);
			m.put("name", value);
			m.put("required", key.endsWith("_r"));
			descs.add(m);
		});
		String orgName = null;
		if (orgID == ConstantData.TREE_ROOT_ID) {
			orgName = "机构树";
		} else {
			orgName = orgService.get(orgID).getText();
		}
		model.put("orgID", orgID);
		model.put("orgName", orgName);
		model.put("descs", descs);
		return "permission/addUser";
	}

	@RequestMapping("/add")
	public String add(User user, Integer[] orgID, HttpServletRequest request, ModelMap model) {
		UserExt userExt = new UserExt();
		Map<String, String> extMap = new HashMap<>();
		userExt.setExtMap(extMap);
		userExt.setUserAccount(user.getAccount());
		Map<String, String> descMap = UserExtDesc.getAllDesc();
		descMap.keySet().forEach((key) -> {
			extMap.put(key, request.getParameter(key));
		});
		List<UserOrg> userOrgList = new ArrayList<UserOrg>();
		for (Integer org : orgID) {
			UserOrg userOrg = new UserOrg();
			userOrg.setOrgID(org);
			userOrg.setUserAccount(user.getAccount());
			userOrgList.add(userOrg);
		}
		userService.add(user, userExt, userOrgList);
		return "redirect:/user/list?orgID=" + orgID[0];
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(String account, HttpServletRequest request, ModelMap model)
			throws UnsupportedEncodingException {
		User loginUser = SessionUtil.getUser(request.getSession());
		User user = null;
		boolean self = true;
		if (StringUtils.isNotEmpty(account)) {
			account = URLDecoder.decode(account, ConstantData.DEFAULT_CHARSET);
			user = userService.get(account);
			if (!loginUser.getAccount().equals(account)) {
				self = false;
			}
		} else {
			user = loginUser;
		}
		UserExt userExt = userService.getUserExt(user.getAccount());
		Map<String, String> desc = UserExtDesc.getAllDesc();
		List<Map<String, Object>> descs = new ArrayList<>(desc.size());
		desc.forEach((key, value) -> {
			Map<String, Object> m = new HashMap<>(2);
			m.put("key", key);
			m.put("name", value);
			m.put("value", StringUtils.defaultString(userExt.getExtMap().get(key)));
			m.put("required", key.endsWith("_r"));
			descs.add(m);
		});
		List<UserOrg> userOrgList = userOrgService.listBy("userAccount", account);
		List<String> orgIDs = new ArrayList<>(userOrgList.size());
		List<String> orgNames = new ArrayList<>(userOrgList.size());
		userOrgList.forEach((userOrg) -> {
			orgIDs.add(userOrg.getOrgID() + "");
			String orgName = null;
			if (userOrg.getOrgID() == ConstantData.TREE_ROOT_ID) {
				orgName = "机构树";
			} else {
				orgName = orgService.get(userOrg.getOrgID()).getText();
			}
			orgNames.add(orgName);
		});
		model.put("descs", descs);
		model.put("self", self);
		model.put("user", user);
		model.put("orgID", ListUtil.join(orgIDs));
		model.put("orgName", ListUtil.join(orgNames));
		return "permission/updateUser";
	}

	@RequestMapping("/update")
	public String update(User user, Integer[] orgID, HttpServletRequest request, ModelMap model) {
		UserExt userExt = new UserExt();
		Map<String, String> extMap = new HashMap<>();
		userExt.setExtMap(extMap);
		userExt.setUserAccount(user.getAccount());
		Map<String, String> descMap = UserExtDesc.getAllDesc();
		descMap.keySet().forEach((key) -> {
			extMap.put(key, request.getParameter(key));
		});
		List<UserOrg> userOrgList = new ArrayList<UserOrg>();
		for (Integer org : orgID) {
			UserOrg userOrg = new UserOrg();
			userOrg.setOrgID(org);
			userOrg.setUserAccount(user.getAccount());
			userOrgList.add(userOrg);
		}
		userService.update(user, userExt, userOrgList);
		return "redirect:/user/list?orgID=" + orgID[0];
	}

	@ResponseBody
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(String[] accounts) {
		List<String> accountList = Arrays.asList(accounts);
		userService.batchDelete(accountList);
		return new JsonResult();
	}

	@ResponseBody
	@RequestMapping("/resetPwd")
	public JsonResult resetPwd(String account, ModelMap model) {
		userService.resetPwd(account);
		return new JsonResult();
	}

	@RequestMapping("/toModifyPwd")
	public String toModifyPwd() {
		return "user/modifyPwd";
	}

	@ResponseBody
	@RequestMapping("/modifyPwd")
	public JsonResult modifyPwd(HttpServletRequest request, String oldPwd, String newPwd, String confirmPwd,
			ModelMap model) {
		if (!confirmPwd.equals(newPwd)) {
			throw new RuntimeException("确认密码和新密码不一致");
		}
		if (oldPwd.equals(newPwd)) {
			throw new RuntimeException("旧密码和新密码不能一样");
		}
		User user = SessionUtil.getUser(request.getSession());
		userService.updatePwd(user.getAccount(), oldPwd, newPwd);
		return new JsonResult();
	}

	@RequestMapping("/toModifyInfo")
	public String toModifyInfo(HttpServletRequest request, ModelMap model) {
		User loginUser = SessionUtil.getUser(request.getSession());
		UserExt userExt = userService.getUserExt(loginUser.getAccount());
		Map<String, String> desc = UserExtDesc.getAllDesc();
		List<Map<String, Object>> descs = new ArrayList<>(desc.size());
		desc.forEach((key, value) -> {
			Map<String, Object> m = new HashMap<>(2);
			m.put("key", key);
			m.put("name", value);
			m.put("value", StringUtils.defaultString(userExt.getExtMap().get(key)));
			m.put("required", key.endsWith("_r"));
			descs.add(m);
		});
		model.put("descs", descs);
		model.put("user", loginUser);
		return "user/modifyInfo";
	}

	@ResponseBody
	@RequestMapping("/modifyInfo")
	public JsonResult modifyInfo(HttpServletRequest request, ModelMap model) {
		User loginUser = SessionUtil.getUser(request.getSession());
		UserExt userExt = new UserExt();
		Map<String, String> extMap = new HashMap<>();
		userExt.setExtMap(extMap);
		String name = request.getParameter("name");
		loginUser.setName(name);
		userExt.setUserAccount(loginUser.getAccount());
		Map<String, String> descMap = UserExtDesc.getAllDesc();
		descMap.keySet().forEach((key) -> {
			extMap.put(key, request.getParameter(key));
		});
		userService.update(loginUser, userExt);
		SessionUtil.setUser(request.getSession(), loginUser);
		return new JsonResult();
	}

	@RequestMapping("/toAssignRole")
	public String toAssignRole(String account, ModelMap model) {
		List<Role> allRoleList = roleService.listAll();
		List<Role> assignRoleList = userService.listRoleByAccount(account);
		model.put("account", account);
		model.put("allRoleList", allRoleList);
		model.put("assignRoleList", assignRoleList);
		return "permission/assignRole";
	}

	@ResponseBody
	@RequestMapping("/assignRole")
	public JsonResult assignRole(String[] roleName, String account, ModelMap model) {
		if (roleName == null || roleName.length == 0) {
			throw new RuntimeException("角色不能为空");
		}
		userService.assignRoles(account, Arrays.asList(roleName));
		return new JsonResult();
	}

	@ResponseBody
	@RequestMapping("/getCurrentUserInfo")
	public JsonResult getCurrentUserInfo(HttpSession session) {
		Map<String, Object> info = new HashMap<>();
		info.put("isAdmin", SessionUtil.isAdmin(session));
		info.put("userName", SessionUtil.getUser(session).getName());
		return new JsonResult(info);
	}

	/**
	 * 清空角色
	 * 
	 * @param account
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/clearRole")
	public JsonResult clearRole(String account) {
		userService.clearRole(account);
		return new JsonResult();
	}

}
