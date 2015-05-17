package org.spamjs.mangolite.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.ini4j.InvalidFileFormatException;
import org.spamjs.mangolite.WebUtilsEnum.FILE_TYPE;
import org.springframework.beans.factory.annotation.Value;

// TODO: Auto-generated Javadoc
/**
 * The Class WebAppProperties.
 */
public class WebAppProperties {

	/** The Constant DEFAULT_STATIC_LIB_PARH. */
	public static final String DEFAULT_STATIC_LIB_PARH = "/resources/weblib/";
	
	/** The Constant DEFAULT_STATIC_APP_PARH. */
	public static final String DEFAULT_STATIC_APP_PARH = "/resources/webroot/";

	/** The props. */
	private static Properties props = new Properties();

	/** The build build. */
	@Value("${build.debug}")
	private boolean buildBuild;
	
	/**
	 * Checks if is builds the build.
	 *
	 * @return true, if is builds the build
	 */
	public boolean isBuildBuild() {
		return this.buildBuild;
	}

	/** The app context. */
	@Value("${server.context-path}")
	private String appContext;
	
	/** The static lib resource path. */
	@Value("${resources.static.lib-path}")
	private String staticLibResourcePath;
	
	/** The static app resource path. */
	@Value("${resources.static.app-path}")
	private String staticAppResourcePath;
	
	/** The static js minify. */
	@Value("${resources.static.js.minify}")
	private boolean staticJSMinify = true;
	
	/** The static css minify. */
	@Value("${resources.static.css.minify}")
	private boolean staticCSSMinify = true;
	
	/** The static js cache. */
	@Value("${resources.static.js.cache}")
	private boolean staticJSCache = true;
	
	/** The static css cache. */
	@Value("${resources.static.css.cache}")
	private boolean staticCSSCache = true;

	/** The static lib resources path match. */
	private String staticLibResourcesPathMatch = "/(app/)*resources[0-9.]*/weblibs";
	
	/** The static app resource path match. */
	private String staticAppResourcePathMatch = "/(app/)*resources[0-9.]*/webroot/";

	// private String resourceLibPathRePlace = "/resources/weblibs/";
	// private String resourceAppPathRePlace = "/resources/webroot/";

	/**
	 * Gets the static lib path match.
	 *
	 * @return the static lib path match
	 */
	public String getStaticLibPathMatch() {
		return "/(" + appContext.replaceFirst("/", "") + "/)*"
				+ staticLibResourcePath.replaceFirst("/", "");
	}

	/**
	 * Gets the static app path match.
	 *
	 * @return the static app path match
	 */
	public String getStaticAppPathMatch() {
		return "/(" + appContext.replaceFirst("/", "") + "/)*"
				+ staticAppResourcePath.replaceFirst("/", "");
	}

	/**
	 * Gets the app context.
	 *
	 * @return the app context
	 */
	public String getAppContext() {
		return this.appContext;
	};

	/**
	 * Gets the static lib path.
	 *
	 * @return the static lib path
	 */
	public String getStaticLibPath() {
		return this.staticLibResourcePath;
	}

	/**
	 * Gets the static app path.
	 *
	 * @return the static app path
	 */
	public String getStaticAppPath() {
		return this.staticAppResourcePath;
	}

	/**
	 * Checks if is min resources enabled.
	 *
	 * @return true, if is min resources enabled
	 */
	public boolean isMinResourcesEnabled() {
		return this.staticJSMinify && this.staticCSSMinify;
	}

	/**
	 * Checks if is min resources enabled.
	 *
	 * @param fileType the file type
	 * @return true, if is min resources enabled
	 */
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

	/**
	 * Checks if is resouces cache enabled.
	 *
	 * @return true, if is resouces cache enabled
	 */
	public boolean isResoucesCacheEnabled() {
		return this.staticCSSCache && this.staticJSCache;
	}

	/**
	 * Checks if is resouces cache enabled.
	 *
	 * @param fileType the file type
	 * @return true, if is resouces cache enabled
	 */
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

	/**
	 * Scan.
	 *
	 * @param inputStream the input stream
	 * @throws InvalidFileFormatException the invalid file format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
