package com.webutils;

public class HandlerResponse extends AbstractResponse {

	private Object data;

	public void setData(Object data) {
		this.data = data;
	}

	public HandlerResponse(Object data) {
		this.data = data;
	}

	public HandlerResponse() {
	}

	@Override
	public Object getData() {
		return this.data;
	}

}
