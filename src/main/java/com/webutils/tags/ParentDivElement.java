package com.webutils.tags;

/**
 * Class representing HTML DOM element for parent div of Widget Tag,.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Jan 28, 2014
 */
public class ParentDivElement extends TagElement {

	/** The Constant TAG_COMPLETE. */
	public static final String TAG_COMPLETE = "/>";

	/** The Constant HIDDEN. */
	public static final String HIDDEN = "<input type='hidden' value='";

	/** The Constant DISP_IN. */
	public static final String DISP_IN = "<input type='text' class='input display dn' value='";

	/** The Constant DISP_DIV. */
	public static final String DISP_DIV = "<div class='display input' ";

	/** The Constant DIV_E. */
	public static final String DIV_E = "</div>";

	/** The Constant TAG_CLOSE. */
	public static final String TAG_CLOSE = ">";

	/** The Constant TAG. */
	public static final String TAG = "tag";

	/** The Constant TABINDEX. */
	public static final String TABINDEX = " tabindex";

	/** The Constant READ_ONLY. */
	public static final String READ_ONLY = "readonly";

	/** The Constant READ_ONLY_CLASS. */
	public static final String READ_ONLY_CLASS = "readOnly yes";

	/** The Constant I_ROW. */
	public static final String I_ROW = " iRow";

	/** The Constant I_COL. */
	public static final String I_COL = " iCol";

	/** The Constant TAG_TYPE. */
	public static final String TAG_TYPE = "tagType";

	/** The Constant FIELD_TYPE. */
	public static final String FIELD_TYPE = "fieldtype";

	/** The Constant DEFAULT_VALUE. */
	public static final String DEFAULT_VALUE = "defaultVal";

	/** The Constant R. */
	public static final String R = "r";

	/** The Constant C. */
	public static final String C = "c";

	/** The Constant S_QUOTE_SPACE. */
	public static final String S_QUOTE_SPACE = "' ";

	/** The Constant OLD_VAL. */
	public static final String OLD_VAL = "oldval='";

	/** The Constant LI_E. */
	public static final String LI_E = "</li>";

	/** The Constant LI_S. */
	public static final String LI_S = "<li class='option' data-value='";

	/** The Constant UL_S. */
	public static final String UL_S = "<ul class='dropdown_menu dn'>";

	/** The Constant UL_E. */
	public static final String UL_E = "</ul>";

	/** The Constant DATA_DISPLAY. */
	public static final String DATA_DISPLAY = "data-display='";

	/** The Constant SHORTCUT. */
	public static final String SHORTCUT = "shortcut='";

	/** The display value. */
	private String displayValue;

	/** The display attrs. */
	private String displayAttrs;

	/** The input class. */
	private String inputClass = "value";

	/** The has display div. */
	private boolean hasDisplayDiv = true;

	/** The data value. */
	private String dataValue;

	/** The tag type. */
	private String tagType;

	/** The options. */
	private String options = EMPTY;

	/** The has options. */
	private boolean hasOptions = false;

	/** The placeholder. */
	private String placeholder = "";

	/**
	 * Sets the checks for options. Setting flag whether the widget is having
	 * options or not.
	 *
	 * @param hasOptions
	 *            the new checks for options
	 */
	public void setHasOptions(boolean hasOptions) {
		this.hasOptions = hasOptions;
	}

	/**
	 * Adding 'li' tag.
	 *
	 * @param dataValue1
	 *            the data value1
	 * @param dataDisplay
	 *            the data display
	 * @param shortcut
	 *            the shortcut
	 * @param innerHTML
	 *            the inner html
	 */
	public void addOption(String dataValue1, String dataDisplay, String shortcut, String innerHTML) {
		this.options += LI_S + dataValue1 + S_QUOTE_SPACE + DATA_DISPLAY + dataDisplay + S_QUOTE_SPACE + SHORTCUT
				+ shortcut + S_QUOTE_SPACE + TAG_CLOSE + innerHTML + LI_E;
	}

	/**
	 * Sets the tag type.
	 *
	 * @param tagType
	 *            the new tag type
	 */
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	/**
	 * Sets the value.
	 *
	 * @param dataValue
	 *            the new value
	 */
	public void setValue(String dataValue) {
		this.dataValue = dataValue;
	}

	/**
	 * Sets the checks for display div.
	 *
	 * @param hasDisplayDiv
	 *            the new checks for display div
	 */
	public void setHasDisplayDiv(boolean hasDisplayDiv) {
		this.hasDisplayDiv = hasDisplayDiv;
	}

	/**
	 * Sets the display value.
	 *
	 * @param displayValue
	 *            the new display value
	 */
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	/**
	 * Sets the place holder.
	 *
	 * @param placeholder
	 *            the new place holder
	 */
	public void setPlaceHolder(String placeholder) {
		this.placeholder = placeholder;
	}

	/**
	 * Display attr.
	 *
	 * @param attrName
	 *            the attr name
	 * @param attrValue
	 *            the attr value
	 */
	public void displayAttr(String attrName, String attrValue) {
		this.displayAttrs = this.displayAttrs + SPACE + attrName + EQUALS + S_QUOTE + attrValue + S_QUOTE;
	}

	/**
	 * Display attr.
	 *
	 * @param attrName
	 *            the attr name
	 * @param attrValue
	 *            the attr value
	 */
	public void displayAttr(String attrName, long attrValue) {
		this.displayAttrs = this.displayAttrs + SPACE + attrName + EQUALS + S_QUOTE + attrValue + S_QUOTE;
	}

	/**
	 * Display attr.
	 *
	 * @param attrString
	 *            the attr string
	 */
	public void displayAttr(String attrString) {
		this.displayAttrs = this.displayAttrs + SPACE + attrString;
	}

	/**
	 * Gets the disp input.
	 *
	 * @param displayValue
	 *            the display value
	 * @param attrString
	 *            the attr string
	 * @return the disp input
	 */
	public static String getDispInput(String displayValue, String attrString) {
		return DISP_IN + displayValue + S_QUOTE_SPACE + OLD_VAL + displayValue + S_QUOTE_SPACE + attrString
				+ TAG_COMPLETE;
	}

	/**
	 * Gets the disp div.
	 *
	 * @param displayValue
	 *            the display value
	 * @param placeHolder
	 *            the place holder
	 * @return the disp div
	 */
	public static String getDispDiv(String displayValue, String placeHolder) {
		if (!displayValue.equalsIgnoreCase("")) {
			return DISP_DIV + TAG_CLOSE + displayValue + DIV_E;
		}
		return DISP_DIV + TAG_CLOSE + placeHolder + DIV_E;
	}

	/**
	 * Append input class.
	 *
	 * @param classString
	 *            the class string
	 */
	public void appendInputClass(String classString) {
		this.inputClass = this.inputClass + SPACE + classString;
	}

	/**
	 * Gets the value input.
	 *
	 * @param value
	 *            the value
	 * @param inputClass
	 *            the input class
	 * @return the value input
	 */
	public static String getValueInput(String value, String inputClass) {
		return HIDDEN + value + S_QUOTE_SPACE + CLASS + EQUALS + S_QUOTE + inputClass + S_QUOTE + TAG_COMPLETE;
	}

	/**
	 * Instantiates a new parent div element.
	 *
	 * @param tagType
	 *            the tag type
	 */
	public ParentDivElement(String tagType) {
		this.setElementType(ElementType.DIV);
		initTag(tagType, true);
	}

	/**
	 * Instantiates a new parent div element.
	 *
	 * @param tagType
	 *            the tag type
	 * @param hasDisplayDiv
	 *            the has display div
	 */
	public ParentDivElement(String tagType, boolean hasDisplayDiv) {
		this.setElementType(ElementType.DIV);
		initTag(tagType, hasDisplayDiv);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.TagElement#initTag()
	 */
	public void initTag() {
		initTag(this.tagType, this.hasDisplayDiv);
	}

	/**
	 * Inits the tag.
	 *
	 * @param tagType1
	 *            the tag type1
	 * @param hasDisplayDiv1
	 *            the has display div1
	 */
	public void initTag(String tagType1, boolean hasDisplayDiv1) {
		super.initTag();
		setClassString(TAG);
		this.displayValue = EMPTY;
		this.displayAttrs = EMPTY;
		this.placeholder = EMPTY;
		this.setTagType(tagType1);
		this.setHasDisplayDiv(hasDisplayDiv1);
	}

	/**
	 * Sets the nav index.
	 *
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 */
	public void setNavIndex(String row, String col) {
		this.addClass(R + row + SPACE + C + col);
		this.attr(I_ROW, row);
		this.attr(I_COL, col);
	}

	/**
	 * Sets the tab index.
	 *
	 * @param tabIndex
	 *            the new tab index
	 */
	public void setTabIndex(int tabIndex) {
		this.attr(TABINDEX, "" + tabIndex);
	}

	/**
	 * Sets the read only.
	 */
	public void setReadOnly() {
		this.addClass(READ_ONLY_CLASS);
		if (this.hasDisplayDiv) {
			this.displayAttr(READ_ONLY, READ_ONLY);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dfferentia.app.tags.TagElement#toString()
	 */
	public String toString() {
		this.attr(TAG_TYPE, this.tagType);
		if (this.hasDisplayDiv) {
			this.append(getDispInput(this.displayValue, this.displayAttrs));
			this.append(getDispDiv(this.displayValue, this.placeholder));
		}
		if (this.hasOptions) {
			this.append(UL_S + this.options + UL_E);
		}
		this.append(getValueInput(this.dataValue, this.inputClass));
		return super.toString();
	}
}
