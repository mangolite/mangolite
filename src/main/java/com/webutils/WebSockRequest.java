/**
 * Copyright (c) - 2014, Dion Global Solutions. Company confidential.
 */
package com.webutils;

/**
 * @author <a mailto:lalit.tanwar@dionglobal.com> Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Jul 17, 2014
 */
public class WebSockRequest {
	private String userToken;

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

}
