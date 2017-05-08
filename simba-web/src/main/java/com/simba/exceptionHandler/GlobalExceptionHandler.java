package com.simba.exceptionHandler;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.simba.exception.ForbidException;
import com.simba.framework.util.json.JsonResult;

@ControllerAdvice
class GlobalExceptionHandler {

	private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Throwable.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		logger.error("发生异常", e);
		ModelAndView model = new ModelAndView();
		if (isJsonException(req)) {
			model.setViewName("message");
			model.addObject("message", new JsonResult(e.getMessage(), 400).toJson());
		} else if (e instanceof LoginException) {
			model.addObject("top", true);
			model.setViewName("login");
		} else if (e instanceof ForbidException) {
			model.setViewName("error/forbid");
		} else {
			model.setViewName("error/error");
			model.addObject("message", e.getMessage());
		}
		return model;
	}

	/**
	 * 判断是否是json格式请求，如果是json格式请求，返回json格式错误信息
	 * 
	 * @param request
	 * @return
	 */
	private boolean isJsonException(HttpServletRequest request) {
		boolean isJson = false;
		if (request.getParameter("json") != null || request.getParameter("jsonp") != null
				|| (request.getHeader("Accept") != null && request.getHeader("Accept").indexOf("json") > -1
						|| request.getRequestURI().endsWith(".json") || request.getRequestURI().endsWith(".jsonp"))) {
			isJson = true;
		}
		return isJson;
	}
}