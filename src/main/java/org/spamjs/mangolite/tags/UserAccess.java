package org.spamjs.mangolite.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;

// TODO: Auto-generated Javadoc
/**
 * tag to check if user can Access specific module, in JSP.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 2.2
 * @since Aug 30, 2014
 */
public class UserAccess extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "access";

	/** The access key. */
	private String accessKey;

	/**
	 * Gets the access key.
	 *
	 * @return the access key
	 */
	public String getAccessKey() {
		return this.accessKey;
	}

	/**
	 * Sets the access key.
	 *
	 * @param accessKey
	 *            the new access key
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	/**
	 * Default processing of the start tag returning EVAL_BODY_BUFFERED.
	 *
	 * @return EVAL_BODY_BUFFERED
	 * @throws JspException
	 *             if an error occurred while processing this tag
	 * @see BodyTag#doStartTag
	 */
	@Override
	public int doStartTag() throws JspException {
		// TODO: accessTo logic implementation
		String accessTo = "fx";
		if (this.accessKey.equalsIgnoreCase(accessTo)) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}
}