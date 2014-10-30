package com.webutils.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import com.webutils.tags.TagElement.ElementType;

/**
 * The Class InstanceMenuItem.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 */
public class InstanceMenuItem extends AbstractTag {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TAG_TYPE. */
	private static final String TAG_TYPE = "instance_menu_item";


	/** The title. */
	private String title;

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	String getTitle() {
		return this.title;
	}

	/** The sel - attribute to check 'selected' condition. */
	private String sel;

	/**
	 * Sets the selected.
	 *
	 * @param sel
	 *            the new selected
	 */
	public void setSelected(String sel) {
		this.sel = sel;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	String getSelected() {
		return this.sel;
	}

	/** The id. */
	private String id;

	/**
	 * Sets the node id.
	 *
	 * @param id
	 *            the new node id
	 */
	public void setNodeId(String id) {
		this.id = id;
	}

	/**
	 * Gets the node id.
	 *
	 * @return the node id
	 */
	String getNodeId() {
		return this.id;
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
			this.id = this.getNodeId() != null ? this.getNodeId().trim() : "";
			this.title = this.getTitle() != null ? this.getTitle().trim() : "";
			TagElement li = new TagElement(ElementType.LI);
			li.addClass(this.getTagType() + SPACE + TAG);
			li.attr(NODE_ID, this.id);
			if (this.getSelected() != null) {
				li.addClass(OPEN);
			} else {
				TagElement span = new TagElement(ElementType.SPAN);
				span.attr(TITLE, this.title);
				span.addClass(TITLE);
				span.html("<a>" + this.title + "</a>");
				li.append(span);

				span = new TagElement(ElementType.SPAN);
				span.addClass(CLOSE);
				span.html("x");
				li.append(span);
			}
			out.print(li.toString());
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTagType() {
		return TAG_TYPE;
	}
}