package com.simba.permission.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.framework.util.json.JsonResult;
import com.simba.model.constant.ConstantData;
import com.simba.permission.controller.form.UserSearchForm;
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
		Map<String, String> desc = UserExtDesc.getAllDesc();
		List<String> keys = new ArrayList<>(desc.size());
		List<Map<String, String>> descs = new ArrayList<>(desc.size());
		desc.forEach((key, value) -> {
			keys.add(key);
			Map<String, String> m = new HashMap<>(2);
			m.put("key", key);
			m.put("value", value);
			descs.add(m);
		});
		if (orgID == null) {
			orgID = ConstantData.TREE_ROOT_ID;
		}
		String orgName = "机构树";
		if (orgID != ConstantData.TREE_ROOT_ID) {
			orgName = orgService.get(orgID).getText();
		}
		model.put("keys", keys);
		model.put("descs", descs);
		model.put("orgID", orgID);
		model.put("orgName", orgName);
		model.put("rootID", ConstantData.TREE_ROOT_ID);
		return "permission/listUser";
	}

	@RequestMapping
	public String listFull(UserSearchForm form, String forSimple, ModelMap model) {
		List<UserVo> voList = new ArrayList<>();
		int total = 0;
		if (StringUtils.isNotEmpty(form.getAccount())) {
			User user = userService.get(form.getAccount());
			if (user != null) {
				total = 1;
				UserVo vo = new UserVo();
				vo.setUser(user);
				vo.setUserExt(userService.getUserExt(user.getAccount()));
				voList.add(vo);
			}
		} else if (form.getOrgID() != null) {
//			Pager pager = new Pager((page.getPage() - 1) * page.getRows(), page.getRows());
//			List<UserOrg> userOrgList = userOrgService.pageBy("orgID", form.getOrgID(), pager);
//			total = pager.getTotalCount();
//			List<User> userList = new ArrayList<User>(userOrgList.size());
//			for (UserOrg userOrg : userOrgList) {
//				User user = userService.get(userOrg.getUserAccount());
//				userList.add(user);
//			}
//			userList.forEach((user) -> {
//				UserVo vo = new UserVo();
//				vo.setUser(user);
//				vo.setUserExt(userService.getUserExt(user.getAccount()));
//				voList.add(vo);
//			});
		}
		if ("true".equals(forSimple)) {
			List<Map<String, Object>> mapList = new ArrayList<>(voList.size());
			voList.forEach((vo) -> {
				Map<String, Object> map = new HashMap<>();
				map.put("account", vo.getUser().getAccount());
				map.put("name", vo.getUser().getName());
				map.putAll(vo.getUserExt().getExtMap());
				mapList.add(map);
			});
//			model.put("message", FastJsonUtil.toJson(new PageGrid(total, mapList)));
		} else {
//			model.put("message", FastJsonUtil.toJson(new PageGrid(total, voList)));
		}
		return "message";
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
		model.put("descs", descs);
		model.put("orgID", orgID);
		model.put("rootID", ConstantData.TREE_ROOT_ID);
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
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(String account, HttpServletRequest request, ModelMap model) throws UnsupportedEncodingException {
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
		List<Integer> orgIDs = new ArrayList<Integer>(userOrgList.size());
		userOrgList.forEach((userOrg) -> {
			orgIDs.add(userOrg.getOrgID());
		});
		model.put("descs", descs);
		model.put("self", self);
		model.put("user", user);
		model.put("rootID", ConstantData.TREE_ROOT_ID);
		model.put("orgIDs", FastJsonUtil.toJson(orgIDs));
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
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/batchDelete")
	public String batchDelete(String[] accounts, ModelMap model) {
		List<String> accountList = Arrays.asList(accounts);
		userService.batchDelete(accountList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/resetPwd")
	public String resetPwd(String account, ModelMap model) {
		userService.resetPwd(account);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toModifyPwd")
	public String toModifyPwd() {
		return "user/modifyPwd";
	}

	@RequestMapping("/modifyPwd")
	public String modifyPwd(HttpServletRequest request, String oldPwd, String newPwd, String confirmPwd, ModelMap model) {
		if (!confirmPwd.equals(newPwd)) {
			throw new RuntimeException("确认密码和新密码不一致");
		}
		if (oldPwd.equals(newPwd)) {
			throw new RuntimeException("旧密码和新密码不能一样");
		}
		User user = SessionUtil.getUser(request.getSession());
		userService.updatePwd(user.getAccount(), oldPwd, newPwd);
		model.put("message", new JsonResult().toJson());
		return "message";
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

	@RequestMapping("/modifyInfo")
	public String modifyInfo(HttpServletRequest request, ModelMap model) {
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
		model.put("message", new JsonResult().toJson());
		return "message";
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

	@RequestMapping("/assignRole")
	public String assignRole(String[] roleName, String account, ModelMap model) {
		if (roleName == null || roleName.length == 0) {
			throw new RuntimeException("角色不能为空");
		}
		userService.assignRoles(account, Arrays.asList(roleName));
		model.put("message", new JsonResult().toJson());
		return "message";
	}

}
