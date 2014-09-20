package com.webutils;

import com.utils.DebugUtil;

public final class WebDebugUtils {
	public static String PROP_MIN_RESOURCES = "minResources";
	public static String PROP_CACHE_RESOURCES = "cacheMinResources";
	public static boolean MIN_RESOURCES = true;
	public static boolean CACHE_RESOURCES = true;
	static {
		if (DebugUtil.getDebugProperties().containsKey(PROP_MIN_RESOURCES)) {
			MIN_RESOURCES = "true".equals(DebugUtil.getDebugProperties().get(
					PROP_MIN_RESOURCES));
		}
		if (DebugUtil.getDebugProperties().containsKey(PROP_CACHE_RESOURCES)) {
			CACHE_RESOURCES = "true".equals(DebugUtil.getDebugProperties().get(
					PROP_CACHE_RESOURCES));
		}
	}

	public static boolean isMinResourcesEnabled() {
		return MIN_RESOURCES;
	}

	public static boolean isResoucesCacheEnabled() {
		return CACHE_RESOURCES;
	}

}
