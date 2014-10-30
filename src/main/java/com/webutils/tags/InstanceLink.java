package com.webutils.tags;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.Tag;

import com.utils.Log;
import com.webutils.tags.TagElement.ElementType;

/**
 * Tag for Instance link for AppInstance.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 */
public class InstanceLink extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "instance_link";

	/** The LOG object for logging all log events to a log file. */
	private static final Log LOG = new Log();

	/** The sys. */
	private String sys;

	/**
	 * Gets the sys.
	 *
	 * @return the sys
	 */
	public String getSys() {
		return this.sys;
	}

	/**
	 * Sets the sys.
	 *
	 * @param sys
	 *            the new sys
	 */
	public void setSys(String sys) {
		this.sys = sys;
	}

	/**
	 * Gets the app.
	 *
	 * @return the app
	 */
	public String getApp() {
		return this.app;
	}

	/**
	 * Sets the app.
	 *
	 * @param app
	 *            the new app
	 */
	public void setApp(String app) {
		this.app = app;
	}

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public String getPage() {
		return this.page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page
	 *            the new page
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/** The app. */
	private String app;

	/** The page. */
	private String page;

	/** The app key. */
	private String appKey;

	/*
	 * <li class="usersetting tag instance_link dn" sys="settings"
	 * app="asettings" page="rfqsetup"><a>RFQ Setup</a></li> (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */

	/**
	 * Gets the app key.
	 *
	 * @return the app key
	 */
	public String getAppKey() {
		return this.appKey;
	}

	/**
	 * Sets the app key.
	 *
	 * @param appKey
	 *            the new app key
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * Default processing of the end tag returning EVAL_PAGE.
	 *
	 * @return EVAL_PAGE
	 * @see Tag#doEndTag
	 */
	@Override
	public int doEndTag() {
		BodyContent bc = getBodyContent();
		String body = bc.getString();
		JspWriter out = this.pageContext.getOut();
		boolean hasAccess = false;
		if (this.appKey != null) {
			//hasAccess = AppFactory.canAccess(this.appKey);
		} else {
			//hasAccess = AppFactory.canAccess(this.sys, this.app, this.page);
		}
		if (hasAccess) {
			TagElement li = new TagElement(ElementType.LI);
			li.addClass(this.getTagType() + SPACE + TAG);
			li.attr("sys", this.sys);
			li.attr("app", this.app);
			li.attr("page", this.page);
			li.html(body);
			try {
				out.print(li.toString());
			} catch (IOException e) {
				LOG.error("---cannot write for args---" + e.toString());
			}
		}
		return EVAL_PAGE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}
}