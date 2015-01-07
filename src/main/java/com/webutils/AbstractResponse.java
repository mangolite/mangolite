package com.webutils;

public abstract class AbstractResponse {

	public abstract Object getData();

	private Boolean success = true;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
