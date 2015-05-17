package org.spamjs.mangolite.app;

import java.util.HashMap;
import java.util.Map;

import org.spamjs.mangolite.WebUtilsConstants;
import org.spamjs.mangolite.abstracts.AbstractUser;
import org.spamjs.mangolite.abstracts.WebRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class WebAppContext.
 */
public class WebAppContext {

	/** The user. */
	private AbstractUser user;
	
	/** The was valid. */
	private Boolean wasValid;
	
	/** The user token. */
	private String userToken;
	
	/** The web request. */
	private WebRequest webRequest;

	/**
	 * Gets the web request.
	 *
	 * @return the web request
	 */
	public WebRequest getWebRequest() {
		return webRequest;
	}

	/**
	 * Sets the web request.
	 *
	 * @param webRequest the new web request
	 */
	public void setWebRequest(WebRequest webRequest) {
		this.webRequest = webRequest;
	}

	/**
	 * Sets the user token.
	 *
	 * @param userToken the new user token
	 */
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public AbstractUser getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(AbstractUser user) {
		this.wasValid = user.isValid();
		this.user = user;
	}

	/**
	 * Gets the was valid.
	 *
	 * @return the was valid
	 */
	public Boolean getWasValid() {
		return wasValid;
	}

	/**
	 * Sets the was valid.
	 *
	 * @param wasValid the new was valid
	 */
	public void setWasValid(Boolean wasValid) {
		this.wasValid = wasValid;
	}

	/**
	 * Gets the user token.
	 *
	 * @return the user token
	 */
	public String getUserToken() {
		return userToken;
	}

	/**
	 * Was user validated.
	 *
	 * @return the boolean
	 */
	public Boolean wasUserValidated() {
		return !this.wasValid && user.isValid();
	}

	/**
	 * Was user in validated.
	 *
	 * @return the boolean
	 */
	public Boolean wasUserInValidated() {
		return this.wasValid && !user.isValid();
	}

	/**
	 * Gets the response header.
	 *
	 * @param complete the complete
	 * @return the response header
	 */
	public Map<String, Object> getResponseHeader(boolean complete) {
		Map<String, Object> header = new HashMap<String, Object>();
		if (this.webRequest != null) {
			header.put(WebUtilsConstants.CALLBACK_ID_KEY,
					this.webRequest.getCallbackId());
		}
		header.put(WebUtilsConstants.CALLBACK_COMPLETE_KEY, complete);
		return header;
	}

}
