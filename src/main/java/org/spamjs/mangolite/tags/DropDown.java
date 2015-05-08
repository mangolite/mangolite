package org.spamjs.mangolite.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import org.spamjs.utils.JsonUtil;

/**
 * This class is used for creating "dropdown" field.
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * 
 */
public class DropDown extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "dropdown";

	/** The Constant UNDERSCORE. */
	private static final String UNDERSCORE = "_";

	/** The Constant DATA_ATTR. */
	private static final String DATA_ATTR = "data-";

	/** The Constant TOKEN_URL. */
	private static final String TOKEN_URL = "url";

	/** The Constant TYPE_CUSTOM_TOKEN. */
	private static final String TYPE_CUSTOM_TOKEN = "customtokeninput";

	/** The Constant FLAG_HAS_AUTO_SEARCH. */
	private static final String FLAG_HAS_AUTO_SEARCH = "autosearch";

	/** The Constant FINAL_DISPLAY. */
	private static final String FINAL_DISPLAY = "finalDisplay";

	/** The Constant FINAL_DATA. */
	private static final String FINAL_DATA = "finalData";

	/** The Constant DISP. */
	private static final String DISP = "disp";

	/** The Constant DATA. */
	private static final String DATA = "data";

	/** The Constant BODY. */
	private static final String BODY = "body";

	/** The Constant FIRST_KEY. */
	private static final String FIRST_KEY = "firstKey";

	/** The Constant FIRST_DISP. */
	private static final String FIRST_DISP = "firstDisp";

	/** The Constant OPTIONS. */
	private static final String OPTIONS = "options";

	/** The Constant NEW_BODY. */
	private static final String NEW_BODY = "newBody";

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

	/** The extra option. */
	private String extraOption;

	/**
	 * Gets the extra option.
	 *
	 * @return the extra option
	 */
	public String getExtraOption() {
		return this.extraOption;
	}

	/**
	 * Sets the extra option.
	 *
	 * @param extraOption
	 *            the new extra option
	 */
	public void setExtraOption(String extraOption) {
		this.extraOption = extraOption;
	}

	/** The json. */
	private String json;

	/**
	 * Gets the json.
	 *
	 * @return the json
	 */
	public String getJson() {
		return this.json;
	}

	/**
	 * Sets the json.
	 *
	 * @param json
	 *            the new json
	 */
	public void setJson(String json) {
		this.json = json;
	}

	/** The display map list. */
	private String displayMapList;

	/**
	 * Gets the display map list.
	 *
	 * @return the display map list
	 */
	public String getDisplayMapList() {
		return this.displayMapList;
	}

	/**
	 * Sets the display map list.
	 *
	 * @param displayMap
	 *            the new display map list
	 */
	public void setDisplayMapList(String displayMap) {
		this.displayMapList = displayMap;
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

	/** The prefix. */
	private String prefix;

	/**
	 * Gets the prefix.
	 *
	 * @return the prefix
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**
	 * Sets the prefix.
	 *
	 * @param prefix
	 *            the new prefix
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/** The token url. */
	private String tokenURL;

	/**
	 * Gets the token url.
	 *
	 * @return the token url
	 */
	public String getTokenURL() {
		return this.tokenURL;
	}

	/**
	 * Sets the token url.
	 *
	 * @param tokenURL
	 *            the new token url
	 */
	public void setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
	}

	/**
	 * The escape single quote - attribute which tells, whether to escape single
	 * quote or not.
	 */
	private Boolean escapeSQuote;

	/**
	 * Gets the escape single quote.
	 *
	 * @return the escape single quote
	 */
	public Boolean getEscapeSQuote() {
		return this.escapeSQuote;
	}

	/**
	 * Sets the escape single quote.
	 *
	 * @param escapeSQuote
	 *            the new escape single quote
	 */
	public void setEscapeSQuote(Boolean escapeSQuote) {
		this.escapeSQuote = escapeSQuote;
	}

	/**
	 * The search clause. Attribute's values can be - - contains - startsWith -
	 * endsWith
	 */
	private String searchClause;

	/**
	 * Gets the search clause.
	 *
	 * @return the search clause
	 */
	public String getSearchClause() {
		return this.searchClause;
	}

	/**
	 * Sets the search clause.
	 *
	 * @param searchClause
	 *            the new search clause
	 */
	public void setSearchClause(String searchClause) {
		this.searchClause = searchClause;
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
			parentDiv.addClass(this.getParentDivClass() + SPACE + MODE_NONE
					+ "droption");

			String disp = EMPTY;
			String data = EMPTY;
			if (this.prefix == null) {
				this.prefix = EMPTY;
			}

			Map<String, Object> responseMap = new HashMap<String, Object>();
			String prepend = getPrepend();
			if ((this.getJson() != null)) {
				responseMap = fromJson(body, prepend, parentDiv);
				body = (String) responseMap.get(BODY);
				data = (String) responseMap.get(DATA);
				disp = (String) responseMap.get(DISP);
			} else if (this.getDisplayMapList() != null) {
				responseMap = fromDisplayMapList(body, prepend, parentDiv);
				body = (String) responseMap.get(BODY);
				data = (String) responseMap.get(DATA);
				disp = (String) responseMap.get(DISP);
			} else {
				disp = this.getValue() != null ? this.getValue().trim() : EMPTY;
				data = disp.toUpperCase();
				if (data.contains(SPACE)) {
					data = data.replace(SPACE, UNDERSCORE);
				}
			}

			handleAutoSearch(parentDiv);
			body = handleTokenUrl(body, parentDiv);
			parentDiv.attr(DEF_VAL1, this.getDefaultVal());

			String finalDisplay = EMPTY;
			String finalData = EMPTY;
			responseMap = handleEmptyValue(parentDiv, disp, data);
			finalData = (String) responseMap.get(FINAL_DATA);
			finalDisplay = (String) responseMap.get(FINAL_DISPLAY);

			parentDiv.attr(TITLE, finalDisplay);
			parentDiv.setDisplayValue(finalDisplay);
			parentDiv.setValue(finalData);

			parentDiv.append("<a class='downArrow closed' tabindex='-1'></a>"
					+ UL_S + body + UL_E);
			out.print(parentDiv.toString());
		} catch (IOException ioe) {
			LOG.error(
					EXCEPTION + this.getFieldType() + " : Error: "
							+ ioe.getMessage(), ioe);
		}
		return (SKIP_BODY);
	}

	/**
	 * Handles condition for empty value.
	 *
	 * @param parentDiv
	 *            the parent div
	 * @param disp
	 *            the disp
	 * @param data
	 *            the data
	 * @return the map
	 */
	public Map<String, Object> handleEmptyValue(ParentDivElement parentDiv,
			String disp, String data) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (disp != null && data != null) {
			returnMap.put(FINAL_DISPLAY, this.prefix + disp);
			returnMap.put(FINAL_DATA, data);
		} else if (this.getEmptyValue() != null) {
			returnMap.put(FINAL_DISPLAY, this.getEmptyValue());
			returnMap.put(FINAL_DATA, EMPTY);
			parentDiv.attr(DATA_ATTR, this.getEmptyValue());
		} else {
			returnMap.put(FINAL_DISPLAY, this.prefix + EMPTY);
			returnMap.put(FINAL_DATA, EMPTY);
		}
		return returnMap;
	}

	/**
	 * Handles condition for auto search.
	 *
	 * @param parentDiv
	 *            the parent div
	 */
	public void handleAutoSearch(ParentDivElement parentDiv) {
		if (this.getAutosearch().booleanValue()) {
			parentDiv.addClass(FLAG_HAS_AUTO_SEARCH);
			if (this.getSearchClause() != null) {
				parentDiv.addClass(this.searchClause);
			}
		}
	}

	/**
	 * Handles condition for token URL.
	 *
	 * @param body
	 *            the body
	 * @param parentDiv
	 *            the parent div
	 * @return the string
	 */
	public String handleTokenUrl(String body, ParentDivElement parentDiv) {
		if (this.getTokenURL() != null) {
			parentDiv.addClass(TYPE_CUSTOM_TOKEN);
			parentDiv.attr(TOKEN_URL, this.getTokenURL());
			return EMPTY;
		}
		return body;
	}

	/**
	 * Makes prepend string.
	 *
	 * @return the prepend
	 */
	public String getPrepend() {
		if (this.getExtraOption() != null) {
			String[] opts = (this.getExtraOption()).split(COMMA);
			if (opts.length > 2) {
				return (LI_S + DATA_VALUE + opts[1] + S_QUOTE_SPACE
						+ DATA_DISPLAY + this.prefix + opts[2] + S_QUOTE
						+ TAG_CLOSE + this.prefix + opts[2] + LI_E);
			}
		}
		return EMPTY;
	}

	/**
	 * Making dropdown from display map list.
	 *
	 * @param body
	 *            the body
	 * @param prepend
	 *            the prepend
	 * @param parentDiv
	 *            the parent div
	 * @return the map
	 */
	public Map<String, Object> fromDisplayMapList(String body, String prepend,
			ParentDivElement parentDiv) {
		String newBody = EMPTY;
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> responseMap = processBody(body);
		List<Map<String, Object>> options = (List<Map<String, Object>>) responseMap
				.get(OPTIONS);
		newBody = (String) responseMap.get(NEW_BODY);

		if (options.isEmpty() && this.getAutosearch() == null) {
			parentDiv.addClass("readOnly yes");
		}
		responseMap = traverseOptions(options, prepend);
		String firstKey = (String) responseMap.get(FIRST_KEY);
		String firstDisp = (String) responseMap.get(FIRST_DISP);
		newBody = (String) responseMap.get(BODY);

		if (this.getDataValue() != null) {
			if (firstKey != null) {
				returnMap.put(DISP, firstDisp);
				returnMap.put(DATA, firstKey);
			} else {
				returnMap.put(DISP, firstDisp);
				returnMap.put(DATA, this.getDataValue());
			}
		}
		returnMap.put(BODY, newBody);
		return returnMap;
	}

	/**
	 * Formats options from body.
	 *
	 * @param body
	 *            the body
	 * @return Options
	 */
	public Map<String, Object> processBody(String body) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String newBody = EMPTY;
		if (this.escapeSQuote != null) {
			if (this.escapeSQuote.booleanValue()) {
				// For Special character '
				newBody = body.replaceAll("'", "&#039;");
			} else {
				newBody = body.replaceAll("'", "\"");
			}
		} else {
			newBody = body.replaceAll("'", "\"");
		}
		responseMap.put(OPTIONS, JsonUtil.fromJson(newBody, List.class));
		responseMap.put(NEW_BODY, newBody);
		return responseMap;
	}

	/**
	 * Traverses options & makes body.
	 *
	 * @param options
	 *            the options
	 * @param prepend
	 *            the prepend
	 * @return A map, which contains body, first key & first display
	 */
	public Map<String, Object> traverseOptions(
			List<Map<String, Object>> options, String prepend) {
		String firstKey = null, firstDisp = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		for (Map<String, Object> val : options) {
			sb.append(prepend);
			sb.append(LI_S);
			sb.append(DATA_VALUE);
			sb.append(val.get("shortcut"));
			sb.append("' shortcut='");
			sb.append(val.get(""));
			sb.append(S_QUOTE_SPACE);
			sb.append(DATA_DISPLAY);
			sb.append(this.prefix);
			sb.append(val.get("displayLabel"));
			sb.append(S_QUOTE_SPACE);
			sb.append(DATA_TITLE);
			sb.append(this.prefix);
			sb.append(val.get("label"));
			sb.append(S_QUOTE);
			sb.append(TAG_CLOSE);
			sb.append(this.prefix);
			sb.append(val.get("label"));
			sb.append(LI_E);
			if (firstKey == null) {
				firstKey = (String) val.get("shortcut");
			}
			if (firstDisp == null) {
				firstDisp = (String) val.get("displayLabel");
			}
		}
		responseMap.put(BODY, sb.toString());
		responseMap.put(FIRST_KEY, firstKey);
		responseMap.put(FIRST_DISP, firstDisp);
		return responseMap;
	}

	/**
	 * Making dropdown from json.
	 *
	 * @param body
	 *            the body
	 * @param prepend
	 *            the prepend
	 * @param parentDiv
	 *            the parent div
	 * @return the map
	 */
	public Map<String, Object> fromJson(String body, String prepend,
			ParentDivElement parentDiv) {
		String newBody = EMPTY;
		String reply = prepend;
		newBody = handleEscapeQuoteCondtn(body);
		Map<String, Object> options;
		try {
			options = JsonUtil.getLinkedMapFromJsonString(newBody);
		} catch (IOException ioe) {
			newBody = EMPTY;
			options = new HashMap<String, Object>();
		}
		if (options.isEmpty() && this.getAutosearch() == null) {
			parentDiv.addClass("readOnly yes");
		}
		Map<String, Object> responseMap = getHtmlFromJsonOptions(options, reply);
		Map<String, Object> returnMap = setDataDisplay(options,
				(String) responseMap.get(FIRST_KEY));
		returnMap.put(BODY, (String) responseMap.get(BODY));
		return returnMap;
	}

	/**
	 * Setting data & display parameters.
	 *
	 * @param options
	 *            the options
	 * @param firstKey
	 *            the first key
	 * @return the map
	 */
	private Map<String, Object> setDataDisplay(Map<String, Object> options,
			String firstKey) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (this.getDataValue() != null) {
			String key = this.getDataValue();
			Object option = options.get(key);
			if (option == null) {
				if (firstKey != null) {
					returnMap.put(DISP, options.get(firstKey));
					returnMap.put(DATA, firstKey);
				}
			} else {
				returnMap.put(DISP, option);
				returnMap.put(DATA, this.getDataValue());
			}
		}
		return returnMap;
	}

	/**
	 * Handles escape quote condition.
	 *
	 * @param body
	 *            the body
	 * @return the string
	 */
	private String handleEscapeQuoteCondtn(String body) {
		if (this.escapeSQuote != null) {
			if (this.escapeSQuote.booleanValue()) {
				// For Special character '
				return body.replaceAll("'", "&#039;");
			} else {
				return body.replaceAll("'", "\"");
			}
		} else {
			return body.replaceAll("'", "\"");
		}
	}

	/**
	 * Get html from json options.
	 *
	 * @param body
	 *            the body
	 * @param options
	 *            the options
	 * @param reply
	 *            the reply
	 * @return the html from json options
	 */
	private Map<String, Object> getHtmlFromJsonOptions(
			Map<String, Object> options, String reply) {
		String newBody = EMPTY;
		String firstKey = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> mapEntry : options.entrySet()) {
			String key = mapEntry.getKey();
			String val = (String) mapEntry.getValue();
			sb.append(reply);
			sb.append(LI_S);
			sb.append(DATA_VALUE);
			sb.append(key);
			sb.append("' shortcut='");
			sb.append(val.toUpperCase());
			sb.append(S_QUOTE_SPACE);
			sb.append(DATA_DISPLAY);
			sb.append(this.prefix);
			sb.append(val);
			sb.append(S_QUOTE_SPACE);
			sb.append(DATA_TITLE);
			sb.append(this.prefix);
			sb.append(val);
			sb.append(S_QUOTE);
			sb.append(TAG_CLOSE);
			sb.append(this.prefix);
			sb.append(val);
			sb.append(LI_E);
			if (firstKey == null) {
				firstKey = key;
			}
			newBody = sb.toString();
		}
		responseMap.put(BODY, newBody);
		responseMap.put(FIRST_KEY, firstKey);
		return responseMap;
	}
}