package com.webutils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.utils.Log;
import com.utils.UniqueID;

public class AccessInterceptor implements HandlerInterceptor {
	private static final Log LOG = new Log();
	public static final String JSESSIONID = "JSESSIONID";
	public static final String EMPTY_STRING = "";
	public static final int AUTH_ERROR_CODE = 401;
	public static final String AUTH_ERROR_MESSAGE = "Un-Authorised Request";
	public static final String BYPASS_URL_PREFIX = "/auth/";
	public static final String RESOURCES_URL_PREFIX = "/resources";
	public static final String COOKIES_PATH = "/";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String jSessionId = extractSessionId(request.getCookies());
		String context = request.getRequestURI();
		request.setAttribute(JSESSIONID, jSessionId);
		LOG.trace("Called for " + context);
		AbstractUser user;
		if (context == null || context.contains(BYPASS_URL_PREFIX)
				|| context.contains(RESOURCES_URL_PREFIX)) {
			return true;
		} else {
			HttpSession session = request.getSession(true);
			user = (AbstractUser) session.getAttribute("currentSessionUser");
			if (user == null) {
				user = AbstractWebAppClient.getUser();
				session.setAttribute("currentSessionUser",user);
				user.setSessionID(session.getId());
				session.setMaxInactiveInterval(60);
			}
			if (user == null) {
				response.sendError(AUTH_ERROR_CODE, AUTH_ERROR_MESSAGE);
				return false;
			}
		}
		WebAppContext.setUser(user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		try {
			AbstractUser user = WebAppContext.getUser();
			if (user != null) {
				if(user.isSetTimeOut()){
					HttpSession session = request.getSession(true);
					session.setMaxInactiveInterval(user.getSessionTimout()*60);
				}
				// UPDATE CAHCE
			}
		} finally {
			WebAppContext.clear();
		}
	}

	public static String extractSessionId(Cookie[] cookies) {
		String jSessionID = EMPTY_STRING;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(JSESSIONID)) {
					jSessionID = cookie.getValue();
					break;
				}
			}
		}
		return jSessionID;
	}

	public static Cookie getSessionCookie(AbstractUser user) {
		return getSessionCookie(user, EMPTY_STRING);
	}

	public static Cookie getSessionCookie() {
		return getSessionCookie(null, EMPTY_STRING);
	}

	public static Cookie getSessionCookie(AbstractUser user, String defaultValue) {
		return getSessionCookie(user, defaultValue, COOKIES_PATH);
	}

	public static Cookie getSessionCookie(AbstractUser user,
			String defaultValue, String path) {
		Cookie cookie = null;
		if (user != null && user.isValid()) {
			cookie = new Cookie(JSESSIONID, user.getSessionID());
			cookie.setMaxAge(-1);
		} else {
			cookie = new Cookie(JSESSIONID, defaultValue);
			cookie.setMaxAge(0);
		}
		cookie.setPath(path);
		return cookie;
	}
}
