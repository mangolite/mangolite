package com.webutils;

public class HandlerResponse extends AbstractResponse {

	private Object data;

	public HandlerResponse(Object data) {
		this.data = data;
	}

	@Override
	public Object getData() {
		return this.data;
	}

}
