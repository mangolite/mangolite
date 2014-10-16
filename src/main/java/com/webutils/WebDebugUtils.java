package com.webutils;

import java.io.IOException;
import java.util.Properties;

import com.utils.DebugUtil;

public final class WebDebugUtils {
	public static final String PROP_MIN_RESOURCES = "minResources";
	public static final String PROP_CACHE_RESOURCES = "cacheMinResources";
	private static final String DEBUG_BUILD_PROP = "debugBuild";
	public static boolean MIN_RESOURCES = true;
	public static boolean CACHE_RESOURCES = true;
	public static boolean DEBUG_BUILD = true;
	private static Properties webDebugUtilsDebugProperties;
	static {
		boolean temporaryDebugBuild = false;
		webDebugUtilsDebugProperties = new Properties();
		try {
			webDebugUtilsDebugProperties.load(WebDebugUtils.class
					.getResourceAsStream("/debug.properties"));

			if (webDebugUtilsDebugProperties.containsKey(PROP_MIN_RESOURCES)) {
				temporaryDebugBuild = "true"
						.equals(webDebugUtilsDebugProperties
								.get(DEBUG_BUILD_PROP));
			} else {
				temporaryDebugBuild = DebugUtil.isDebugBuild();
			}

			if (webDebugUtilsDebugProperties.containsKey(PROP_MIN_RESOURCES)) {
				MIN_RESOURCES = "true".equals(DebugUtil.getDebugProperties()
						.get(PROP_MIN_RESOURCES));
			}
			if (webDebugUtilsDebugProperties.containsKey(PROP_CACHE_RESOURCES)) {
				CACHE_RESOURCES = "true".equals(DebugUtil.getDebugProperties()
						.get(PROP_CACHE_RESOURCES));
			}
		} catch (IOException e) {
			e.printStackTrace();
			temporaryDebugBuild = DebugUtil.isDebugBuild();
		}
		DEBUG_BUILD = temporaryDebugBuild;
	}

	public static boolean isMinResourcesEnabled() {
		return MIN_RESOURCES;
	}

	public static boolean isResoucesCacheEnabled() {
		return CACHE_RESOURCES;
	}

	public static boolean isDebugBuild() {
		return DEBUG_BUILD;
	}

}
