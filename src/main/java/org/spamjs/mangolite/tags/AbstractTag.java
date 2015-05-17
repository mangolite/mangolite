package org.spamjs.mangolite.tags;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.spamjs.utils.Log;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.support.JspAwareRequestContext;
import org.springframework.web.servlet.support.RequestContext;

// TODO: Auto-generated Javadoc
/**
 * This is the abstract class, used for creating all the JSP custom tags.
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2012
 * 
 */
public abstract class AbstractTag extends BodyTagSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOG. */
	public static final Log LOG = new Log();

	// HTML related constants
	/** The Constant DIV_S. */
	public static final String DIV_S = "<div";

	/** The Constant TAG_CLOSE. */
	public static final String TAG_CLOSE = ">";

	/** The Constant DIV_E. */
	public static final String DIV_E = "</div>";

	/** The Constant TAG_COMPLETE. */
	public static final String TAG_COMPLETE = "/>";

	/** The Constant SPAN_E. */
	public static final String SPAN_E = "</span>";

	/** The Constant SPAN_S. */
	public static final String SPAN_S = "<span class='option";

	/** The Constant HIDDEN. */
	public static final String HIDDEN = "<input type='hidden' value='";

	/** The Constant DISP_IN. */
	public static final String DISP_IN = "<input type='text' class='input display dn' value='";

	/** The Constant DISP_DIV. */
	public static final String DISP_DIV = "<div class='display input' ";

	/** The Constant DIV_RIGHT_POS. */
	public static final String DIV_RIGHT_POS = "<div class='right_position ";

	/** The Constant DIV_OPT. */
	public static final String DIV_OPT = "<div class='option";

	/** The Constant UL_S. */
	public static final String UL_S = "<ul class='dropdown_menu dn'>";

	/** The Constant UL_E. */
	public static final String UL_E = "</ul>";

	/** The Constant LI_E. */
	public static final String LI_E = "</li>";

	/** The Constant LI_S. */
	public static final String LI_S = "<li class='option' ";

	/** The Constant CHECKBOX. */
	public static final String CHECKBOX = "<input type='checkbox' ";

	/** The Constant LABEL_S. */
	public static final String LABEL_S = "<label>";

	/** The Constant LABEL_E. */
	public static final String LABEL_E = "</label>";

	/** The Constant SPACE. */
	public static final String SPACE = " ";

	/** The Constant EMPTY. */
	public static final String EMPTY = "";

	/** The Constant PERCENT. */
	public static final String PERCENT = "%";

	/** The Constant CLASS. */
	public static final String CLASS = " class='";

	/** The Constant TAG. */
	public static final String TAG = "tag";

	/** The Constant S_QUOTE. */
	public static final String S_QUOTE = "'";

	/** The Constant TAB. */
	public static final String TAB = " tabindex='";

	/** The Constant I_ROW. */
	public static final String I_ROW = " iRow='";

	/** The Constant I_COL. */
	public static final String I_COL = " iCol='";

	/*
	 * new start
	 */
	/** The Constant TAB1. */
	public static final String TAB1 = " tabindex";

	/** The Constant I_ROW1. */
	public static final String I_ROW1 = " iRow";

	/** The Constant I_COL1. */
	public static final String I_COL1 = " iCol";

	/** The Constant FIELD1. */
	public static final String FIELD1 = "fieldtype";

	/** The Constant FORMAT_TYPE. */
	public static final String FORMAT_TYPE = "formatType";

	/** The Constant DEF_VAL1. */
	public static final String DEF_VAL1 = "defaultVal";

	/** The Constant RIGHT_POS. */
	public static final String RIGHT_POS = "right_position";

	/** The Constant AMT_TOGGLE. */
	public static final String AMT_TOGGLE = "amount_toggle";

	/** The Constant OPTION. */
	public static final String OPTION = "option";

	/** The Constant DATA_VALUE1. */
	public static final String DATA_VALUE1 = "data-value";

	/** The Constant DATA_DISPLAY1. */
	public static final String DATA_DISPLAY1 = "data-display";

	/** The Constant VALUE. */
	public static final String VALUE = "value";

	/** The Constant TYPE. */
	public static final String TYPE = "type";

	/** The Constant HIDDEN1. */
	public static final String HIDDEN1 = "hidden";

	/** The Constant UNDERSCORE_CUR. */
	public static final String UNDERSCORE_CUR = "_cur";

	/** The Constant KEY. */
	public static final String KEY = "key";

	/** The Constant SINGLE_KEY. */
	public static final String SINGLE_KEY = "singlekey";

	/** The Constant BUTTON1. */
	public static final String BUTTON1 = "button";

	/** The Constant AUTOSEARCH. */
	public static final String AUTOSEARCH = "autosearch";

	/** The Constant INPUT. */
	public static final String INPUT = "input";

	/** The Constant OLD_VAL1. */
	public static final String OLD_VAL1 = "oldval";

	/** The Constant URL. */
	public static final String URL = "url";
	/*
	 * new end
	 */
	/** The Constant SYS. */
	public static final String SYS = "sys";

	/** The Constant APP. */
	public static final String APP = "app";

	/** The Constant ACCESSKEY. */
	public static final String ACCESSKEY = "accesskey";

	/** The Constant NODE_ID. */
	public static final String NODE_ID = "node-id";

	/** The Constant OPEN. */
	public static final String OPEN = "open";

	/** The Constant TITLE. */
	public static final String TITLE = "title";

	/** The Constant CLOSE. */
	public static final String CLOSE = "close";

	/** The Constant R. */
	public static final String R = " r";

	/** The Constant C. */
	public static final String C = " c";

	/** The Constant MAX_LEN. */
	public static final String MAX_LEN = "maxlength";

	/** The Constant MIN_LEN. */
	public static final String MIN_LEN = "minlength";

	/** The Constant EQUAL. */
	public static final String EQUAL = "=";

	/** The Constant MAX_VAL. */
	public static final String MAX_VAL = "maxValue";

	/** The Constant MIN_VAL. */
	public static final String MIN_VAL = "minValue";

	/** The Constant MIN_VAL_EXPR. */
	public static final String MIN_VAL_EXPR = "minValueExpr";

	/** The Constant FIELD. */
	public static final String FIELD = "fieldtype='";

	/** The Constant DEF_VAL. */
	public static final String DEF_VAL = "defaultVal='";

	/** The Constant VAL. */
	public static final String VAL = "value='";

	/** The Constant UNDERSCORE_CUR1. */
	public static final String UNDERSCORE_CUR1 = "_cur";

	/** The Constant NO_ENTER_NEXT. */
	public static final String NO_ENTER_NEXT = " noenternext ";

	/** The Constant TOGGLE_VAL. */
	public static final String TOGGLE_VAL = "toggleValue";

	/** The Constant COMMA. */
	public static final String COMMA = ",";

	/** The Constant DN. */
	public static final String DN = "dn";

	/** The Constant MODE_NONE. */
	public static final String MODE_NONE = " mode_none ";

	/** The Constant DATA_DISPLAY. */
	public static final String DATA_DISPLAY = "data-display='";

	/** The Constant DATA_TITLE. */
	public static final String DATA_TITLE = "title='";

	/** The Constant DATA_VALUE. */
	public static final String DATA_VALUE = "data-value='";

	/** The Constant EQUAL_S_QUOTE. */
	public static final String EQUAL_S_QUOTE = "='";

	/** The Constant S_QUOTE_SPACE. */
	public static final String S_QUOTE_SPACE = "' ";

	/** The Constant STRING_TRUE. */
	public static final String STRING_TRUE = "true";

	/** The Constant STRING_CHECKED. */
	public static final String STRING_CHECKED = "checked='checked'";

	/** The Constant ALL. */
	public static final String ALL = "all";

	/** The Constant NONE. */
	public static final String NONE = "none";

	/** The Constant OLD_VAL. */
	public static final String OLD_VAL = "oldval='";

	// tag names
	/** The Constant TXT_LBL. */
	public static final String TXT_LBL = " textlabel";

	/** The Constant AMT. */
	public static final String AMT = " amount";

	/** The Constant DATE. */
	public static final String DATE = " date";

	/** The Constant TXT. */
	public static final String TXT = " inputbox";

	/** The Constant TOGGLE. */
	public static final String TOGGLE = " toggle";

	/** The Constant BUTTON. */
	public static final String BUTTON = " button";

	/** The Constant CLICKABLE. */
	public static final String CLICKABLE = " clickable";

	/** The Constant TOKENINPUT. */
	public static final String TOKENINPUT = " tokeninput";

	/** The Constant TOGGLEINPUT. */
	public static final String TOGGLEINPUT = " toggleinput";

	/** The Constant DROPDOWN. */
	public static final String DROPDOWN = " dropdown";

	/** The Constant MULTISELECT. */
	public static final String MULTISELECT = " multiselect";

	/** The Constant LIMIT. */
	protected static final String LIMIT = "limit";

	/** The Constant CUR_PAIR_START. */
	protected static final int CUR_PAIR_START = 0;

	/** The Constant CUR_PAIR_MID. */
	protected static final int CUR_PAIR_MID = 3;

	/** The Constant CUR_PAIR_END. */
	protected static final int CUR_PAIR_END = 6;

	/** The Constant READ_ONLY_CLASS. */
	public static final String READ_ONLY_CLASS = "readOnly yes";

	/** The Constant REQUEST_CONTEXT_PAGE_ATTRIBUTE. */
	public static final String REQUEST_CONTEXT_PAGE_ATTRIBUTE = "org.springframework.web.servlet.tags.REQUEST_CONTEXT";
	/** The Constant EXCEPTION. */
	public static final String EXCEPTION = "IOException occured. ";

	/**
	 * Gets the request context.
	 *
	 * @return the request context
	 * @throws JspTagException
	 *             the jsp tag exception
	 */
	protected RequestContext getRequestContext() throws JspTagException {
		try {
			RequestContext requestContext1 = (RequestContext) this.pageContext
					.getAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE);
			if (requestContext1 == null) {
				requestContext1 = new JspAwareRequestContext(this.pageContext);
				this.pageContext.setAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE,
						requestContext1);
			}
			return requestContext1;
		} catch (RuntimeException ex) {
			LOG.error(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
			throw new JspTagException(ex.getMessage());
		}
	}

	/**
	 * This is the handle to the messageResource.properties strings
	 */
	private static ReloadableResourceBundleMessageSource messageSource;

	/**
	 * Sets the message source.
	 *
	 * @param messageSource1
	 *            the new message source
	 */
	public static void setMessageSource(
			ReloadableResourceBundleMessageSource messageSource1) {
		messageSource = messageSource1;
	}

	/**
	 * Resolve message.
	 *
	 * @param code
	 *            the code
	 * @param msg
	 *            the msg
	 * @return the string
	 * @throws JspTagException
	 *             the jsp tag exception
	 */
	protected String resolveMessage(String code, String msg)
			throws JspTagException {
		if (messageSource == null) {
			return (msg == null) ? code : msg;
		}
		return messageSource.getMessage(code, null, (msg == null) ? code : msg,
				getRequestContext().getLocale());
	}

	/** The Constant TAG_TYPE. */
	protected static final String TAG_TYPE = "tag";

	/**
	 * Method is Supposed to return Type of Custom tag.
	 *
	 * @return the tag type
	 */
	protected abstract String getTagType();

	/** The parent div class. */
	private String parentDivClass;

	/**
	 * Gets the parent div class.
	 *
	 * @return the parent div class
	 */
	public String getParentDivClass() {
		return this.parentDivClass != null ? SPACE + this.parentDivClass.trim()
				: EMPTY;
	}

	/**
	 * Sets the parent div class.
	 *
	 * @param parentDivClass
	 *            the new parent div class
	 */
	public void setParentDivClass(String parentDivClass) {
		this.parentDivClass = parentDivClass;
	}

	/** The limit. */
	private String limit;

	/**
	 * Gets the limit.
	 *
	 * @return the limit
	 */
	public String getLimit() {
		return this.limit;
	}

	/**
	 * Sets the limit.
	 *
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(String limit) {
		this.limit = limit;
	}

	/** The field type. */
	private String fieldType;

	/**
	 * Gets the field type.
	 *
	 * @return the field type
	 */
	public String getFieldType() {
		return this.fieldType != null ? this.fieldType.trim() : EMPTY;
	}

	/**
	 * Sets the field type.
	 *
	 * @param fieldType
	 *            the new field type
	 */
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	/** The tab index. */
	private int tabIndex;

	/**
	 * Gets the tab index.
	 *
	 * @return the tab index
	 */
	public int getTabIndex() {
		return this.tabIndex;
	}

	/**
	 * Sets the tab index.
	 *
	 * @param tabIndex
	 *            the new tab index
	 */
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	/**
	 * Sets the tab index.
	 *
	 * @param tabIndex
	 *            the new tab index
	 */
	public void setTabIndex(String tabIndex) {
		this.tabIndex = Integer.parseInt(tabIndex);
	}

	/** The nav index - left, right, up, down. */
	private String navIndex;

	/**
	 * Gets the nav index.
	 *
	 * @return the nav index
	 */
	public String getNavIndex() {
		return this.navIndex;
	}

	/**
	 * Sets the nav index.
	 *
	 * @param navIndex
	 *            the new nav index
	 */
	public void setNavIndex(String navIndex) {
		this.navIndex = navIndex;
	}

	/** The maxlength - TO SET MAXIMUM LENGTH ALLOWED AS INPUT. */
	private int maxlength;

	/**
	 * Gets the maxlength.
	 *
	 * @return the maxlength
	 */
	public int getMaxlength() {
		return this.maxlength;
	}

	/**
	 * Sets the maxlength.
	 *
	 * @param maxlength
	 *            the new maxlength
	 */
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}

	/** The minlength - TO SET MANIIMUM LENGTH ALLOWED AS INPUT. */
	private int minlength;

	/**
	 * Gets the minlength.
	 *
	 * @return the minlength
	 */
	public int getMinlength() {
		return this.minlength;
	}

	/**
	 * Sets the minlength.
	 *
	 * @param minlength
	 *            the minlength to set
	 */
	public void setMinlength(int minlength) {
		this.minlength = minlength;
	}

	/** The max value - maximum input value possible. */
	private String maxValue;

	/**
	 * Gets the max value.
	 *
	 * @return the max value
	 */
	public String getMaxValue() {
		return this.maxValue;
	}

	/**
	 * Sets the max value.
	 *
	 * @param maxValue
	 *            the new max value
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * Sets the max value.
	 *
	 * @param maxValue
	 *            the new max value
	 */
	public void setMaxValue(long maxValue) {
		this.maxValue = "" + maxValue;
	}

	/** The min value - minimum input value possible. */
	private String minValue;

	/**
	 * Gets the min value.
	 *
	 * @return the min value
	 */
	public String getMinValue() {
		return this.minValue;
	}

	/**
	 * Sets the min value.
	 *
	 * @param minValue
	 *            the new min value
	 */
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	/**
	 * Sets the min value.
	 *
	 * @param minValue
	 *            the new min value
	 */
	public void setMinValue(long minValue) {
		this.minValue = "" + minValue;
	}

	/** minimum input value possible. */
	private String minValueExpr;

	/**
	 * Gets the min value expr.
	 *
	 * @return the min value expr
	 */
	public String getMinValueExpr() {
		return this.minValueExpr;
	}

	/**
	 * Sets the min value expr.
	 *
	 * @param minValueExpr
	 *            the new min value expr
	 */
	public void setMinValueExpr(String minValueExpr) {
		this.minValueExpr = minValueExpr;
	}

	/**
	 * Sets the min value expr.
	 *
	 * @param minValueExpr
	 *            the new min value expr
	 */
	public void setMinValueExpr(long minValueExpr) {
		this.minValueExpr = "" + minValueExpr;
	}

	/** default value to be set. */
	private String defaultVal;

	/**
	 * Gets the default val.
	 *
	 * @return the default val
	 */
	public String getDefaultVal() {
		return this.defaultVal != null ? this.defaultVal.trim() : EMPTY;
	}

	/**
	 * Sets the default val.
	 *
	 * @param defaultVal
	 *            the new default val
	 */
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	/** display value. */
	private String dataDisplay;

	/**
	 * Gets the data display.
	 *
	 * @return the data display
	 */
	public String getDataDisplay() {
		return this.dataDisplay;
	}

	/**
	 * Sets the data display.
	 *
	 * @param displayValue
	 *            the new data display
	 */
	public void setDataDisplay(String displayValue) {
		this.dataDisplay = displayValue;
	}

	/** data value. */
	private String dataValue;

	/**
	 * Gets the data value.
	 *
	 * @return the data value
	 */
	public String getDataValue() {
		return this.dataValue;
	}

	/**
	 * Sets the data value.
	 *
	 * @param dataValue
	 *            the new data value
	 */
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	/** Net Value returned. */
	private String value;

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/** editable. */
	private Boolean readOnly = Boolean.FALSE;

	/**
	 * Gets the read only.
	 *
	 * @return the read only
	 */
	public Boolean getReadOnly() {
		return this.readOnly;
	}

	/**
	 * Sets the read only.
	 *
	 * @param readOnly
	 *            the new read only
	 */
	public void setReadOnly(Boolean readOnly) {
		if (readOnly != null) {
			this.readOnly = readOnly;
		}
	}

	/** autosearch attribute. */
	private Boolean autosearch = Boolean.FALSE;

	/**
	 * Gets the autosearch.
	 *
	 * @return the autosearch
	 */
	public Boolean getAutosearch() {
		return this.autosearch;
	}

	/**
	 * Sets the autosearch.
	 *
	 * @param autosearch
	 *            the new autosearch
	 */
	public void setAutosearch(Boolean autosearch) {
		this.autosearch = autosearch;
	}

	/** custom attributes. */
	private String attrNames;

	/**
	 * Gets the attr names.
	 *
	 * @return the attr names
	 */
	public String getAttrNames() {
		return this.attrNames;
	}

	/**
	 * Sets the attr names.
	 *
	 * @param attrNames
	 *            the new attr names
	 */
	public void setAttrNames(String attrNames) {
		this.attrNames = attrNames;
	}

	// custom attributes
	/** The data content. */
	private String dataContent;

	/**
	 * Gets the data content.
	 *
	 * @return the data content
	 */
	public String getDataContent() {
		return dataContent;
	}

	/**
	 * Sets the data content.
	 *
	 * @param dataContent the new data content
	 */
	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	/** The attr values. */
	private String attrValues;

	/**
	 * Gets the attr values.
	 *
	 * @return the attr values
	 */
	public String getAttrValues() {
		return this.attrValues;
	}

	/**
	 * Sets the attr values.
	 *
	 * @param attrValues
	 *            the new attr values
	 */
	public void setAttrValues(String attrValues) {
		this.attrValues = attrValues;
	}

	/** autosearch attribute. */
	private String emptyValue;

	/**
	 * Gets the empty value.
	 *
	 * @return the empty value
	 */
	public String getEmptyValue() {
		return this.emptyValue;
	}

	/**
	 * Sets the empty value.
	 *
	 * @param emptyValue
	 *            the new empty value
	 */
	public void setEmptyValue(String emptyValue) {
		this.emptyValue = emptyValue;
	}

	/** The format type. */
	private String formatType;

	/**
	 * Gets the format type.
	 *
	 * @return the format type
	 */
	public String getFormatType() {
		return this.formatType;
	}

	/**
	 * Sets the format type.
	 *
	 * @param formatType
	 *            the new format type
	 */
	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	/**
	 * to be deleted - end.
	 *
	 * @param tagElement
	 *            the tag element
	 */

	public void addAttributes(TagElement tagElement) {
		if (this.getAttrNames() != null && this.getAttrValues() != null) {
			String names[] = this.getAttrNames().trim().split(COMMA);
			String values[] = this.getAttrValues().trim().split(COMMA);
			int valid = (names.length < values.length) ? names.length
					: values.length;
			if (values.length != names.length) {
				LOG.error("Exception occured: fieldType = " + this.fieldType
						+ ": no. of names & values are not same.");
			}
			for (int i = 0; i < valid; i++) {
				tagElement.attr(names[i], values[i]);
			}
		}
	}

	/**
	 * Gets the disp input.
	 *
	 * @param value
	 *            the value
	 * @param xtraParam
	 *            the xtra param
	 * @return the disp input
	 */
	public static String getDispInput(String value, String xtraParam) {
		return DISP_IN + value + S_QUOTE_SPACE + OLD_VAL + value
				+ S_QUOTE_SPACE + xtraParam + TAG_COMPLETE;
	}

	/**
	 * Gets the disp div.
	 *
	 * @param value
	 *            the value
	 * @param tabStr
	 *            the tab str
	 * @return the disp div
	 */
	public static String getDispDiv(String value, String tabStr) {
		return DISP_DIV + tabStr + TAG_CLOSE + value + DIV_E;
	}

	/** The is nav index. */
	private boolean isNavIndex = true;

	/** The is tab index. */
	private boolean isTabIndex = true;

	/** The is read only. */
	private boolean isReadOnly = true;

	/**
	 * Gets the parent div.
	 *
	 * @return the parent div
	 */
	protected ParentDivElement getParentDiv() {
		ParentDivElement parentDiv = new ParentDivElement(this.getTagType());
		return getParentDiv(parentDiv);
	}

	/**
	 * Gets the parent div.
	 *
	 * @param parentDiv
	 *            the parent div
	 * @return the parent div
	 */
	protected ParentDivElement getParentDiv(ParentDivElement parentDiv) {
		parentDiv.initTag();
		parentDiv.addClass(this.getTagType());
		parentDiv.addClass(this.getFieldType());
		parentDiv.attr(FIELD1, this.getFieldType());
		parentDiv.addClass(this.getParentDivClass());
		addAttributes(parentDiv);

		handleFormatType(parentDiv);
		handleNavIndex(parentDiv);
		handleTabIndex(parentDiv);
		handleReadOnly(parentDiv);
		handleMaxLength(parentDiv);
		handleMinLength(parentDiv);
		handleMinValue(parentDiv);
		handleMinValueExpr(parentDiv);
		handleMaxValue(parentDiv);
		handleLimit(parentDiv);
		return parentDiv;
	}

	/**
	 * Handles formattype variable.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	private void handleFormatType(ParentDivElement parentDiv) {
		if (this.getFormatType() != null) {
			parentDiv.attr(FORMAT_TYPE, this.getFormatType());
		}
	}

	/**
	 * Handles navIndex variable.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	private void handleNavIndex(ParentDivElement parentDiv) {
		if (this.isNavIndex && this.getNavIndex() != null) {
			String inav[] = this.getNavIndex().trim().split(COMMA);
			parentDiv.setNavIndex(inav[0], inav[1]);
		}
	}

	/**
	 * Handles tabIndex variable.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	private void handleTabIndex(ParentDivElement parentDiv) {
		if (this.isTabIndex) {
			parentDiv.attr(TAB1, "" + this.getTabIndex());
		}
	}

	/**
	 * Handles readonly variable.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	private void handleReadOnly(ParentDivElement parentDiv) {
		if (this.isReadOnly && this.getReadOnly().booleanValue()) {
			parentDiv.setReadOnly();
		}
	}

	/**
	 * Handles maxLength variable.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	private void handleMaxLength(ParentDivElement parentDiv) {
		if (this.getMaxlength() > 0) {
			parentDiv.displayAttr(MAX_LEN, this.getMaxlength());
			parentDiv.attr(MAX_LEN, this.getMaxlength());
			parentDiv.addClass(MAX_LEN);
		}
	}

	/**
	 * Handles minLength variable.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	private void handleMinLength(ParentDivElement parentDiv) {
		if (this.getMinlength() > 0) {
			parentDiv.displayAttr(MIN_LEN, this.getMinlength());
			parentDiv.attr(MIN_LEN, this.getMinlength());
			parentDiv.addClass(MIN_LEN);
		}
	}

	/**
	 * Handles minValue variable.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	private void handleMinValue(ParentDivElement parentDiv) {
		if (this.getMinValue() != null) {
			// TODO @poonam:- TO VERIFY IF IT IS USED IN JavaScript
			// else remove
			// it
			parentDiv.displayAttr(MIN_VAL, this.getMinValue());
			parentDiv.attr(MIN_VAL, this.getMinValue());
			parentDiv.addClass(MIN_VAL);
		}
	}

	/**
	 * Handles minValueExpr variable.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	private void handleMinValueExpr(ParentDivElement parentDiv) {
		if (this.getMinValueExpr() != null) {
			// TODO@poonam:- TO VERIFY IF IT IS USED IN JavaScript
			// else remove
			// it
			parentDiv.displayAttr(MIN_VAL_EXPR, this.getMinValueExpr());
			parentDiv.attr(MIN_VAL_EXPR, this.getMinValueExpr());
			parentDiv.addClass(MIN_VAL_EXPR);
		}
	}

	/**
	 * Handles maxValue variable.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	private void handleMaxValue(ParentDivElement parentDiv) {
		if (this.getMaxValue() != null) {
			// TODO:- @poonam:-- TO VERIFY IF IT IS USED IN JavaScript
			// else
			// remove it
			parentDiv.displayAttr(MAX_VAL, this.getMaxValue());
			parentDiv.attr(MAX_VAL, this.getMaxValue());
			parentDiv.addClass(MAX_VAL);
		}
		if (this.getDataContent() != null) {
			parentDiv.attr("data-content", this.getDataContent());
		}
	}

	/**
	 * Handles limit variable.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	private void handleLimit(ParentDivElement parentDiv) {
		if (this.getLimit() != null) {
			parentDiv.attr(LIMIT, this.getLimit());
		}
	}
}