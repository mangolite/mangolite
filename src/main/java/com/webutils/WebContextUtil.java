package com.webutils;

import com.utils.ContextUtil;
import com.webutils.app.WebAppContext;
import com.webutils.app.WebAppProperties;

public final class WebContextUtil {
	// following keys in the context map is reserved by infra team
	public static final String USER = "user";
	public static final String CONTEXTURL = "contextURL";
	public static final String REQUESTCONTEXTURL = "requestcontext";
	public static final String WEB_REQUEST = "web_request";
	public static final String COMPONENT_WEBSITE = "website";
	public static final String WEBSITE_USER = "user";

	public static WebAppProperties wepAppProperties;

	private WebContextUtil() {
		throw new IllegalStateException("Sorry!!");
	}

	public static WebAppContext get() {
		WebAppContext coreComponent = (WebAppContext) ContextUtil.get().get(
				COMPONENT_WEBSITE);
		if (coreComponent == null) {
			coreComponent = new WebAppContext();
			ContextUtil.get().put(COMPONENT_WEBSITE, coreComponent);
		}
		return coreComponent;
	}

	public static void clear() {
		ContextUtil.get().put(COMPONENT_WEBSITE, null);
	}

}
