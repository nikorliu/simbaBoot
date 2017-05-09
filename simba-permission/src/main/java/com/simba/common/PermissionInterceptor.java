package com.simba.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.simba.exception.ForbidException;
import com.simba.framework.util.applicationcontext.ApplicationContextUtil;
import com.simba.framework.util.common.PathUtil;
import com.simba.util.SessionUtil;

/**
 * 权限拦截器
 * 
 * @author caozj
 */
public class PermissionInterceptor implements HandlerInterceptor {

	private String excludeUrl;

	private List<String> excludeUrls = new ArrayList<String>();

	public PermissionInterceptor() {
		super();
		init();
	}

	private void init() {
		EnvironmentUtil environmentUtil = ApplicationContextUtil.getBean(EnvironmentUtil.class);
		excludeUrl = environmentUtil.get("permission.interceptor.exclude");
		String[] urls = excludeUrl.split(",");
		for (String url : urls) {
			excludeUrls.add(url);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUri = SessionUtil.getRequestUri(request);
		for (String excludeUrl : excludeUrls) {
			if (PathUtil.match(requestUri, excludeUrl)) {
				return true;
			}
		}
		HttpSession session = request.getSession();
		if (SessionUtil.isAdmin(session)) {
			return true;
		}
		if (SessionUtil.hasPermission(request)) {
			return true;
		}
		throw new ForbidException("您没有权限访问,所以不能访问" + requestUri);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
