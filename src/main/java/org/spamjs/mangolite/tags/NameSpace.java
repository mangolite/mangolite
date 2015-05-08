package org.spamjs.mangolite.tags;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import org.spamjs.mangolite.tags.TagElement.ElementType;

/**
 * Tag to include namespace definition insdie JSP pages.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Jun 24, 2014
 */

public class NameSpace extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "jscript";

	/** The Constant NAMESPACEC_PREFIX. */
	public static final String NAMESPACEC_PREFIX = "select_namespace('bigpipe.apps', function(namespace) { ";

	/** The Constant NAMESPACEC_SUFFIX. */
	public static final String NAMESPACEC_SUFFIX = " });";

	/** The Constant ADDFILES_PREFIX. */
	private static final String ADDFILES_PREFIX = "namespace.addFiles('";

	/** The Constant ADDFILES_MID. */
	private static final String ADDFILES_MID = "',";

	/** The Constant ADDFILES_SUFFIX. */
	private static final String ADDFILES_SUFFIX = ");";

	/** The Constant NAMESPACEC_COMMON_PREFFIX. */
	private static final String NAMESPACEC_COMMON_PREFFIX = "namespace['";

	/** The Constant NAMESPACEC_COMMON_MID. */
	private static final String NAMESPACEC_COMMON_MID = "'] = ";

	/** The Constant NAMESPACEC_COMMON_SUFFIX. */
	private static final String NAMESPACEC_COMMON_SUFFIX = ";";

	/** The file chache. */
	private static Map<String, String> fileChache = new LinkedHashMap<String, String>();

	/**
	 * Gets the file list.
	 *
	 * @param fileSet
	 *            the file set
	 * @return the file list
	 */
	public static String getFileList(String fileSet) {
		return ADDFILES_PREFIX + fileSet + ADDFILES_MID + "JSFiles.getFileList(fileSet)" + ADDFILES_SUFFIX;
	}

	/**
	 * Gets the file content.
	 *
	 * @param sys
	 *            the sys
	 * @param reset
	 *            the reset
	 * @return the file content
	 */
	public static String getFileContent(String sys, boolean reset) {
		String fileString = fileChache.get(sys);
		if (fileString == null || reset) {
			//SysDescriptor sysDesc = AppStructure.get(sys);
			StringBuilder sb = new StringBuilder();
			sb.append(NAMESPACEC_PREFIX);
			/*
			if (sysDesc != null) {
				for (Entry<String, String> entry : sysDesc.getIncludeMap().entrySet()) {
					sb.append(getFileList(entry.getKey()));
				}
				sb.append(NAMESPACEC_COMMON_PREFFIX);
				sb.append(sys);
				sb.append(NAMESPACEC_COMMON_MID);
				sb.append(JsonUtil.toJson(sysDesc.toMap()));
				sb.append(NAMESPACEC_COMMON_SUFFIX);
			}*/
			sb.append(NAMESPACEC_SUFFIX);
			fileString = sb.toString();
			fileChache.put(sys, fileString);
		}
		return fileString;
	}

	/** The name. */
	private String name;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
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
			String fileString = getFileContent(this.getName(), false);
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
			out.print(script.toString());
		} catch (IOException e) {
			LOG.debug(EXCEPTION + e.getMessage(), e);
		} finally {
			this.setAddScript(null);
		}
		return EVAL_PAGE;
	}
}
