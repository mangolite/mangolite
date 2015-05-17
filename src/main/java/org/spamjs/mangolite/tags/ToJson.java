package org.spamjs.mangolite.tags;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.spamjs.utils.JsonUtil;

// TODO: Auto-generated Javadoc
/**
 * to Render JSON representaion in JSPO page.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 2.2
 * @since Aug 30, 2014
 */
public class ToJson extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "toJson";

	/** The map. */
	private Object map;

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Object getMap() {
		return map;
	}

	/**
	 * Sets the map.
	 *
	 * @param map
	 *            the new map
	 */
	public void setMap(Object map) {
		this.map = map;
	}

	/**
	 * Default processing of the end tag returning EVAL_PAGE.
	 *
	 * @return EVAL_PAGE
	 * @see Tag#doEndTag
	 */
	@Override
	public int doEndTag() {
		JspWriter out = this.pageContext.getOut();
		try {
			out.print(JsonUtil.toJson(getMap()));
		} catch (IOException e) {
			LOG.error("---cannot write for args---" + e.toString());
		}
		return EVAL_PAGE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}
}