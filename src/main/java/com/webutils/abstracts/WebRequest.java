package com.webutils.abstracts;

/**
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Jul 17, 2014
 */
public class WebRequest {

	public WebRequest(String data) {
		this.data = data;
	}

	public WebRequest() {
		this.data = "";
	}

	private String data;
	private String namesapce;
	private String callbackId;

	public String getNamesapce() {
		return namesapce;
	}

	public void setNamesapce(String namesapce) {
		this.namesapce = namesapce;
	}

	public String getCallbackId() {
		return callbackId;
	}

	public void setCallbackId(String callbackId) {
		this.callbackId = callbackId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
