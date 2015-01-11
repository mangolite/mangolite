package com.webutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.ini4j.InvalidFileFormatException;

import com.utils.DebugUtil;
import com.webutils.WebUtilsEnum.FILE_TYPE;

public class WebAppProperties {

	private static Properties props = new Properties();

	// @Value("${server.context-path}")
	private String appContext;
	private String staticLibResourcePath;
	private String staticAppResourcePath;
	private boolean staticJSMinify = true;
	private boolean staticCSSMinify = true;
	private boolean staticJSCache = true;
	private boolean staticCSSCache = true;

	public void setAppContext(String appContext) {
		this.appContext = appContext;
	}

	public String getAppContext() {
		return this.appContext;
	};

	public void scan(InputStream inputStream)
			throws InvalidFileFormatException, IOException {
		props.load(inputStream);
		this.appContext = props.getProperty("server.context-path");
		this.staticLibResourcePath = props
				.getProperty("resources.static.lib-path");
		this.staticAppResourcePath = props
				.getProperty("resources.static.app-path");

		if (props.containsKey("resources.static.js.minify")) {
			staticJSMinify = "true".equalsIgnoreCase(props
					.getProperty("resources.static.js.minify"));
		}
		if (props.containsKey("resources.static.css.minify")) {
			staticCSSMinify = "true".equalsIgnoreCase(props
					.getProperty("resources.static.css.minify"));
		}
		if (props.containsKey("resources.static.js.cache")) {
			staticJSCache = "true".equalsIgnoreCase(props
					.getProperty("resources.static.js.cache"));
		}
		if (props.containsKey("resources.static.css.cache")) {
			staticCSSCache = "true".equalsIgnoreCase(props
					.getProperty("resources.static.css.cache"));
		}
	}

	public String getStaticLibPath() {
		return this.staticLibResourcePath;
	}

	public String getStaticAppPath() {
		return this.staticAppResourcePath;
	}

	public boolean isMinResourcesEnabled() {
		return this.staticJSMinify && this.staticCSSMinify;
	}

	public boolean isMinResourcesEnabled(FILE_TYPE fileType) {
		switch (fileType) {
		case JS:
			return this.staticJSMinify;
		case CSS:
			return this.staticCSSMinify;
		default:
			return false;
		}
	}

	public boolean isResoucesCacheEnabled() {
		return this.staticCSSCache && this.staticJSCache;
	}

	public boolean isResoucesCacheEnabled(FILE_TYPE fileType) {
		switch (fileType) {
		case JS:
			return this.staticJSCache;
		case CSS:
			return this.staticCSSCache;
		default:
			return false;
		}
	}
}
