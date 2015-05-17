package org.spamjs.mangolite.tags;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import org.spamjs.utils.JsonUtil;

// TODO: Auto-generated Javadoc
/**
 * This class is used to create a list of radio buttons / checkboxes.
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 */
public class Stripbar extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "stripbar";

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

	/** The header text. Text to be displayed on header button. */
	private String headerText;

	/**
	 * Gets the header text.
	 *
	 * @return the header text
	 */
	public String getHeaderText() {
		return this.headerText;
	}

	/**
	 * Sets the header text.
	 *
	 * @param headerText
	 *            the new header text
	 */
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	/**
	 * The header code. Code for text to be displayed on header button. This is
	 * to support i18n support.
	 */
	private String headerCode;

	/**
	 * Gets the header code.
	 *
	 * @return the header code
	 */
	public String getHeaderCode() {
		return headerCode;
	}

	/**
	 * Sets the header code.
	 *
	 * @param headerCode
	 *            the new header code
	 */
	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}

	/** The placeholder - Text to be displayed in search box. */
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

	/** The search clause. */
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
			// Sonar fix: If/Else Stmts Must Use Braces
			if (this.getPlaceholder() == null) {
				this.placeholder = "";
			}
			String search = (this.getSearchClause() != null) ? " searchClause='"
					+ this.getSearchClause() + "'"
					: "";

			String reply = "<div class='fl stripbar tag "
					+ this.getParentDivClass()
					+ "' tagtype='"
					+ this.getTagType()
					+ "'><div class='stripRow'><div class='fl'>"
					+ "<input name='input4' type='button' class='headerbutton' value='"
					+ this.resolveMessage(this.getHeaderCode(),
							this.getHeaderText())
					+ "'/></div>"
					+ (this.getAutosearch() != null ? this.getAutosearch()
							.booleanValue() ? "<div class='clear'></div><div class='fl'><input type='text' placeholder='"
							+ this.placeholder
							+ "' class='stripsearch' "
							+ search + ">" + "</div><div class='clear'></div>"
							: ""
							: "") + "</div><div class='striplist options'>";
			// Fix for Sonar: Performance - Method concatenates
			// strings using +
			// in a loop
			StringBuilder sb = new StringBuilder(reply);

			if ((this.getJson() != null)) {
				body = body.replace("'", "\"");
				Map<String, Object> options = JsonUtil
						.getLinkedMapFromJsonString(body);

				for (Map.Entry<String, Object> mapEntry : options.entrySet()) {
					String key = mapEntry.getKey();
					String value = (String) mapEntry.getValue();
					sb.append("<div class='striprow option'><div class='stripelement td'><div class='stripelemradio'><input type='radio' data-value='");
					sb.append(key);
					sb.append("' value='");
					sb.append(key);
					sb.append("' class='optionValue' name='checkbox1'></div><div class='stripelemname'><span>");
					sb.append(value);
					sb.append("</span></div></div></div>");
				}

			}
			sb.append("</div><input name='input4' class='input value' type='hidden' /></div>");
			out.print(sb.toString());
		} catch (IOException ioe) {
			LOG.error(
					EXCEPTION + this.getFieldType() + " : Error: "
							+ ioe.getMessage(), ioe);
		}
		return (SKIP_BODY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}
}