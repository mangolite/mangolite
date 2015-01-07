package com.webutils;

/**
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Jul 17, 2014
 */
public class WebSockRequest extends AbstractWebRequest  {
	public WebSockRequest(String data) {
		super(data);
	}

	private String userToken;

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

}
