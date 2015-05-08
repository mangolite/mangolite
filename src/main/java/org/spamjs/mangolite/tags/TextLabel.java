package org.spamjs.mangolite.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

/**
 * This class is used for creating "textlabel" field.
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 * 
 */
public class TextLabel extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "textLabel";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}

	/** The round. */
	private int round;

	/**
	 * Gets the round.
	 *
	 * @return the round
	 */
	public int getRound() {
		return this.round;
	}

	/**
	 * Sets the round.
	 *
	 * @param round
	 *            the new round
	 */
	public void setRound(int round) {
		this.round = round;
	}

	/** The percentage. */
	private Boolean percentage;

	/**
	 * Gets the percentage.
	 *
	 * @return the percentage
	 */
	public Boolean getPercentage() {
		return this.percentage;
	}

	/**
	 * Sets the percentage.
	 *
	 * @param percentage
	 *            the new percentage
	 */
	public void setPercentage(Boolean percentage) {
		this.percentage = percentage;
	}

	/** The num val. */
	private double numVal;

	/**
	 * Gets the num val.
	 *
	 * @return the num val
	 */
	public double getNumVal() {
		return this.numVal;
	}

	/**
	 * Sets the num val.
	 *
	 * @param numVal
	 *            the new num val
	 */
	public void setNumVal(double numVal) {
		this.numVal = numVal;
	}

	/** The str val. */
	private String strVal;

	/**
	 * Gets the str val.
	 *
	 * @return the str val
	 */
	public String getStrVal() {
		return this.strVal;
	}

	/**
	 * Sets the str val.
	 *
	 * @param strVal
	 *            the new str val
	 */
	public void setStrVal(String strVal) {
		this.strVal = strVal;
	}

	/** The nav index. */
	private String navIndex;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#getNavIndex()
	 */
	public String getNavIndex() {
		return this.navIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#setNavIndex(java.lang.String)
	 */
	public void setNavIndex(String navIndex) {
		this.navIndex = navIndex;
	}

	/** The tabindex. */
	private int tabindex;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#getTabIndex()
	 */
	public int getTabIndex() {
		return this.tabindex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#setTabIndex(int)
	 */
	public void setTabIndex(int tabindex) {
		this.tabindex = tabindex;
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
		String dispVal = EMPTY;

		String navString = EMPTY, classString = CLASS + TAG + TXT_LBL;
		if (this.getNavIndex() != null) {
			String inav[] = this.getNavIndex().trim().split(",");
			classString = classString + R + inav[0] + C + inav[1];
			navString = I_ROW + inav[0] + S_QUOTE + I_COL + inav[1] + S_QUOTE;
		}
		String tabindexString = TAB + this.getTabIndex() + S_QUOTE;

		this.strVal = this.getStrVal();
		if (this.strVal != null) {
			dispVal = this.strVal.trim();
		} else if (this.getNumVal() > 0) {
			String suffix = EMPTY;
			if (this.getPercentage() != null) {
				this.numVal = TagsUtil.percent(this.numVal);
				suffix = PERCENT;
			}
			dispVal = Double.toString(this.numVal);
			if (this.getRound() > 0) {
				double rValue = TagsUtil.round(this.numVal, this.getRound());
				dispVal = Double.toString(rValue);
			}
			dispVal += suffix;
		}
		classString += S_QUOTE;
		JspWriter out = this.pageContext.getOut();
		try {
			out.print(DIV_S + classString + navString + tabindexString + TAG_CLOSE + dispVal + DIV_E);
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