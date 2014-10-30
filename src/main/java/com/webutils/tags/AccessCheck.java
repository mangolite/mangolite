package com.webutils.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import com.utils.Log;
import com.webutils.tags.TagElement.ElementType;

/**
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 2.3
 * @since Aug 30, 2012
 * 
 */
public class AccessCheck extends AbstractTag {

	private static final long serialVersionUID = 1L;
	private static final String TAG_TYPE = "access_key";

	/** The LOG object for logging all log events to a log file */
	private static final Log LOG = new Log();

	private String accessKey;

	/*
	 * <li class="usersetting tag instance_link dn" sys="settings"
	 * app="asettings" page="rfqsetup"><a>RFQ Setup</a></li>
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */

	/**
	 * @return the accessKey
	 */
	public String getAccessKey() {
		return this.accessKey;
	}

	/**
	 * @param accessKey
	 *            the accessKey to set
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	/**
	 * Default processing of the start tag returning
	 * EVAL_BODY_BUFFERED.
	 *
	 * @return EVAL_BODY_BUFFERED
	 * @throws JspException
	 *             if an error occurred while processing this tag
	 * @see BodyTag#doStartTag
	 */
	@Override
	public int doStartTag() {
		BodyContent bc = getBodyContent();
		String body = bc.getString();
		JspWriter out = this.pageContext.getOut();
		// Sonar major fix: String Literal Equality - string literals
		// cannot be
		// compared with == or != but with .equals()
		if (this.accessKey != null && this.accessKey.equals("fx")) {
			TagElement div = new TagElement(ElementType.DIV);
			div.html(body);
			try {
				out.print(div.toString());
			} catch (IOException e) {
				LOG.error("---cannot write for args---" + e.toString());
			}
		}
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}
}