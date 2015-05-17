package org.spamjs.mangolite.abstracts;

// TODO: Auto-generated Javadoc
/**
 * The Class WebRequest.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.0
 */
public class WebRequest {

	/**
	 * Instantiates a new web request.
	 *
	 * @param data the data
	 */
	public WebRequest(String data) {
		this.data = data;
	}

	/**
	 * Instantiates a new web request.
	 */
	public WebRequest() {
		this.data = "";
	}

	/** The data. */
	private String data;
	
	/** The namesapce. */
	private String namesapce;
	
	/** The callback id. */
	private String callbackId;

	/**
	 * Gets the namesapce.
	 *
	 * @return the namesapce
	 */
	public String getNamesapce() {
		return namesapce;
	}

	/**
	 * Sets the namesapce.
	 *
	 * @param namesapce the new namesapce
	 */
	public void setNamesapce(String namesapce) {
		this.namesapce = namesapce;
	}

	/**
	 * Gets the callback id.
	 *
	 * @return the callback id
	 */
	public String getCallbackId() {
		return callbackId;
	}

	/**
	 * Sets the callback id.
	 *
	 * @param callbackId the new callback id
	 */
	public void setCallbackId(String callbackId) {
		this.callbackId = callbackId;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(String data) {
		this.data = data;
	}

}
