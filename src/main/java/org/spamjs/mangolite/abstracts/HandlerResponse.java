package org.spamjs.mangolite.abstracts;


// TODO: Auto-generated Javadoc
/**
 * The Class HandlerResponse.
 */
public class HandlerResponse extends AbstractResponse {

	/** The data. */
	private Object data;

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Instantiates a new handler response.
	 *
	 * @param data the data
	 */
	public HandlerResponse(Object data) {
		this.data = data;
	}

	/**
	 * Instantiates a new handler response.
	 */
	public HandlerResponse() {
	}

	/* (non-Javadoc)
	 * @see org.spamjs.mangolite.abstracts.AbstractResponse#getData()
	 */
	@Override
	public Object getData() {
		return this.data;
	}

}
