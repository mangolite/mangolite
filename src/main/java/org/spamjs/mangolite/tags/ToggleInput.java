package org.spamjs.mangolite.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import org.spamjs.utils.JsonUtil;

// TODO: Auto-generated Javadoc
/**
 * Currency Toggle, supports inverse values.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 */
public class ToggleInput extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "toggleinput";

	/** The has options. */
	private boolean hasOptions = true;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}

	/** The data value. */
	private String dataValue;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#getDataValue()
	 */
	public String getDataValue() {
		return this.dataValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.AbstractTag#setDataValue(java.lang.String)
	 */
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	/** The show toggle flag. */
	private Boolean showToggleFlag = Boolean.TRUE;

	/** The show toggle. */
	private String showToggle;

	/**
	 * Gets the show toggle.
	 *
	 * @return the show toggle
	 */
	public String getShowToggle() {
		return this.showToggle;
	}

	/**
	 * Sets the show toggle.
	 *
	 * @param showToggle
	 *            the new show toggle
	 */
	public void setShowToggle(String showToggle) {
		if (showToggle != null && showToggle.equalsIgnoreCase("false")) {
			this.showToggleFlag = Boolean.FALSE;
		} else {
			this.showToggleFlag = Boolean.TRUE;
		}
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
			parentDiv.addClass(NO_ENTER_NEXT + " droption input1 " + AUTOSEARCH);
			parentDiv.setHasOptions(this.hasOptions);
			JsonUtil.fromJson(body, Map.class);

			// json
			Map<String, Object> options = JsonUtil.getLinkedMapFromJsonString(body);
			String displayValue = EMPTY;
			String numer;
			String asset;
			if (this.getDataValue() != null) {
				try {
					asset = this.dataValue.substring(CUR_PAIR_START, CUR_PAIR_MID);
					displayValue = asset;
					try {
						numer = this.dataValue.substring(CUR_PAIR_MID, CUR_PAIR_END);
						displayValue = displayValue + " / " + numer;
					} catch (Exception e) {
						LOG.error(EXCEPTION + e.getMessage(), e);
					}
				} catch (Exception e) {
					LOG.error(EXCEPTION + e.getMessage(), e);
				}
			} else {
				this.dataValue = EMPTY;
			}

			parentDiv.attr(DEF_VAL1, this.getDefaultVal());
			parentDiv.displayAttr(TAB1, this.getTabIndex());
			parentDiv.displayAttr(VALUE, displayValue);
			parentDiv.setValue(this.dataValue);
			parentDiv.setDisplayValue(displayValue);
			parentDiv.appendInputClass(INPUT);

			String listVal = EMPTY;
			String ccyPair = EMPTY;
			// Sonar critical fix:Performance - Inefficient use of
			// keySet
			// iterator instead of entrySet iterator
			for (Map.Entry<String, Object> mapEntry : options.entrySet()) {
				String key = mapEntry.getKey();
				ccyPair = (String) mapEntry.getValue();
				asset = ccyPair.split("/")[0];
				numer = ccyPair.split("/")[1];
				listVal = asset + " / " + numer;
				parentDiv.addOption(key, listVal, listVal.toUpperCase(), listVal);
			}
			String readonlyForWrapper = "";
			if (this.getReadOnly()) {
				readonlyForWrapper = READ_ONLY_CLASS;
			}

			out.print("<div class='tagwrapper "
					+ this.getParentDivClass()
					+ " "
					+ readonlyForWrapper
					+ "' >"
					+ parentDiv.toString()
					+ (this.showToggleFlag.booleanValue() ? DIV_RIGHT_POS + "tgl_btn btntgl xtoggle' " + TAB1 + "="
							+ this.getTabIndex() + ".1 >" + DIV_E : "") + "</div>");
		} catch (Exception e) {
			LOG.error(EXCEPTION + e.getMessage(), e);
		}
		return SKIP_BODY;
	}
}