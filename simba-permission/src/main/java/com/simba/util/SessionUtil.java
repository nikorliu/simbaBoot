package com.simba.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.simba.framework.util.common.PathUtil;
import com.simba.permission.model.Org;
import com.simba.permission.model.OrgExt;
import com.simba.permission.model.Permission;
import com.simba.permission.model.Role;
import com.simba.permission.model.User;
import com.simba.permission.model.UserExt;

/**
 * Session工具类
 * 
 * @author caozj
 * 
 */
public class SessionUtil {

	// 存在session里的用户对象的key
	public static final String userKey = "sessUser";

	// 存在session里的用户扩展对象的key
	public static final String userExtKey = "sessUserExt";

	// 存在session里的角色对象的key
	public static final String roleKey = "sessRole";

	// 存在session里的权限对象的key
	public static final String permissionKey = "sessPermission";

	// 存在session里的机构对象的key
	public static final String orgKey = "sessOrg";

	// 存在session里的机构扩展对象的key
	public static final String orgExtKey = "sessOrgExt";

	// 存在session里的 用户有权限访问的所有的url地址的key
	public static final String permissionUrlKey = "sessPermissionUrls";

	// 存在session里的标志，代表用户是否为超级管理员
	public static final String isAdminKey = "sessAdmin";

	// 存在session里的验证码的标志
	public static final String captchaKey = "sessCaptcha";

	public static void setCaptcha(HttpSession session, String captcha) {
		session.setAttribute(captchaKey, captcha);
	}

	public static boolean checkCaptcha(HttpSession session, String captcha) {
		String sCaptcha = (String) session.getAttribute(captchaKey);
		return StringUtils.equalsIgnoreCase(sCaptcha, captcha);
	}

	public static void setAdmin(HttpSession session) {
		session.setAttribute(isAdminKey, true);
	}

	public static boolean isAdmin(HttpSession session) {
		Boolean isAdmin = (Boolean) session.getAttribute(isAdminKey);
		return isAdmin != null && isAdmin;
	}

	@SuppressWarnings("unchecked")
	public static List<Org> getOrgs(HttpSession session) {
		return (List<Org>) session.getAttribute(orgKey);
	}

	public static void setOrgs(HttpSession session, List<Org> orgs) {
		session.setAttribute(orgKey, orgs);
	}

	@SuppressWarnings("unchecked")
	public static List<OrgExt> getOrgExts(HttpSession session) {
		return (List<OrgExt>) session.getAttribute(orgExtKey);
	}

	public static void setOrgExts(HttpSession session, List<OrgExt> exts) {
		session.setAttribute(orgExtKey, exts);
	}

	public static User getUser(HttpSession session) {
		return (User) session.getAttribute(userKey);
	}

	public static void setUser(HttpSession session, User user) {
		session.setAttribute(userKey, user);
	}

	public static UserExt getUserExt(HttpSession session) {
		return (UserExt) session.getAttribute(userExtKey);
	}

	public static void setUserExt(HttpSession session, UserExt userExt) {
		session.setAttribute(userExtKey, userExt);
	}

	@SuppressWarnings("unchecked")
	public static List<Role> getRoles(HttpSession session) {
		List<Role> list = (List<Role>) session.getAttribute(roleKey);
		return list != null ? list : new ArrayList<Role>(0);
	}

	public static void setRoles(HttpSession session, List<Role> roles) {
		session.setAttribute(roleKey, roles);
	}

	@SuppressWarnings("unchecked")
	public static List<Permission> getPermissions(HttpSession session) {
		List<Permission> list = (List<Permission>) session.getAttribute(permissionKey);
		return list != null ? list : new ArrayList<Permission>(0);
	}

	public static void setPermissions(HttpSession session, List<Permission> permissionList) {
		session.setAttribute(permissionKey, permissionList);
		Set<String> permissionUrlSet = new HashSet<String>();
		for (Permission permission : permissionList) {
			String url = permission.getUrl();
			if (StringUtils.isEmpty(url)) {
				continue;
			}
			String[] urls = url.trim().split(",");
			for (String regUrl : urls) {
				if (StringUtils.isNotEmpty(regUrl)) {
					permissionUrlSet.add(regUrl);
				}
			}
		}
		session.setAttribute(permissionUrlKey, permissionUrlSet);
	}

	@SuppressWarnings("unchecked")
	public static Set<String> getPermissionUrl(HttpSession session) {
		Set<String> set = (Set<String>) session.getAttribute(permissionUrlKey);
		return set != null ? set : new HashSet<String>(0);
	}

	public static boolean hasPermission(HttpSession session, String uri) {
		if (isAdmin(session)) {
			return true;
		}
		if (StringUtils.isBlank(uri)) {
			return false;
		}
		Set<String> permissionUrlSet = SessionUtil.getPermissionUrl(session);
		boolean hasPermission = false;
		for (String permissionUrl : permissionUrlSet) {
			if (PathUtil.match(uri, permissionUrl)) {
				hasPermission = true;
				break;
			}
		}
		return hasPermission;
	}

	public static boolean hasPermission(HttpServletRequest request) {
		String uri = getRequestUri(request);
		return hasPermission(request.getSession(), uri);
	}

	/**
	 * 用户是否登录
	 * 
	 * @param session
	 * @return
	 */
	public static boolean isLogin(HttpSession session) {
		return getUser(session) != null;
	}

	/**
	 * 从request中取出uri.经过处理，已经过滤了上下文路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestUri(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (StringUtils.isNotEmpty(contextPath) && !"/".equals(contextPath)) {
			uri = uri.substring(contextPath.length());
		}
		return uri;
	}

	public static void clearSession(HttpSession session) {
		session.invalidate();
	}
}
