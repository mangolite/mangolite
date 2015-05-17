package org.spamjs.mangolite.abstracts;

// TODO: Auto-generated Javadoc
/**
 * The Class WebSockRequest.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.0
 */
public class WebSockRequest extends WebRequest  {
	
	/**
	 * Instantiates a new web sock request.
	 *
	 * @param data the data
	 */
	public WebSockRequest(String data) {
		super(data);
	}

	/** The user token. */
	private String userToken;

	/**
	 * Gets the user token.
	 *
	 * @return the user token
	 */
	public String getUserToken() {
		return userToken;
	}

	/**
	 * Sets the user token.
	 *
	 * @param userToken the new user token
	 */
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

}
