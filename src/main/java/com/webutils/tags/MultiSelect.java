package com.webutils.tags;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import com.utils.JsonUtil;
import com.webutils.tags.TagElement.ElementType;

/**
 * This class is used for creating a multiselectable dropdown.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 */
public class MultiSelect extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1024032067345124792L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "multiselect";

	/** The Constant HAS_DISP_DIV. */
	private static final boolean HAS_DISP_DIV = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}

	/** The all - attribute which tells whether selecting all is allowed or not. */
	private String all;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public String getAll() {
		return this.all;
	}

	/**
	 * Sets the all.
	 *
	 * @param all
	 *            the new all
	 */
	public void setAll(String all) {
		this.all = all;
	}

	/**
	 * The none - attribute which tells whether selecting none is allowed or
	 * not.
	 */
	private String none;

	/**
	 * Gets the none.
	 *
	 * @return the none
	 */
	public String getNone() {
		return this.none;
	}

	/**
	 * Sets the none.
	 *
	 * @param none
	 *            the new none
	 */
	public void setNone(String none) {
		this.none = none;
	}

	/** The data values - values to be selected by deafult. */
	private String dataValues;

	/**
	 * Gets the data values.
	 *
	 * @return the data values
	 */
	public String getDataValues() {
		return this.dataValues;
	}

	/**
	 * Sets the data values.
	 *
	 * @param dataValues
	 *            the new data values
	 */
	public void setDataValues(String dataValues) {
		this.dataValues = dataValues;
	}

	/**
	 * The default text - default text to be displayed when the widget is
	 * rendered.
	 */
	private String defaultText;

	/**
	 * Gets the default text.
	 *
	 * @return the default text
	 */
	public String getDefaultText() {
		return this.defaultText;
	}

	/**
	 * Sets the default text.
	 *
	 * @param defaultText
	 *            the new default text
	 */
	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
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
			body = body.replace(S_QUOTE, "\"");
			Map<String, Object> options = JsonUtil.getLinkedMapFromJsonString(body);

			// default selection
			this.dataValues = this.getDataValues();
			this.dataValues = this.dataValues != null ? this.dataValues.trim() : EMPTY;
			String arrDefaults[] = this.dataValues.split(COMMA);
			// Fix for Sonar: Critical: Dodgy - Dead store to local
			// variable
			List<String> lstDefaults = Arrays.asList(arrDefaults);
			this.defaultText = this.getDefaultText() != null ? this.getDefaultText().trim() : EMPTY;

			ParentDivElement parentDiv = getParentDiv();
			parentDiv.setHasDisplayDiv(HAS_DISP_DIV);
			if (this.getAutosearch() != null) {
				parentDiv.addClass(AUTOSEARCH);
			}
			TagElement innerHTML = new TagElement(ElementType.DIV);
			innerHTML.addClass(INPUT);
			innerHTML.html(this.defaultText);
			parentDiv.append(innerHTML);
			parentDiv.append("<a tabindex='-1' class='downArrow closed'></a>");
			parentDiv.appendInputClass(INPUT);

			innerHTML = new TagElement(ElementType.UL);
			innerHTML.addClass("multiselect_menu dn");
			innerHTML.attr(TAB1, "-1");

			String checked = EMPTY;
			checked = all(options, lstDefaults, checked, parentDiv, innerHTML);
			none(parentDiv, innerHTML);
			populateOptions(options, lstDefaults, checked, innerHTML);
			parentDiv.append(innerHTML);
			out.print(parentDiv.toString());

		} catch (IOException ioe) {
			LOG.error(EXCEPTION + ioe.getMessage(), ioe);
		}
		return (SKIP_BODY);
	}

	/**
	 * Changes according to the value of "all" field.
	 *
	 * @param options
	 *            the options
	 * @param lstDefaults
	 *            the lst defaults
	 * @param checked
	 *            the checked
	 * @param parentDiv
	 *            the parent div
	 * @param innerHTML
	 *            the inner html
	 * @return the string
	 */
	public String all(Map<String, Object> options, List<String> lstDefaults, String checked,
			ParentDivElement parentDiv, TagElement innerHTML) {
		String newChecked = checked;
		this.all = this.getAll();
		this.all = this.all != null ? this.all.trim() : EMPTY;
		if (!(this.all.equals(EMPTY))) {
			parentDiv.addClass(ALL);
			if (this.all.equals(STRING_TRUE)) {
				newChecked = STRING_CHECKED;
			}
		} else {
			if (lstDefaults.containsAll(options.keySet())) {
				newChecked = STRING_CHECKED;
			}
		}
		TagElement li = new TagElement(ElementType.LI);
		li.addClass(OPTION);
		li.attr(DATA_VALUE1, ALL);
		li.attr(DATA_DISPLAY1, "All");
		li.html(CHECKBOX + newChecked + TAG_COMPLETE + LABEL_S + "All" + LABEL_E);
		innerHTML.append(li);
		return newChecked;
	}

	/**
	 * Changes according to the value of "none" field.
	 *
	 * @param parentDiv
	 *            the parent div
	 * @param innerHTML
	 *            the inner html
	 */
	public void none(ParentDivElement parentDiv, TagElement innerHTML) {
		this.none = this.getNone();
		this.none = this.none != null ? this.none.trim() : EMPTY;
		TagElement li = new TagElement(ElementType.LI);
		if (!(this.none.equals(EMPTY))) {
			parentDiv.addClass(NONE);
			String checkNone = EMPTY;
			if (this.none.equals(STRING_TRUE)) {
				checkNone = STRING_CHECKED;
			}
			li.addClass(OPTION);
			li.attr(DATA_VALUE1, NONE);
			li.attr(DATA_DISPLAY1, "None");
			li.html(CHECKBOX + checkNone + TAG_COMPLETE + LABEL_S + "None" + LABEL_E);
			innerHTML.append(li);
		}
	}

	/**
	 * Populates options in dropdown.
	 *
	 * @param options
	 *            the options
	 * @param lstDefaults
	 *            the lst defaults
	 * @param checked
	 *            the checked
	 * @param innerHTML
	 *            the inner html
	 */
	public void populateOptions(Map<String, Object> options, List<String> lstDefaults, String checked,
			TagElement innerHTML) {
		String newChecked = checked;
		for (Map.Entry<String, Object> mapEntry : options.entrySet()) {
			String key = mapEntry.getKey();
			Object value = mapEntry.getValue();
			if (!this.all.equalsIgnoreCase(STRING_TRUE) && !this.none.equalsIgnoreCase(STRING_TRUE)) {
				if (!lstDefaults.isEmpty() && lstDefaults.contains(key)) {
					newChecked = STRING_CHECKED;
				} else {
					newChecked = EMPTY;
				}
			}
			TagElement li = new TagElement(ElementType.LI);
			li.addClass(OPTION);
			li.attr(DATA_VALUE1, key);
			li.attr(DATA_DISPLAY1, (String) value);
			li.html(CHECKBOX + newChecked + TAG_COMPLETE + LABEL_S + (String) value + LABEL_E);
			innerHTML.append(li);
		}
	}
}