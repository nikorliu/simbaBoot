package com.simba.permission.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simba.framework.util.code.EncryptUtil;
import com.simba.framework.util.jdbc.Pager;
import com.simba.model.constant.ConstantData;
import com.simba.permission.dao.OrgDao;
import com.simba.permission.dao.OrgExtDao;
import com.simba.permission.dao.RoleDao;
import com.simba.permission.dao.UserDao;
import com.simba.permission.dao.UserExtDao;
import com.simba.permission.dao.UserOrgDao;
import com.simba.permission.dao.UserRoleDao;
import com.simba.permission.model.Org;
import com.simba.permission.model.OrgExt;
import com.simba.permission.model.Role;
import com.simba.permission.model.User;
import com.simba.permission.model.UserExt;
import com.simba.permission.model.UserExtDesc;
import com.simba.permission.model.UserOrg;
import com.simba.permission.model.UserRole;
import com.simba.permission.service.UserService;

/**
 * 用户service实现
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Value("${key}")
	private String key;

	@Value("${user.ext}")
	private String userExt;

	@Value("${default.pwd}")
	private String defaultPwd;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserExtDao userExtDao;

	@Autowired
	private OrgDao orgDao;

	@Autowired
	private OrgExtDao orgExtDao;

	@Autowired
	private UserOrgDao userOrgDao;

	@Override
	public void add(User user) {
		user.setPwd(defaultPwd);
		userDao.add(user);
	}

	@Override
	public void delete(String account) {
		userDao.delete(account);
		userExtDao.delete(account);
		userRoleDao.deleteByUserAccount(account);
		userOrgDao.deleteByUserAccount(account);
	}

	@Override
	public void updateName(String account, String name) {
		userDao.updateName(account, name);
	}

	@Override
	public void updatePwd(String account, String pwd) {
		pwd = EncryptUtil.md5(pwd + key);
		userDao.updatePwd(account, pwd);
	}

	@Override
	public User get(String account) {
		if (StringUtils.isEmpty(account)) {
			return null;
		}
		return userDao.get(account);
	}

	@Override
	public List<User> page(Pager page) {
		return userDao.page(page);
	}

	@Override
	public boolean checkUser(String account, String pwd) {
		pwd = EncryptUtil.md5(pwd + key);
		return userDao.checkUser(account, pwd);
	}

	@Override
	public List<User> listAll() {
		return userDao.listAll();
	}

	@Override
	public void assignRole(String userAccount, String roleName) {
		userRoleDao.deleteByUserAccount(userAccount);
		userRoleDao.add(new UserRole(userAccount, roleName));
	}

	@Override
	public void updatePwd(String account, String oldPwd, String newPwd) {
		if (!checkUser(account, oldPwd)) {
			throw new RuntimeException("旧密码错误");
		}
		updatePwd(account, newPwd);
	}

	@Override
	public void resetPwd(String account) {
		userDao.updatePwd(account, defaultPwd);
	}

	@Override
	public void batchDelete(List<String> accountList) {
		for (String account : accountList) {
			delete(account);
		}
	}

	@Override
	public List<Role> listRoleByAccount(String account) {
		List<UserRole> userRoleList = userRoleDao.listByUserAccount(account);
		List<Role> roleList = new ArrayList<Role>(userRoleList.size());
		for (UserRole userRole : userRoleList) {
			String roleName = userRole.getRoleName();
			Role role = roleDao.get(roleName);
			if (role != null) {
				roleList.add(role);
			}
		}
		return roleList;
	}

	@Override
	public void assignRoles(String userAccount, List<String> roleNames) {
		userRoleDao.deleteByUserAccount(userAccount);
		for (String roleName : roleNames) {
			if (StringUtils.isNotEmpty(roleName)) {
				userRoleDao.add(new UserRole(userAccount, roleName));
			}
		}
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	@PostConstruct
	public void checkAndCreateUserExt() {
		if (StringUtils.isBlank(userExt)) {
			return;
		}
		List<String> existColumns = userExtDao.showAllColumns();
		String[] ext = userExt.trim().split(",");
		for (String column : ext) {
			String[] kv = column.split(":");
			String key = kv[0];
			String value = kv[1];
			UserExtDesc.put(key, value);
			if (!existColumns.contains(key)) {
				userExtDao.addColumn(key);
			}
		}
	}

	@Override
	public UserExt getUserExt(String userAccount) {
		return userExtDao.get(userAccount);
	}

	@Override
	public List<Org> listOrgByUser(String userAccount) {
		List<UserOrg> userOrgList = userOrgDao.listBy("userAccount", userAccount);
		List<Org> orgList = new ArrayList<>(userOrgList.size());
		userOrgList.forEach((userOrg) -> {
			Org org = null;
			if (userOrg.getOrgID() != ConstantData.TREE_ROOT_ID) {
				org = orgDao.get(userOrg.getOrgID());
			} else {
				org = new Org();
				org.setId(ConstantData.TREE_ROOT_ID);
				org.setText("机构树");
			}
			orgList.add(org);
		});
		return orgList;
	}

	@Override
	public List<OrgExt> listOrgExtByUser(String userAccount) {
		List<UserOrg> userOrgList = userOrgDao.listBy("userAccount", userAccount);
		List<OrgExt> orgExtList = new ArrayList<>(userOrgList.size());
		userOrgList.forEach((userOrg) -> {
			OrgExt orgExt = null;
			if (userOrg.getOrgID() != ConstantData.TREE_ROOT_ID) {
				orgExt = orgExtDao.get(userOrg.getOrgID());
			} else {
				orgExt = new OrgExt();
				orgExt.setId(ConstantData.TREE_ROOT_ID);
			}
			orgExtList.add(orgExt);
		});
		return orgExtList;
	}

	@Override
	public void add(User user, UserExt userExt) {
		this.add(user);
		userExtDao.add(userExt);
	}

	@Override
	public void update(User user, UserExt userExt) {
		this.updateName(user.getAccount(), user.getName());
		userExtDao.update(userExt);
	}

	@Override
	public void update(User user, UserExt userExt, List<UserOrg> userOrgList) {
		this.update(user, userExt);
		userOrgDao.deleteByUserAccount(user.getAccount());
		userOrgList.forEach((userOrg) -> {
			userOrgDao.add(userOrg);
		});
	}

	@Override
	public void add(User user, UserExt userExt, List<UserOrg> userOrgList) {
		this.add(user, userExt);
		userOrgList.forEach((userOrg) -> {
			userOrgDao.add(userOrg);
		});
	}

	@Override
	public String getDesc(String account) {
		User user = this.get(account);
		String desc = account;
		if (user != null) {
			desc = user.getName() + "(" + account + ")";
		}
		return desc;
	}
}
