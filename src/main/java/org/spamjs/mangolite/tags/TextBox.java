package org.spamjs.mangolite.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

// TODO: Auto-generated Javadoc
/**
 * This class is used for creating "textbox" field.
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 * 
 */
public class TextBox extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "inputbox";

	/** The Constant ROUND. */
	private static final String ROUND = "round";

	/** The Constant REG_EXPR. */
	private static final String REG_EXPR = "regex";

	/** The Constant REG_EXPR_MSG. */
	private static final String REG_EXPR_MSG = "regexmsg";

	/** The Constant ERROR_CODE. */
	private static final String ERROR_CODE = "errorCode";

	/** The Constant ERROR_MSG. */
	private static final String ERROR_MSG = "errorMsg";

	/** The Constant SUFFIX. */
	private static final String SUFFIX = "suffix";

	/** The Constant SUFFIX_HIDE. */
	private static final String SUFFIX_HIDE = "suffix_hide";

	/** The Constant PLACEHOLDER. */
	private static final String PLACEHOLDER = "placeholder";

	/** The Constant HELP_TEXT. */
	private static final String HELP_TEXT = "helptext help";

	/** The Constant INVALID_MSG. */
	private static final String INVALID_MSG = "invalidInput";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}

	/**
	 * The Enum DataType.
	 */
	public static enum DataType {

		/** The percent. */
		percent,
		/** The decimal. */
		decimal,
		/** The rounded. */
		rounded,
		/** The integer. */
		integer
	};

	/** The data type. */
	private DataType dataType;

	/**
	 * Gets the data type.
	 *
	 * @return the data type
	 */
	public DataType getDataType() {
		return this.dataType;
	}

	/**
	 * Sets the data type.
	 *
	 * @param dataType
	 *            the new data type
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	/** The round. */
	private String round;

	/**
	 * Gets the round.
	 *
	 * @return the round
	 */
	public String getRound() {
		return this.round;
	}

	/**
	 * Sets the round.
	 *
	 * @param round
	 *            the new round
	 */
	public void setRound(String round) {
		this.round = round;
	}

	/** The help text. */
	private String helpText;

	/**
	 * Gets the help text.
	 *
	 * @return the help text
	 */
	public String getHelpText() {
		return this.helpText;
	}

	/**
	 * Sets the help text.
	 *
	 * @param helpText
	 *            the new help text
	 */
	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	/** The placeholder. */
	private String placeholder;

	/**
	 * Gets the placeholder.
	 *
	 * @return the placeholder
	 */
	public String getPlaceholder() {
		return this.placeholder;
	}

	/**
	 * Sets the placeholder.
	 *
	 * @param placeholder
	 *            the new placeholder
	 */
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	/** The suffix. */
	private String suffix;

	/**
	 * Gets the suffix.
	 *
	 * @return the suffix
	 */
	public String getSuffix() {
		return this.suffix;
	}

	/**
	 * Sets the suffix.
	 *
	 * @param suffix
	 *            the new suffix
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/** The regex. */
	private String regex;

	/**
	 * Gets the regex.
	 *
	 * @return the regex
	 */
	public String getRegex() {
		return this.regex;
	}

	/**
	 * Sets the regex.
	 *
	 * @param regex
	 *            the regex to set
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}

	/** The regexmsg. */
	private String regexmsg;

	/**
	 * Gets the regexmsg.
	 *
	 * @return the regexmsg
	 */
	public String getRegexmsg() {
		if (this.regexmsg == null) {
			return INVALID_MSG;
		}
		return this.regexmsg;
	}

	/**
	 * Sets the regexmsg.
	 *
	 * @param regexmsg
	 *            the regexmsg to set
	 */
	public void setRegexmsg(String regexmsg) {
		this.regexmsg = regexmsg;
	}

	/** The error code. */
	private String errorCode;

	/**
	 * Gets the errorcode.
	 *
	 * @return the errorcode
	 */
	public String getErrorcode() {
		return errorCode;
	}

	/**
	 * Sets the errorcode.
	 *
	 * @param errorcode
	 *            the errorcode to set
	 */
	public void setErrorcode(String errorcode) {
		this.errorCode = errorcode;
	}

	/** The error msg. */
	private String errorMsg;

	/**
	 * Gets the error msg.
	 *
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Sets the error msg.
	 *
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/** The placeholder code. */
	private String placeholderCode;

	/**
	 * Gets the placeholder code.
	 *
	 * @return the placeholderCode
	 */
	public String getPlaceholderCode() {
		return placeholderCode;
	}

	/**
	 * Sets the placeholder code.
	 *
	 * @param placeholderCode
	 *            the placeholderCode to set
	 */
	public void setPlaceholderCode(String placeholderCode) {
		this.placeholderCode = placeholderCode;
	}

	/** The placeholder text. */
	private String placeholderText;

	/**
	 * Gets the placeholder text.
	 *
	 * @return the placeholderText
	 */
	public String getPlaceholderText() {
		return placeholderText;
	}

	/**
	 * Sets the placeholder text.
	 *
	 * @param placeholderText
	 *            the placeholderText to set
	 */
	public void setPlaceholderText(String placeholderText) {
		this.placeholderText = placeholderText;
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

			String placeholderStr = EMPTY;
			if ((this.getPlaceholder() != null && this.getPlaceholderCode() == null)) {
				parentDiv.attr(PLACEHOLDER, this.getPlaceholder());
				placeholderStr = this.getPlaceholder();
			}
			if (this.getPlaceholderCode() != null) {
				String plmsg = this.resolveMessage(this.placeholderCode, this.placeholderText);
				parentDiv.attr(PLACEHOLDER, plmsg);
				parentDiv.addClass(HELP_TEXT);
				placeholderStr = plmsg;
			}

			setValue((this.getValue() != null) ? this.getValue().trim() : EMPTY);
			String displayValue = getValue();
			String dataValue = getValue();
			if ((this.getSuffix() != null)) {
				this.suffix = this.getSuffix().trim();
				parentDiv.displayAttr(SUFFIX, this.suffix);
				parentDiv.addClass(SUFFIX_HIDE);
				displayValue = displayValue.replaceAll(this.suffix, EMPTY);
				dataValue = displayValue + this.suffix;
			}
			if ((this.getRound() != null)) {
				parentDiv.addClass(ROUND);
				parentDiv.displayAttr(ROUND, this.getRound());
				parentDiv.attr(ROUND, this.getRound());
			}
			if ((this.getRegex() != null)) {
				parentDiv.addClass(REG_EXPR);
				parentDiv.attr(REG_EXPR, this.getRegex());
				parentDiv.attr(FORMAT_TYPE, REG_EXPR);
				parentDiv.attr(REG_EXPR_MSG, this.getRegexmsg());
			}
			if ((this.getErrorcode() != null)) {
				// TODO : BASED ON BROWSER/USER PREF.
				parentDiv.attr(ERROR_CODE, this.errorCode);
				parentDiv.attr(ERROR_MSG, this.resolveMessage(this.errorCode, this.errorMsg));
			}

			parentDiv.displayAttr(TAB1, "" + this.getTabIndex());
			// for adding new attributes
			parentDiv.setDisplayValue(displayValue);
			parentDiv.setPlaceHolder(placeholderStr);
			parentDiv.setValue(dataValue);
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