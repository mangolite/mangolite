package org.spamjs.mangolite.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

/**
 * This class is used for creating "date" field.
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * 
 */
public class Date extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "date";

	/** The Constant CAL_DIV. */
	private static final String CAL_DIV = DIV_RIGHT_POS + "calendar_btn'>"
			+ DIV_E;

	/** The Constant DATE_FORMAT. */
	private static final String DATE_FORMAT = "dd MMM yyyy";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}

	/** The format. */
	private String format;

	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public String getFormat() {
		return this.format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format
	 *            the new format
	 */
	public void setFormat(String format) {
		this.format = format;
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
			parentDiv.addClass(MODE_NONE);

			this.setDataDisplay(EMPTY);

			if (this.getDataValue() != null
					&& !(this.getDataValue().equals(EMPTY))) {
				long millisDate = Long.parseLong(this.getDataValue().trim());
				String dateFormat = this.getFormat();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				if (dateFormat != null) {
					if (dateFormat.equalsIgnoreCase("us")) {
						sdf = new SimpleDateFormat("MMM dd yyyy");
					} else if (dateFormat.equalsIgnoreCase("non_us")) {
						sdf = new SimpleDateFormat("dd MMM yyyy");
					}
				}

				java.util.Date resultdate = new java.util.Date(millisDate);
				this.setDataDisplay(sdf.format(resultdate));
			}

			setDataValue((this.getDataValue() != null) ? this.getDataValue()
					.trim() : EMPTY);
			parentDiv.setValue(getDataValue());
			setDataDisplay(this.getDataDisplay().trim());
			parentDiv.setDisplayValue(getDataDisplay());
			parentDiv.displayAttr(TAB1, EMPTY + this.getTabIndex());

			parentDiv.attr(DEF_VAL + this.getDefaultVal() + S_QUOTE_SPACE);
			parentDiv.append(CAL_DIV);
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