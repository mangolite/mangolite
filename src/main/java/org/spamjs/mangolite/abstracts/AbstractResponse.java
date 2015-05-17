package org.spamjs.mangolite.abstracts;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractResponse.
 */
public abstract class AbstractResponse {

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public abstract Object getData();

	/** The success. */
	private Boolean success = true;

	/**
	 * Gets the success.
	 *
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 *
	 * @param success the new success
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
