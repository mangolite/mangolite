package org.spamjs.mangolite.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spamjs.mangolite.ResourceMinifyFilterErrorReporter;
import org.spamjs.mangolite.ResourceWriter;
import org.spamjs.mangolite.WebUtilsEnum;
import org.spamjs.mangolite.manager.ResourcePackages;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * Servlet Filter to Minify JavaScript and CSS files on the fly, currently it
 * uses YUI compressor, it can be changed
 * 
 * NOTE:- Handling multiple files at once, support need to be added
 * http://www.julienlecomte.net/blog/2007/08/11/
 * http://yuilibrary.com/download/#yuicompressor
 * 
 *
 * <p>
 * <filter> <display-name>Files Minify Filter</display-name>
 * <filter-name>ResourceMinifyFilter</filter-name>
 * <filter-class>com.dfferentia.filter.ResourceMinifyFilter</filter-class>
 * <init-param> <param-name>preserve-semi</param-name>
 * <param-value>true</param-value> </init-param> </filter> <filter-mapping>
 * <filter-name>ResourceMinifyFilter</filter-name>
 * <url-pattern>*.js</url-pattern> <dispatcher>REQUEST</dispatcher>
 * </filter-mapping> <filter-mapping>
 * <filter-name>ResourceMinifyFilter</filter-name>
 * <url-pattern>*.css</url-pattern> <dispatcher>REQUEST</dispatcher>
 * </filter-mapping> <servlet-mapping> <servlet-name>appServlet</servlet-name>
 * <url-pattern>/</url-pattern> </servlet-mapping>
 * <p>
 * 
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * 
 * @version 1.0
 */

public class WebAppResourceMinifyFilter implements Filter {

	private static final String PARAM_LINEBREAK = "line-break";
	private static final String PARAM_WARN = "warn";
	private static final String PARAM_NOMUNGE = "nomunge";
	private static final String JS_CONTENT_TYPE = "application/x-javascript; charset=UTF-8";
	private static final String CSS_CONTENT_TYPE = "text/css; charset=UTF-8";
	private static final String WEBJAR_PATH = "/webjars/";
	private static final String FILES_LIST_PARAM = "@";
	private static final String BUNDLE_LIST_PARAM = "$";
	private static final String LIST_DELIMETER = ",";
	private static final String NO_MIN_PARAM = "no";

	private static final ResourcePackages packages = new ResourcePackages();
	private static Map<String, String> cache = new Hashtable<String, String>();
	protected FilterConfig filterConfig;

	/**
	 * Insert a line break after the specified column number
	 */
	protected int lineBreakPos = -1;

	/**
	 * Display possible errors in the code
	 */
	protected boolean warn = false;

	/**
	 * Minify only, do not obfuscate
	 */
	protected boolean munge = true;

	/**
	 * Preserve unnecessary semicolons
	 */
	protected boolean preserveAllSemiColons = false;
	protected boolean disableOptimizations = false;

	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;

		String lineBreak = filterConfig.getInitParameter(PARAM_LINEBREAK);
		if (lineBreak != null) {
			lineBreakPos = Integer.parseInt(lineBreak);
		}

		String warnString = filterConfig.getInitParameter(PARAM_WARN);
		if (warnString != null) {
			warn = Boolean.parseBoolean(warnString);
		}

		String noMungeString = filterConfig.getInitParameter(PARAM_NOMUNGE);
		if (noMungeString != null) {
			/**
			 * swap values because it's nomunge
			 */
			munge = Boolean.parseBoolean(noMungeString) ? false : true;
		}

		String preserveAllSemiColonsString = filterConfig
				.getInitParameter("preserve-semi");
		if (preserveAllSemiColonsString != null) {
			preserveAllSemiColons = Boolean
					.parseBoolean(preserveAllSemiColonsString);
		}
		packages.scanResources(filterConfig.getServletContext());
	}

	public Map<String, String> getCache() {
		return cache;
	}

	public String filterURI(String requestURI) {
		return requestURI.replaceAll(
				WebAppClient.getWebAppProperties().getStaticAppPathMatch(),
				WebAppClient.getWebAppProperties().getStaticAppPath())
				.replaceAll(
						WebAppClient.getWebAppProperties()
								.getStaticLibPathMatch(),
						WebAppClient.getWebAppProperties().getStaticLibPath());
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		// response.setCharacterEncoding("UTF-8");
		ServletContext context = filterConfig.getServletContext();
		// String requestedURI =request.getRequestURI();
		String requestedURI = request.getServletPath();
		WebUtilsEnum.FILE_TYPE fileType = WebUtilsEnum.FILE_TYPE.OTHER;

		if (requestedURI.endsWith(ResourcePackages.EXT_JS)) {
			fileType = WebUtilsEnum.FILE_TYPE.JS;
			response.setContentType(JS_CONTENT_TYPE);
		} else if (requestedURI.endsWith(ResourcePackages.EXT_CSS)) {
			fileType = WebUtilsEnum.FILE_TYPE.CSS;
			response.setContentType(CSS_CONTENT_TYPE);
		}

		if (!requestedURI.startsWith(WEBJAR_PATH)) {

			if (WebAppClient.getWebAppProperties().isBuildBuild()
					&& !("XMLHttpRequest".equals(request
							.getHeader("X-Requested-With")))) {
				Long nowTime = (new Date()).getTime();
				if((nowTime-packages.getLastScanTime())>3000){
					packages.scanResources(filterConfig.getServletContext());
				}
			}

			String requestURI = filterURI(requestedURI);
			InputStream inputStream = context.getResourceAsStream(requestURI);
			if (inputStream == null) {
				inputStream = context.getResourceAsStream(packages
						.getModulePath(requestURI));
			}
			String morefiles = request.getParameter(FILES_LIST_PARAM);
			String bundleParam = request.getParameter(BUNDLE_LIST_PARAM);
			Boolean skipMinification = (request.getParameter(NO_MIN_PARAM) != null) ? Boolean.TRUE
					: Boolean.FALSE;
			String[] files = {};
			if (morefiles != null) {
				files = morefiles.split(LIST_DELIMETER);
			}
			ResourceWriter writer = new ResourceWriter(response);
			if (bundleParam != null) {
				String cb = request.getParameter("cb");
				writePacks(writer, bundleParam.split(LIST_DELIMETER), cb);
			} else if (WebAppClient.getWebAppProperties()
					.isMinResourcesEnabled(fileType) && !skipMinification) {
				writeMinifiedFiles(writer, context, requestURI, fileType,
						inputStream, files);
			} else {
				writeOriginalFiles(writer, context, inputStream, files);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

	private void writePacks(ResourceWriter writer, String[] packs, String cb)
			throws IOException {
		// PrintWriter printWriter = response.getWriter();
		write(packages.writePacks(packs, cb), writer);
		writer.getPrintWriter().close();
	}

	private void writeMinifiedFiles(ResourceWriter writer,
			ServletContext context, String requestURI,
			WebUtilsEnum.FILE_TYPE fileType, InputStream inputStream,
			String[] files) throws IOException {
		// PrintWriter printWriter = writer.getWriter();
		if (inputStream != null) {
			writeMinifiedFileToServletOutputStream(requestURI, fileType,
					inputStream, writer);
		}
		for (String file : files) {
			if (file != null && !file.isEmpty()) {
				String uri = filterURI(file);
				InputStream inputStreamTemp = context.getResourceAsStream(uri);
				if (inputStreamTemp != null) {
					writeMinifiedFileToServletOutputStream(file, fileType,
							inputStreamTemp, writer);
				}
			}
		}
		writer.getPrintWriter().close();
	}

	private void writeOriginalFiles(ResourceWriter writer,
			ServletContext context, InputStream inputStream, String[] files)
			throws IOException {
		writer.write(inputStream);
		for (String file : files) {
			if (file != null && !file.isEmpty()) {
				InputStream inputStreamTemp = context
						.getResourceAsStream(filterURI(file));
				if (inputStreamTemp != null) {
					writer.write(inputStreamTemp);
				}
			}
		}
		writer.getOutputStream().flush();
	}

	private void writeMinifiedFileToServletOutputStream(String requestURI,
			WebUtilsEnum.FILE_TYPE fileType, InputStream inputStream,
			ResourceWriter writer) throws IOException {
		String s;
		if (!WebAppClient.getWebAppProperties()
				.isResoucesCacheEnabled(fileType)
				|| !getCache().containsKey(requestURI)) {
			if (fileType == WebUtilsEnum.FILE_TYPE.JS) {
				s = getCompressedJavaScript(inputStream, requestURI);
			} else if (fileType == WebUtilsEnum.FILE_TYPE.CSS) {
				s = getCompressedCss(inputStream);
			} else {
				s = "This file format is not supported by this filter. Only .css and .js are supported";
			}

			if (WebAppClient.getWebAppProperties().isResoucesCacheEnabled(
					fileType))
				getCache().put(requestURI, s);
		} else {
			s = getCache().get(requestURI);
		}
		write(s, writer);
	}

	/**
	 * Write s to servletOutputStream
	 *
	 * @param s
	 * @param writer
	 * @throws IOException
	 */
	private void write(String s, ResourceWriter writer) throws IOException {
		writer.getPrintWriter().print(s);
	}

	/**
	 * Note that the inputStream is closed!
	 *
	 * @param inputStream
	 * @param requestURI
	 * @throws IOException
	 */
	private String getCompressedJavaScript(InputStream inputStream,
			String requestURI) throws IOException {
		InputStreamReader isr = new InputStreamReader(inputStream);
		ResourceMinifyFilterErrorReporter errorWriter = new ResourceMinifyFilterErrorReporter(
				requestURI);
		try {
			JavaScriptCompressor compressor = new JavaScriptCompressor(isr,
					errorWriter);
			inputStream.close();

			StringWriter out = new StringWriter();
			compressor.compress(out, lineBreakPos, munge, warn,
					preserveAllSemiColons, disableOptimizations);
			out.flush();

			StringBuffer buffer = out.getBuffer();
			return buffer.toString();
		} catch (Exception e) {
			return errorWriter.getError();
		}
	}

	/**
	 * Note that the inputStream is closed!
	 *
	 * @param inputStream
	 * @throws IOException
	 */
	private String getCompressedCss(InputStream inputStream) throws IOException {
		InputStreamReader isr = new InputStreamReader(inputStream);
		CssCompressor compressor = new CssCompressor(isr);
		inputStream.close();

		StringWriter out = new StringWriter();
		compressor.compress(out, lineBreakPos);
		out.flush();

		StringBuffer buffer = out.getBuffer();
		return buffer.toString();
	}

	public List<String> getResourcerlPatterns() {
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("*.js");
		urlPatterns.add("*.css");
		urlPatterns.add("*.json");
		urlPatterns.add("*.html");
		return urlPatterns;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
