package com.webutils.tags;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import com.utils.FileUtil;
import com.webutils.tags.TagElement.ElementType;

/**
 * This is JSP tag, makes it possible to include java-script in JSP using
 * relative path and code snippet, no need to add or specify version or
 * resource-folder in path,.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Jun 24, 2014
 */

public class JScript extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "jscript";

	/** The Constant PATH_PREFIX. */
	private static final String PATH_PREFIX =  "Website.getContext()" + "resources" + "Website.getFversion()";

	/** The Constant RES_PREFIX. */
	private static final String RES_PREFIX = "/files/";

	/** The Constant SCRIPT_PREFIX. */
	private static final String SCRIPT_PREFIX = "</script><script type='text/javascript' src='";

	/** The Constant SCRIPT_SUFFIX. */
	private static final String SCRIPT_SUFFIX = "?v=" + "Website.getDebugTime()" + "' ></script><script>";
	// private static final String SCRIPT_SRC = "src";
	private static final String CLEAN_SCRIPT_CLASS = "<script class=''  ></script>";
	private static final String CLEAN_SCRIPT_EMPTY = "<script></script>";

	/** The file chache. */
	private static Map<String, String> fileChache = new LinkedHashMap<String, String>();

	/**
	 * Gets the res path.
	 *
	 * @param filePath
	 *            the file path
	 * @return the res path
	 */
	private static String getResPath(String filePath) {
		return PATH_PREFIX + filePath;
	}

	/**
	 * Read file content.
	 *
	 * @param filePath
	 *            the file path
	 * @return the string
	 */
	private static String readFileContent(String filePath) {
		String fileString = FileUtil.readFile(RES_PREFIX + filePath);
		if (fileString == null) {
			return SCRIPT_PREFIX + getResPath(filePath) + SCRIPT_SUFFIX;
		}
		return fileString;
	}

	/**
	 * Gets the file content.
	 *
	 * @param filePath
	 *            the file path
	 * @return the file content
	 */
	private static String getFileContent(String filePath) {
		String fileString = fileChache.get(filePath);
		if (fileString == null || false) {
			fileString = readFileContent(filePath);
			/*
			JSFileSet jSFileSet = JSFiles.get(filePath);
			if (jSFileSet == null) {
				fileString = readFileContent(filePath);
			} else {
				StringBuilder sb = new StringBuilder(EMPTY);
				for (String fileName : jSFileSet.getFiles()) {
					sb.append(readFileContent(fileName));
				}
				fileString = sb.toString();
			}*/
			fileChache.put(filePath, fileString);
		}
		return fileString;
	}

	/** The file name. */
	private String fileName;

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName
	 *            the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}

	/** The add script. */
	private TagElement addScript;

	/**
	 * Gets the adds the script.
	 *
	 * @return the adds the script
	 */
	public TagElement getAddScript() {
		return this.addScript;
	}

	/**
	 * Sets the adds the script.
	 *
	 * @param addScript
	 *            the new adds the script
	 */
	public void setAddScript(TagElement addScript) {
		this.addScript = addScript;
	}

	/**
	 * Gets the script tag.
	 *
	 * @return the script tag
	 */
	public TagElement getScriptTag() {
		if (this.getAddScript() == null) {
			TagElement script = new TagElement(ElementType.SCRIPT);
			this.setAddScript(script);
			String fileString = getFileContent(this.getFileName());
			// This check is not really required as 'getFileContent' never
			// returns null value
			// if (fileString == null) {
			// script.attr(SCRIPT_SRC, getResPath(this.getFileName()));
			// }
			script.html(fileString);
		}
		return getAddScript();
	}

	/**
	 * After the body evaluation: do not reevaluate and continue with the page.
	 * By default nothing is done with the bodyContent data (if any).
	 *
	 * @return SKIP_BODY
	 * @throws JspException
	 *             if an error occurred while processing this tag
	 * @see #doInitBody
	 * @see BodyTag#doAfterBody
	 */
	@Override
	public int doAfterBody() throws JspException {
		BodyContent bc = getBodyContent();
		String body = bc.getString();
		TagElement script = getScriptTag();
		script.append(body);
		return (SKIP_BODY);
	}

	/**
	 * Default processing of the end tag returning EVAL_PAGE.
	 *
	 * @return EVAL_PAGE
	 * @see Tag#doEndTag
	 */
	@Override
	public int doEndTag() {
		JspWriter out = this.pageContext.getOut();
		TagElement script = getScriptTag();
		try {
			out.print(script.toString().replaceAll(CLEAN_SCRIPT_CLASS, EMPTY).replaceAll(CLEAN_SCRIPT_EMPTY, EMPTY));
		} catch (IOException e) {
			LOG.debug(EXCEPTION + e.getMessage(), e);
		} finally {
			this.setAddScript(null);
		}
		return EVAL_PAGE;
	}
}
