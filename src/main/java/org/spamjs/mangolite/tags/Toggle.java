package org.spamjs.mangolite.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import org.spamjs.mangolite.tags.TagElement.ElementType;

// TODO: Auto-generated Javadoc
/**
 * To Create n-Way Toggle Tag
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 */

public class Toggle extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "toggle";

	/** The Constant HAS_DISP_DIV. */
	private static final boolean HAS_DISP_DIV = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}

	/** The data display. */
	private String dataDisplay;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#setDataDisplay(java.lang.String)
	 */
	public void setDataDisplay(String displayValue) {
		this.dataDisplay = displayValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#getDataDisplay()
	 */
	public String getDataDisplay() {
		return this.dataDisplay;
	}

	/** The value. */
	private String value;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#getValue()
	 */
	public String getValue() {
		return this.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#setValue(java.lang.String)
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/** The data value. */
	private String dataValue;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#setDataValue(java.lang.String)
	 */
	public void setDataValue(String dv) {
		this.dataValue = dv;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#getDataValue()
	 */
	public String getDataValue() {
		return this.dataValue;
	}

	/** The data code. */
	private String dataCode;

	/**
	 * Gets the data code.
	 *
	 * @return the dataCode
	 */
	public String getDataCode() {
		return dataCode;
	}

	/**
	 * Sets the data code.
	 *
	 * @param dataCode
	 *            the dataCode to set
	 */
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
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
			ParentDivElement parentDiv = getParentDiv();
			parentDiv.addClass(NO_ENTER_NEXT);
			parentDiv.attr(DEF_VAL1, this.getDefaultVal());
			parentDiv.setHasDisplayDiv(HAS_DISP_DIV);
			parentDiv.appendInputClass(TOGGLE_VAL + " display input");

			String splittedDisp[] = null;
			String splittedData[] = null;
			String splittedCode[];
			if (this.getDataValue() != null) {
				splittedData = this.getDataValue().trim().split(COMMA);
			}

			if (this.getDataDisplay() != null) {
				splittedDisp = this.getDataDisplay().trim().split(COMMA);
			} else if (splittedData != null) {
				splittedDisp = splittedData;
			}

			// For i18n
			if (this.getDataCode() != null) {
				splittedCode = this.getDataCode().trim().split(COMMA);
				for (int i = 0; i < splittedData.length; i++) {
					splittedDisp[i] = this.resolveMessage(splittedCode[i], splittedDisp[i]);
				}
			}
			
			// Looping over options.
			if (splittedData != null) {
				for (int i = 0; i < splittedData.length; i++) {
					TagElement innerHTML = new TagElement(ElementType.DIV);
					// Selected value
					if (this.getValue() != null) {
						if (splittedData[i].equalsIgnoreCase(this.getValue())) {
							innerHTML.addClass(OPTION);
							parentDiv.setValue(splittedData[i]);
						} else {
							innerHTML.addClass(OPTION + SPACE + DN);
						}
					} else {
						if (i == 0) {
							innerHTML.addClass(OPTION);
							parentDiv.setValue(splittedData[i]);
						} else {
							innerHTML.addClass(OPTION + SPACE + DN);
						}
					}
					innerHTML.attr(DATA_VALUE1, splittedData[i]);
					innerHTML.attr(DATA_DISPLAY1, splittedDisp[i]);
					innerHTML.html(splittedDisp[i]);
					parentDiv.append(innerHTML);
				}
			}
			out.print(parentDiv.toString());
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
}
