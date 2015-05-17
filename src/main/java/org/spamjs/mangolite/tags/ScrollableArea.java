package org.spamjs.mangolite.tags;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.Tag;

import org.spamjs.mangolite.tags.TagElement.ElementType;

// TODO: Auto-generated Javadoc
/**
 * ScrollBar WidgetTag.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 */
public class ScrollableArea extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant SCROLLBAR1. */
	private static final String SCROLLBAR1 = "<div class='scrollBar";

	/** The Constant SCROLLBAR2. */
	private static final String SCROLLBAR2 = " draggableBlock'><div class='scrollDiv";

	/** The Constant SCROLLBAR3. */
	private static final String SCROLLBAR3 = "'><div class='";

	/** The Constant SCROLLBAR4. */
	private static final String SCROLLBAR4 = "Content'></div></div><div class='handle draggable drag";

	/** The Constant SCROLLBAR5. */
	private static final String SCROLLBAR5 = "'></div></div>";

	/** The Constant INNERSCROLL_DIV. */
	private static final String INNERSCROLL_DIV = "innerScrollDiv";

	/** The Constant DIRECTION_X. */
	private static final String DIRECTION_X = "X";

	/** The Constant DIRECTION_Y. */
	private static final String DIRECTION_Y = "Y";

	/** The scroll x. */
	private Boolean scrollX;

	/** The scroll y. */
	private Boolean scrollY;

	/** The left. */
	private String left;

	/**
	 * Gets the left.
	 *
	 * @return the left
	 */
	public String getLeft() {
		return left;
	}

	/**
	 * Sets the left.
	 *
	 * @param left
	 *            the new left
	 */
	public void setLeft(String left) {
		this.left = left;
	}

	/** The top. */
	private String top;

	/**
	 * Gets the top.
	 *
	 * @return the top
	 */
	public String getTop() {
		return top;
	}

	/**
	 * Sets the top.
	 *
	 * @param top
	 *            the new top
	 */
	public void setTop(String top) {
		this.top = top;
	}

	/**
	 * Gets the scroll x.
	 *
	 * @return the scroll x
	 */
	public Boolean getScrollX() {
		return (scrollX == null) ? Boolean.FALSE : scrollX;
	}

	/**
	 * Gets the scroll x class.
	 *
	 * @return the scroll x class
	 */
	public String getScrollXClass() {
		return getScrollX() ? "linkedScrollSetX" : "";
	}

	/**
	 * Gets the scroll y class.
	 *
	 * @return the scroll y class
	 */
	public String getScrollYClass() {
		return getScrollY() ? "linkedScrollSetY" : "";
	}

	/**
	 * Sets the scroll x.
	 *
	 * @param scrollX
	 *            the new scroll x
	 */
	public void setScrollX(Boolean scrollX) {
		this.scrollX = scrollX;
	}

	/**
	 * Gets the scroll y.
	 *
	 * @return the scroll y
	 */
	public Boolean getScrollY() {
		return (scrollY == null) ? Boolean.FALSE : scrollY;
	}

	/**
	 * Sets the scroll y.
	 *
	 * @param scrollY
	 *            the new scroll y
	 */
	public void setScrollY(Boolean scrollY) {
		this.scrollY = scrollY;
	}

	/**
	 * Gets the scroll div.
	 *
	 * @param xy
	 *            the xy
	 * @return the scroll div
	 */
	public static String getScrollDiv(String xy) {
		return "<div class='sbwrapper" + xy + "'>" + SCROLLBAR1 + xy + SCROLLBAR2 + xy + SCROLLBAR3 + xy + SCROLLBAR4
				+ xy + SCROLLBAR5 + "</div>";
	}

	/**
	 * Default processing of the end tag returning EVAL_PAGE.
	 *
	 * @return EVAL_PAGE
	 * @see Tag#doEndTag
	 */
	@Override
	public int doEndTag() {
		BodyContent bc = getBodyContent();
		String body = bc.getString();
		JspWriter out = this.pageContext.getOut();
		TagElement scrollSet = new TagElement(ElementType.DIV);
		scrollSet.addClass(getScrollXClass() + SPACE + getScrollYClass() + SPACE + getParentDivClass());

		TagElement innerScrollDiv = new TagElement(ElementType.DIV);
		innerScrollDiv.addClass(INNERSCROLL_DIV);
		innerScrollDiv.html(body);

		scrollSet.append(innerScrollDiv);
		if (getScrollY()) {
			scrollSet.append(getScrollDiv(DIRECTION_Y));
		}
		if (getScrollX()) {
			scrollSet.append(getScrollDiv(DIRECTION_X));
		}

		if (this.getLeft() != null) {
			scrollSet.attr("data-left", this.getLeft());
		}
		if (this.getTop() != null) {
			scrollSet.attr("data-top", this.getTop());
		}

		try {
			out.print(scrollSet.toString());
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