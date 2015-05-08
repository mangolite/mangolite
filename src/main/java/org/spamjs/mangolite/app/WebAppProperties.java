package org.spamjs.mangolite.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.ini4j.InvalidFileFormatException;
import org.spamjs.mangolite.WebUtilsEnum.FILE_TYPE;
import org.springframework.beans.factory.annotation.Value;

public class WebAppProperties {

	public static final String DEFAULT_STATIC_LIB_PARH = "/resources/weblib/";
	public static final String DEFAULT_STATIC_APP_PARH = "/resources/webroot/";

	private static Properties props = new Properties();

	@Value("${build.debug}")
	private boolean buildBuild;
	public boolean isBuildBuild() {
		return this.buildBuild;
	}

	@Value("${server.context-path}")
	private String appContext;
	@Value("${resources.static.lib-path}")
	private String staticLibResourcePath;
	@Value("${resources.static.app-path}")
	private String staticAppResourcePath;
	@Value("${resources.static.js.minify}")
	private boolean staticJSMinify = true;
	@Value("${resources.static.css.minify}")
	private boolean staticCSSMinify = true;
	@Value("${resources.static.js.cache}")
	private boolean staticJSCache = true;
	@Value("${resources.static.css.cache}")
	private boolean staticCSSCache = true;

	private String staticLibResourcesPathMatch = "/(app/)*resources[0-9.]*/weblibs";
	private String staticAppResourcePathMatch = "/(app/)*resources[0-9.]*/webroot/";

	// private String resourceLibPathRePlace = "/resources/weblibs/";
	// private String resourceAppPathRePlace = "/resources/webroot/";

	public String getStaticLibPathMatch() {
		return "/(" + appContext.replaceFirst("/", "") + "/)*"
				+ staticLibResourcePath.replaceFirst("/", "");
	}

	public String getStaticAppPathMatch() {
		return "/(" + appContext.replaceFirst("/", "") + "/)*"
				+ staticAppResourcePath.replaceFirst("/", "");
	}

	public String getAppContext() {
		return this.appContext;
	};

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
}
