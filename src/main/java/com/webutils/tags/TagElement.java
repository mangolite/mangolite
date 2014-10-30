package com.webutils.tags;

/**
 * This class is to create HTML DOM element in way very close to jQuery, it will
 * be used to create tag.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 11, 2014
 */
public class TagElement {

	/** The Constant TAG_OPEN. */
	protected static final String TAG_OPEN = "<";

	/** The Constant TAG_CLOSE. */
	protected static final String TAG_CLOSE = ">";

	/** The Constant EMPTY. */
	protected static final String EMPTY = "";

	/** The Constant SLASH. */
	protected static final String SLASH = "/";

	/** The Constant S_QUOTE. */
	protected static final String S_QUOTE = "'";

	/** The Constant EQUALS. */
	protected static final String EQUALS = "=";

	/** The Constant CLASS. */
	protected static final String CLASS = "class";

	/** The Constant SPACE. */
	protected static final String SPACE = " ";

	/**
	 * The Enum ElementType.
	 */
	public static enum ElementType {

		/** The div. */
		DIV("div", true),
		/** The input. */
		INPUT("input", false),
		/** The span. */
		SPAN("span", true),
		/** The ul. */
		UL("ul", true),
		/** The li. */
		LI("li", true),
		/** The label. */
		LABEL("label", true),
		/** The script. */
		SCRIPT("script", true);

		/** The has body. */
		private boolean hasBody;

		/** The text. */
		private String text;

		/** The element start. */
		private String elementStart;

		/**
		 * Gets the element start.
		 *
		 * @return the element start
		 */
		public String getElementStart() {
			return this.elementStart;
		}

		/** The element end. */
		private String elementEnd;

		/**
		 * Gets the end string.
		 *
		 * @param innerHTML
		 *            the inner html
		 * @return the end string
		 */
		public String getEndString(String innerHTML) {
			if (this.hasBody) {
				return TAG_CLOSE + innerHTML + this.elementEnd;
			} else {
				return this.elementEnd;
			}
		}

		/**
		 * Instantiates a new element type.
		 *
		 * @param text
		 *            the text
		 * @param hasBody
		 *            the has body
		 */
		ElementType(String text, boolean hasBody) {
			this.text = text;
			this.hasBody = hasBody;
			this.elementStart = TAG_OPEN + this.text;
			if (hasBody) {
				this.elementEnd = TAG_OPEN + SLASH + this.text + TAG_CLOSE;
			} else {
				this.elementEnd = SLASH + TAG_CLOSE;
			}
		}

		/**
		 * Gets the text.
		 *
		 * @return the text
		 */
		public String getText() {
			return this.text;
		}

		/**
		 * From string.
		 *
		 * @param text
		 *            the text
		 * @return the element type
		 */
		public static ElementType fromString(String text) {
			if (text != null) {
				for (ElementType b : ElementType.values()) {
					if (text.equalsIgnoreCase(b.text)) {
						return b;
					}
				}
			}
			return DIV;
		}
	}

	/** The inner html. */
	private String innerHTML;

	/** The class string. */
	private String classString;

	/**
	 * Gets the class string.
	 *
	 * @return the class string
	 */
	public String getClassString() {
		return this.classString;
	}

	/**
	 * Sets the class string.
	 *
	 * @param classString
	 *            the new class string
	 */
	public void setClassString(String classString) {
		this.classString = classString;
	}

	/** The attr string. */
	private String attrString;

	/** The element type. */
	private ElementType elementType;

	/**
	 * Gets the element type.
	 *
	 * @return the element type
	 */
	public ElementType getElementType() {
		return this.elementType;
	}

	/**
	 * Sets the element type.
	 *
	 * @param elementType
	 *            the new element type
	 */
	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}

	/**
	 * Instantiates a new tag element.
	 */
	public TagElement() {
		this.elementType = ElementType.DIV;
		initTag();
	}

	/**
	 * Instantiates a new tag element.
	 *
	 * @param elementType
	 *            the element type
	 */
	public TagElement(ElementType elementType) {
		this.elementType = elementType;
		initTag();
	}

	/**
	 * Instantiates a new tag element.
	 *
	 * @param elementTypeText
	 *            the element type text
	 */
	public TagElement(String elementTypeText) {
		this.elementType = ElementType.fromString(elementTypeText);
		initTag();
	}

	/**
	 * Inits the tag.
	 */
	public void initTag() {
		this.innerHTML = EMPTY;
		this.classString = EMPTY;
		this.attrString = EMPTY;
	}

	/**
	 * Adds the class.
	 *
	 * @param clsName
	 *            the cls name
	 */
	public void addClass(String clsName) {
		this.classString = this.classString + SPACE + clsName;
	}

	/**
	 * Attr.
	 *
	 * @param attrName
	 *            the attr name
	 * @param attrValue
	 *            the attr value
	 */
	public void attr(String attrName, String attrValue) {
		this.attrString = this.attrString + SPACE + attrName + EQUALS + S_QUOTE + attrValue + S_QUOTE;
	}

	/**
	 * Attr.
	 *
	 * @param attrName
	 *            the attr name
	 * @param attrValue
	 *            the attr value
	 */
	public void attr(String attrName, long attrValue) {
		this.attrString = this.attrString + SPACE + attrName + EQUALS + S_QUOTE + attrValue + S_QUOTE;
	}

	/**
	 * Attr.
	 *
	 * @param attrString
	 *            the attr string
	 */
	public void attr(String attrString) {
		this.attrString = this.attrString + SPACE + attrString;
	}

	/**
	 * Html.
	 *
	 * @param htmlText
	 *            the html text
	 */
	public void html(String htmlText) {
		this.innerHTML = htmlText;

	}

	/**
	 * Html.
	 *
	 * @param tagElement
	 *            the tag element
	 * @return the tag element
	 */
	public TagElement html(TagElement tagElement) {
		this.innerHTML = tagElement.toString();
		return this;
	}

	/**
	 * Append.
	 *
	 * @param domString
	 *            the dom string
	 */
	public void append(String domString) {
		this.innerHTML = this.innerHTML + domString;

	}

	/**
	 * Append.
	 *
	 * @param tagElement
	 *            the tag element
	 * @return the tag element
	 */
	public TagElement append(TagElement tagElement) {
		this.innerHTML = this.innerHTML + tagElement.toString();
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return (this.elementType.getElementStart() + SPACE + CLASS + EQUALS + S_QUOTE + this.classString + S_QUOTE
				+ SPACE + this.attrString + SPACE + this.elementType.getEndString(this.innerHTML));
	}
}
