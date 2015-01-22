package com.webutils;

public abstract class AbstractUser {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUserName(String newUsername) {
		username = newUsername;
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String newPassword) {
		password = newPassword;
	}

	private boolean valid = false;

	public boolean isValid() {
		return this.valid;
	}

	public void isValid(boolean valid) {
		this.valid = valid;
	}

	private String sessionID = "";

	public String getSessionID() {
		return this.sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public long getUid() {
		return 0;
	}

	private int sessionTimeOut = 1;

	public void setSessionTimout(int minutes) {
		this.sessionTimeOut = minutes;
	}

	public int getSessionTimout() {
		return this.sessionTimeOut;
	}

	private boolean setTimeOut = false;

	public boolean isSetTimeOut() {
		return this.setTimeOut;
	}

	public abstract void auth(String userName, String passWord);
}
