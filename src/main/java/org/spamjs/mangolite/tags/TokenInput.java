package org.spamjs.mangolite.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import org.spamjs.mangolite.tags.TagElement.ElementType;
import org.spamjs.utils.Log;

// TODO: Auto-generated Javadoc
/**
 * This class is used for creating "tokeninput" field. Used for calendar widget.
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 * 
 */
public class TokenInput extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The LOG object for logging all log events to a log file. */
	private static final Log LOG = new Log();

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "tokeninput";

	/** The has display div. */
	private boolean hasDisplayDiv = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}

	/** The token url. */
	private String tokenURL;

	/**
	 * Gets the token url.
	 *
	 * @return the token url
	 */
	public String getTokenURL() {
		return this.tokenURL;
	}

	/**
	 * Sets the token url.
	 *
	 * @param tokenURL
	 *            the new token url
	 */
	public void setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
	}

	/** The token limit. */
	private int tokenLimit;

	/**
	 * Gets the token limit.
	 *
	 * @return the token limit
	 */
	public int getTokenLimit() {
		return this.tokenLimit;
	}

	/**
	 * Sets the token limit.
	 *
	 * @param tokenLimit
	 *            the new token limit
	 */
	public void setTokenLimit(int tokenLimit) {
		this.tokenLimit = tokenLimit;
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
		try {
			BodyContent bc = getBodyContent();
			String body = bc.getString();
			JspWriter out = bc.getEnclosingWriter();

			ParentDivElement parentDiv = getParentDiv();
			parentDiv.setHasDisplayDiv(this.hasDisplayDiv);
			parentDiv.attr(DEF_VAL1, this.getDefaultVal());
			parentDiv.attr(URL, this.getTokenURL());
			parentDiv.attr("tokenLimit", this.getTokenLimit());

			TagElement innerHTML = new TagElement(ElementType.INPUT);
			innerHTML.addClass("input geninput pass");
			innerHTML.attr(TAB1, this.getTabIndex());
			parentDiv.html(innerHTML);

			innerHTML = new TagElement(ElementType.DIV);
			innerHTML.addClass("dn values");
			innerHTML.html(body);
			parentDiv.append(innerHTML);
			out.print(parentDiv.toString());
		} catch (Exception e) {
			LOG.error(EXCEPTION + e.getMessage(), e);
		}
		return SKIP_BODY;
	}
}