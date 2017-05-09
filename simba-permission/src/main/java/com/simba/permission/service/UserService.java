package com.simba.permission.service;

import java.util.List;

import com.simba.framework.util.jdbc.Pager;
import com.simba.permission.model.Org;
import com.simba.permission.model.OrgExt;
import com.simba.permission.model.Role;
import com.simba.permission.model.User;
import com.simba.permission.model.UserExt;
import com.simba.permission.model.UserOrg;

/**
 * 用户service(传进方法里的密码全部为明文密码)
 * 
 * @author caozj
 * 
 */
public interface UserService {

	void add(User user);

	void add(User user, UserExt userExt);

	void delete(String account);

	void update(User user);

	void update(User user, UserExt userExt);

	void updateName(String account, String name);

	/**
	 * 修改密码
	 * 
	 * @param account
	 *            账号
	 * @param pwd
	 *            密码(明文)
	 */
	void updatePwd(String account, String pwd);

	/**
	 * 修改密码
	 * 
	 * @param account
	 *            账号
	 * @param oldPwd
	 *            旧密码(明文)
	 * @param newPwd
	 *            新密码(明文)
	 */
	void updatePwd(String account, String oldPwd, String newPwd);

	User get(String account);
	
	/**
	 * 根据账号返回用户在页面的显示描述
	 * @param account
	 * @return name(account)
	 */
	String getDesc(String account);

	List<User> page(Pager page);

	/**
	 * 检查用户是否账号密码正确
	 * 
	 * @param account
	 *            账号
	 * @param pwd
	 *            明文密码
	 * @return
	 */
	boolean checkUser(String account, String pwd);

	List<User> listAll();

	void assignRole(String userAccount, String roleName);

	void assignRoles(String userAccount, List<String> roleNames);

	void resetPwd(String account);

	void batchDelete(List<String> accountList);

	List<Role> listRoleByAccount(String account);

	/**
	 * 检查用户扩展属性，如果没有则创建
	 */
	void checkAndCreateUserExt();

	UserExt getUserExt(String userAccount);

	List<Org> listOrgByUser(String userAccount);

	List<OrgExt> listOrgExtByUser(String userAccount);

	void update(User user, UserExt userExt, List<UserOrg> userOrgList);

	void add(User user, UserExt userExt, List<UserOrg> userOrgList);

}
