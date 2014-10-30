package com.webutils.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import com.webutils.tags.TagElement.ElementType;

/**
 * This class is used for creating "button" field.
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * 
 */
public class Button extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5918752154328937751L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "button";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}

	/** The label. */
	private String label;

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *            the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/** The disabled. */
	private String disabled;

	/**
	 * Gets the disabled.
	 *
	 * @return the disabled
	 */
	public String getDisabled() {
		return this.disabled;
	}

	/**
	 * Sets the disabled.
	 *
	 * @param disabled
	 *            the new disabled
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/** The key - some keyboard key + this key. */
	private String key;

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/** The single key. */
	private String singleKey;

	/**
	 * Gets the single key.
	 *
	 * @return the single key
	 */
	public String getSingleKey() {
		return this.singleKey;
	}

	/**
	 * Sets the single key.
	 *
	 * @param singleKey
	 *            the new single key
	 */
	public void setSingleKey(String singleKey) {
		this.singleKey = singleKey;
	}

	/** The tag class. */
	private String tagClass;

	/**
	 * Gets the tag class.
	 *
	 * @return the tag class
	 */
	public String getTagClass() {
		return this.tagClass;
	}

	/**
	 * Sets the tag class.
	 *
	 * @param tagClass
	 *            the new tag class
	 */
	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}

	/** The appfunction. */
	private String appfunction;

	/**
	 * Gets the appfunction.
	 *
	 * @return the appfunction
	 */
	public String getAppfunction() {
		return this.appfunction;
	}

	/**
	 * Sets the appfunction.
	 *
	 * @param appfunction
	 *            the new appfunction
	 */
	public void setAppfunction(String appfunction) {
		this.appfunction = appfunction;
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
		JspWriter out = bc.getEnclosingWriter();
		// read all values
		String body = bc.getString();
		this.label = body.trim();
		this.disabled = this.getDisabled();
		this.tagClass = this.getTagClass();
		String hotkey = EMPTY;
		if (this.getKey() != null && !EMPTY.equals(this.getKey())) {
			hotkey = "hotkey";
			this.key = this.getKey();
		}
		if (this.getSingleKey() != null && !EMPTY.equals(this.getSingleKey())) {
			hotkey = "hotkey";
			this.singleKey = this.getSingleKey();
		}
		try {
			out.write(makeTagElem(hotkey).toString());
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
	 * Makes TagElement & returns it.
	 *
	 * @param hotkey
	 *            the hotkey
	 * @return the tag element
	 */
	public TagElement makeTagElem(String hotkey) {
		TagElement tagElem = new TagElement(ElementType.INPUT);
		tagElem.addClass(TAG + SPACE + BUTTON1);
		tagElem.addClass(this.getParentDivClass());
		tagElem.addClass(this.getFieldType());
		tagElem.attr(FIELD1, this.getFieldType());
		if (this.disabled != null && !EMPTY.equals(this.disabled) && this.disabled.equalsIgnoreCase("true")) {
			tagElem.addClass("disabled");
		}
		if (this.tagClass != null && !EMPTY.equals(this.tagClass)) {
			tagElem.addClass(this.tagClass);
		}
		if (this.appfunction != null && !EMPTY.equals(this.appfunction)) {
			tagElem.addClass("appfunction");
			tagElem.attr("appfunction", this.appfunction);
		}
		tagElem.addClass(hotkey);
		tagElem.attr(TYPE, BUTTON1);
		tagElem.attr(VALUE, this.label);
		tagElem.attr(KEY, this.key);
		tagElem.attr(SINGLE_KEY, this.singleKey);
		tagElem.attr(TAB1, this.getTabIndex());

		if (this.getNavIndex() != null) {
			String inav[] = this.getNavIndex().trim().split(COMMA);
			tagElem.addClass(R + inav[0] + C + inav[1]);
			tagElem.attr(I_ROW1, inav[0]);
			tagElem.attr(I_COL1, inav[1]);
		}
		return tagElem;
	}
}
