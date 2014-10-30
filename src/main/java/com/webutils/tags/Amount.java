package com.webutils.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import com.webutils.tags.TagElement.ElementType;

/**
 * This class is used for creating "amount" field.
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * 
 * @version 1.3
 * 
 */
public class Amount extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "amount";

	/** The Constant BULK. */
	private static final String BULK = "bulk";

	/** The Constant DISABLE_TOGGLE. */
	private static final String DISABLE_TOGGLE = "disableToggle";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}

	/** The value - default value. */
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

	/** The currencies. */
	private String currencies;

	/**
	 * Gets the currencies.
	 *
	 * @return the currencies
	 */
	public String getCurrencies() {
		return this.currencies;
	}

	/**
	 * Sets the currencies.
	 *
	 * @param currencies
	 *            the new currencies
	 */
	public void setCurrencies(String currencies) {
		this.currencies = currencies;
	}

	/** The default cur. */
	private String defaultCur;

	/**
	 * Gets the default cur.
	 *
	 * @return the default cur
	 */
	public String getDefaultCur() {
		return this.defaultCur;
	}

	/**
	 * Sets the default cur.
	 *
	 * @param defaultCur
	 *            the new default cur
	 */
	public void setDefaultCur(String defaultCur) {
		this.defaultCur = defaultCur;
	}

	/** The bulk. */
	private String bulk;

	/**
	 * Gets the bulk.
	 *
	 * @return the bulk
	 */
	public String getBulk() {
		return this.bulk;
	}

	/**
	 * Sets the bulk.
	 *
	 * @param bulk
	 *            the new bulk
	 */
	public void setBulk(String bulk) {
		this.bulk = bulk;
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
			this.value = ((this.getValue() != null) ? this.getValue().trim() : EMPTY);
			String bulkClass = EMPTY;
			if (this.getBulk() == null) {
				bulkClass = BULK;
			} else {
				bulkClass = BULK + this.getBulk();
			}
			String tempCur[] = { EMPTY, EMPTY };
			if (this.getCurrencies() != null) {
				tempCur = this.getCurrencies().trim().split(COMMA);
			}
			String firstCur = tempCur[0];
			String secondCur = tempCur[1];
			String defaultCurStrTemp = "";
			if (this.getDefaultCur() != null) {
				defaultCurStrTemp = this.getDefaultCur();
				if (secondCur.equalsIgnoreCase(defaultCurStrTemp)) {
					secondCur = tempCur[0];
					firstCur = tempCur[1];
				}
			}
			ParentDivElement parentDiv = getParentDivElement();
			TagElement innerDiv = getInnerDiv(bulkClass);
			innerDiv.html(getFirstCurSpan(firstCur));
			innerDiv.append(getSecondCurSpan(secondCur));

			if (firstCur.equalsIgnoreCase(secondCur)) {
				innerDiv.addClass(DISABLE_TOGGLE);
			}
			innerDiv.append(getHiddenInput(firstCur));
			parentDiv.append(innerDiv);
			out.print(parentDiv.toString());
		} catch (IOException e) {
			LOG.error(EXCEPTION + e.getMessage(), e);
		}
		return SKIP_BODY;
	}

	/**
	 * Gets hidden input.
	 *
	 * @param firstCur
	 *            the first cur
	 * @return Hidden input tag
	 */
	public TagElement getHiddenInput(String firstCur) {
		TagElement innerHTML = new TagElement(ElementType.INPUT);
		innerHTML.addClass(UNDERSCORE_CUR1 + SPACE + TOGGLE_VAL);
		innerHTML.attr(TYPE, HIDDEN1);
		innerHTML.attr(VALUE, firstCur);
		return innerHTML;
	}

	/**
	 * Gets span element for 2nd currency.
	 *
	 * @param secondCur
	 *            the second cur
	 * @return Span tag for 2nd currency
	 */
	public TagElement getSecondCurSpan(String secondCur) {
		TagElement innerHTML = new TagElement(ElementType.SPAN);
		innerHTML.addClass(OPTION + SPACE + DN);
		innerHTML.attr(DATA_VALUE1, secondCur);
		innerHTML.html(secondCur);
		return innerHTML;
	}

	/**
	 * Gets span element for 1st currency.
	 *
	 * @param firstCur
	 *            the first cur
	 * @return Span tag for 1st currency
	 */
	public TagElement getFirstCurSpan(String firstCur) {
		TagElement innerHTML = new TagElement(ElementType.SPAN);
		innerHTML.addClass(OPTION);
		innerHTML.attr(DATA_VALUE1, firstCur);
		innerHTML.html(firstCur);
		return innerHTML;
	}

	/**
	 * Gets inner div.
	 *
	 * @param bulkClass
	 *            the bulk class
	 * @return Inner div
	 */
	public TagElement getInnerDiv(String bulkClass) {
		TagElement innerDiv = new TagElement(ElementType.DIV);
		innerDiv.addClass(RIGHT_POS + SPACE + AMT_TOGGLE + SPACE + TOGGLE + SPACE + bulkClass + SPACE + TAG + SPACE
				+ NO_ENTER_NEXT + SPACE + this.getFieldType() + UNDERSCORE_CUR1);
		innerDiv.attr(FIELD1, this.getFieldType() + UNDERSCORE_CUR1);
		innerDiv.attr(TAB1, this.getTabIndex() + ".2");
		return innerDiv;
	}

	/**
	 * Gets parent div.
	 * 
	 * @return Parent div
	 */
	public ParentDivElement getParentDivElement() {
		ParentDivElement parentDiv = getParentDiv();
		parentDiv.addClass(NO_ENTER_NEXT);
		parentDiv.attr(DEF_VAL1, this.getDefaultVal());
		parentDiv.displayAttr(TAB1, this.getTabIndex() + ".1");
		parentDiv.appendInputClass(UNDERSCORE_CUR);
		parentDiv.setDisplayValue(this.value);
		parentDiv.setValue(this.value);
		return parentDiv;
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
