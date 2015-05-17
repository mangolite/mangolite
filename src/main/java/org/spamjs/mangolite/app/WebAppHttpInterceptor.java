package org.spamjs.mangolite.app;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.spamjs.mangolite.WebContextUtil;
import org.spamjs.mangolite.WebUtilsConstants;
import org.spamjs.mangolite.abstracts.AbstractUser;
import org.spamjs.utils.Log;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc
/**
 * The Class WebAppHttpInterceptor.
 */
public class WebAppHttpInterceptor implements HandlerInterceptor {
	
	/** The Constant LOG. */
	private static final Log LOG = new Log();
	
	/** The Constant JSESSIONID. */
	public static final String JSESSIONID = "JSESSIONID";
	
	/** The Constant EMPTY_STRING. */
	public static final String EMPTY_STRING = "";
	
	/** The Constant AUTH_ERROR_CODE. */
	public static final int AUTH_ERROR_CODE = 401;
	
	/** The Constant AUTH_ERROR_MESSAGE. */
	public static final String AUTH_ERROR_MESSAGE = "Un-Authorised Request";
	
	/** The Constant BYPASS_URL_PREFIX. */
	public static final String BYPASS_URL_PREFIX = "/auth/";
	
	/** The Constant RESOURCES_URL_PREFIX. */
	public static final String RESOURCES_URL_PREFIX = "/resources";
	
	/** The Constant COOKIES_PATH. */
	public static final String COOKIES_PATH = "/";

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
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
			user = (AbstractUser) session
					.getAttribute(WebUtilsConstants.CURRENT_SESSION_USER);
			if (user == null) {
				user = WebAppClient.getUser();
				session.setAttribute(WebUtilsConstants.CURRENT_SESSION_USER,
						user);
				user.setSessionID(session.getId());
				session.setMaxInactiveInterval(60);
			}
			// if (user == null) {
			// response.sendError(AUTH_ERROR_CODE, AUTH_ERROR_MESSAGE);
			// return false;
			// }
		}
		WebContextUtil.get().setUser(user);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		try {
			AbstractUser user = WebContextUtil.get().getUser();
			if (user != null) {
				HttpSession session = request.getSession(true);
				if (WebContextUtil.get().wasUserValidated()) {

					session.setMaxInactiveInterval(user.getSessionTimout() * 60);
				} else if (WebContextUtil.get().wasUserInValidated()) {
					session.invalidate();
				}
				// UPDATE CAHCE
			}
		} catch (Exception e){
			LOG.error("afterComplettion", e);
		}finally {
			WebContextUtil.clear();
		}
	}

	/**
	 * Extract session id.
	 *
	 * @param cookies the cookies
	 * @return the string
	 */
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

	/**
	 * Gets the session cookie.
	 *
	 * @param user the user
	 * @return the session cookie
	 */
	public static Cookie getSessionCookie(AbstractUser user) {
		return getSessionCookie(user, EMPTY_STRING);
	}

	/**
	 * Gets the session cookie.
	 *
	 * @return the session cookie
	 */
	public static Cookie getSessionCookie() {
		return getSessionCookie(null, EMPTY_STRING);
	}

	/**
	 * Gets the session cookie.
	 *
	 * @param user the user
	 * @param defaultValue the default value
	 * @return the session cookie
	 */
	public static Cookie getSessionCookie(AbstractUser user, String defaultValue) {
		return getSessionCookie(user, defaultValue, COOKIES_PATH);
	}

	/**
	 * Gets the session cookie.
	 *
	 * @param user the user
	 * @param defaultValue the default value
	 * @param path the path
	 * @return the session cookie
	 */
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
