package com.webutils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
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

public abstract class AsbtractResourceMinifyFilter implements Filter {

	private static final String PARAM_LINEBREAK = "line-break";
	private static final String PARAM_WARN = "warn";
	private static final String PARAM_NOMUNGE = "nomunge";
	private static final ResourcePackages packages = new ResourcePackages();
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
		packages.scanPacks(filterConfig.getServletContext(), getLibPath());
		packages.scanPacks(filterConfig.getServletContext(), getResourcesPath());
	}

	public abstract Map<String, String> getCache();

	/**
	 * WebApp-Relative path of folder to lib files eg : "/lib/"
	 * 
	 * @return the lib path
	 */
	public abstract String getLibPath();

	/**
	 * WebApp-Relative path of folder to resources files eg : "/resources/"
	 * 
	 * @return
	 */
	public abstract String getResourcesPath();

	public String filterURI(String requestURI) {
		return requestURI;
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// ServletOutputStream servletOutputStream =
		// response.getOutputStream();
		response.setContentType("application/x-javascript; charset=UTF-8");
		// response.setCharacterEncoding("UTF-8");
		ServletContext context = filterConfig.getServletContext();
		String requestURI = filterURI(request.getRequestURI());
		InputStream inputStream = context.getResourceAsStream(requestURI);
		if (inputStream == null) {
			inputStream = context.getResourceAsStream(packages
					.getModulePath(requestURI));
		}
		String morefiles = request.getParameter("@");
		String packsParam = request.getParameter("$");
		Boolean skipMinification = (request.getParameter("no") != null) ? Boolean.TRUE
				: Boolean.FALSE;
		String[] files = {};
		if (morefiles != null) {
			files = morefiles.split(",");
		}
		ResourceWriter writer = new ResourceWriter(response);
		if (packsParam != null) {
			writePacks(writer, packsParam.split(","));
		} else if (WebDebugUtils.isMinResourcesEnabled() && !skipMinification) {
			writeMinifiedFiles(writer, context, requestURI, inputStream, files);
		} else {
			writeOriginalFiles(writer, context, inputStream, files);
		}
	}

	private void writePacks(ResourceWriter writer, String[] packs)
			throws IOException {
		// PrintWriter printWriter = response.getWriter();
		write(packages.writePacks(packs), writer);
		writer.getPrintWriter().close();
	}

	private void writeMinifiedFiles(ResourceWriter writer,
			ServletContext context, String requestURI, InputStream inputStream,
			String[] files) throws IOException {
		// PrintWriter printWriter = writer.getWriter();
		writeMinifiedFileToServletOutputStream(requestURI, inputStream, writer);
		for (String file : files) {
			if (file != null && !file.isEmpty()) {
				InputStream inputStreamTemp = context
						.getResourceAsStream(filterURI(file));
				if (inputStreamTemp != null) {
					writeMinifiedFileToServletOutputStream(file,
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
			InputStream inputStream, ResourceWriter writer) throws IOException {
		String s;
		if (!WebDebugUtils.isResoucesCacheEnabled()
				|| !getCache().containsKey(requestURI)) {
			if (requestURI.endsWith(ResourcePackages.EXT_JS)) {
				s = getCompressedJavaScript(inputStream, requestURI);
			} else if (requestURI.endsWith(ResourcePackages.EXT_CSS)) {
				s = getCompressedCss(inputStream);
			} else {
				s = "This file format is not supported by this filter. Only .css and .js are supported";
			}
			if (WebDebugUtils.isResoucesCacheEnabled())
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

}
