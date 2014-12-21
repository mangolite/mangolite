package com.webutils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.utils.ContextUtil;

@SuppressWarnings("unchecked")
public final class WebAppContext {
	// following keys in the context map is reserved by infra team
	public static final String USER = "user";
	public static final String CONTEXTURL = "contextURL";
	public static final String REQUESTCONTEXTURL = "requestcontext";
	public static final String WEB_REQUEST = "web_request";
	public static final String COMPONENT_WEBSITE = "website";

	private WebAppContext() {
		// Sonar code fix --> Utility classes should not have a public of
		// default constructor
		throw new IllegalStateException("Sorry!!");
	}

	public static Map<String, Object> get() {
		Map<String, Object> coreComponent = (Map<String, Object>) ContextUtil
				.get().get(COMPONENT_WEBSITE);
		if (coreComponent == null) {
			coreComponent = new HashMap<String, Object>();
			ContextUtil.get().put(COMPONENT_WEBSITE, coreComponent);
		}
		return coreComponent;
	}

	public static String getContext() {
		return (String) get().get(CONTEXTURL);
	}

	public static void setContext(String contextURL) {
		if (contextURL != null) {
			get().put(CONTEXTURL, contextURL);
		}
	}

	public static void setRequestContext(WebSockRequest webRequest) {
		get().put(WEB_REQUEST, webRequest);
	}

	public static WebSockRequest getRequestContext() {
		return (WebSockRequest) get().get(WEB_REQUEST);
	}

	public static String getUserToken() {
		return ((WebSockRequest) get().get(WEB_REQUEST)).getUserToken();
	}

	public static void setSession(HttpSession session) {
		// TODO Auto-generated method stub
	}
	
	public static void setUser(AbstractUser user) {
		// TODO Auto-generated method stub
	}

	public static AbstractUser getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void clear() {
		get().clear();
	}
}
