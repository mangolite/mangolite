package org.spamjs.mangolite.app;

import java.util.HashMap;
import java.util.Map;

import org.spamjs.mangolite.WebUtilsConstants;
import org.spamjs.mangolite.abstracts.AbstractUser;
import org.spamjs.mangolite.abstracts.WebRequest;

public class WebAppContext {

	private AbstractUser user;
	private Boolean wasValid;
	private String userToken;
	private WebRequest webRequest;

	public WebRequest getWebRequest() {
		return webRequest;
	}

	public void setWebRequest(WebRequest webRequest) {
		this.webRequest = webRequest;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public AbstractUser getUser() {
		return user;
	}

	public void setUser(AbstractUser user) {
		this.wasValid = user.isValid();
		this.user = user;
	}

	public Boolean getWasValid() {
		return wasValid;
	}

	public void setWasValid(Boolean wasValid) {
		this.wasValid = wasValid;
	}

	public String getUserToken() {
		return userToken;
	}

	public Boolean wasUserValidated() {
		return !this.wasValid && user.isValid();
	}

	public Boolean wasUserInValidated() {
		return this.wasValid && !user.isValid();
	}

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
