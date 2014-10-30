package com.webutils.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import com.webutils.tags.TagElement.ElementType;

/**
 * The Class InstanceMenu.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 */
public class InstanceMenu extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "instance_menu";

	/** The title. */
	private String title;

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	String getTitle() {
		return this.title;
	}

	/** The short name. */
	private String shortName;

	/**
	 * Gets the short name.
	 *
	 * @return the short name
	 */
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * Sets the short name.
	 *
	 * @param shortName
	 *            the new short name
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/** The sys. */
	private String sys;

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
	 * Gets the sys.
	 *
	 * @return the sys
	 */
	String getSys() {
		return this.sys;
	}

	/** The app. */
	private String app;

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
	 * Gets the app.
	 *
	 * @return the app
	 */
	String getApp() {
		return this.app;
	}

	/** The parent div class. */
	private String parentDivClass;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#getParentDivClass()
	 */
	public String getParentDivClass() {
		return this.parentDivClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dfferentia.app.tags.AbstractTag#setParentDivClass(java.lang.String)
	 */
	public void setParentDivClass(String parentDivClass) {
		this.parentDivClass = parentDivClass;
	}

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
		try {
			JspWriter out = this.pageContext.getOut();
			this.sys = this.getSys() != null ? this.getSys().trim() : "";
			this.app = this.getApp() != null ? this.getApp().trim() : "";
			this.title = this.getTitle() != null ? this.getTitle().trim() : "";
			this.accessKey = this.getAccessKey() != null ? this.getAccessKey().trim() : "";

			TagElement tagElem = new TagElement(ElementType.LI);
			tagElem.addClass(this.getTagType() + " sub_nav " + TAG);
			tagElem.attr(SYS, this.sys);
			tagElem.attr(APP, this.app);
			tagElem.attr(ACCESSKEY, this.accessKey);
			tagElem.html("<a class='title'>" + this.title + "</a><a class='_title'>" + this.getShortName() + "</a>");
			out.print(tagElem.toString());
		} catch (IOException e) {
			LOG.error(EXCEPTION + e.getMessage(), e);
		}
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
