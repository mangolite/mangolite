package org.spamjs.mangolite;

import org.spamjs.mangolite.app.WebAppContext;
import org.spamjs.mangolite.app.WebAppProperties;
import org.spamjs.utils.ContextUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class WebContextUtil.
 */
public final class WebContextUtil {
	// following keys in the context map is reserved by infra team
	/** The Constant USER. */
	public static final String USER = "user";
	
	/** The Constant CONTEXTURL. */
	public static final String CONTEXTURL = "contextURL";
	
	/** The Constant REQUESTCONTEXTURL. */
	public static final String REQUESTCONTEXTURL = "requestcontext";
	
	/** The Constant WEB_REQUEST. */
	public static final String WEB_REQUEST = "web_request";
	
	/** The Constant COMPONENT_WEBSITE. */
	public static final String COMPONENT_WEBSITE = "website";
	
	/** The Constant WEBSITE_USER. */
	public static final String WEBSITE_USER = "user";

	/** The wep app properties. */
	public static WebAppProperties wepAppProperties;

	/**
	 * Instantiates a new web context util.
	 */
	private WebContextUtil() {
		throw new IllegalStateException("Sorry!!");
	}

	/**
	 * Gets the.
	 *
	 * @return the web app context
	 */
	public static WebAppContext get() {
		WebAppContext coreComponent = (WebAppContext) ContextUtil.get().get(
				COMPONENT_WEBSITE);
		if (coreComponent == null) {
			coreComponent = new WebAppContext();
			ContextUtil.get().put(COMPONENT_WEBSITE, coreComponent);
		}
		return coreComponent;
	}

	/**
	 * Clear.
	 */
	public static void clear() {
		ContextUtil.get().put(COMPONENT_WEBSITE, null);
	}

}
