package org.spamjs.mangolite.abstracts;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractUser.
 */
public abstract class AbstractUser {
	
	/** The name. */
	private String name = "GUEST";

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** The username. */
	private String username;

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the user name.
	 *
	 * @param newUsername the new user name
	 */
	public void setUserName(String newUsername) {
		username = newUsername;
	}

	/** The password. */
	private String password;

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param newPassword the new password
	 */
	public void setPassword(String newPassword) {
		password = newPassword;
	}

	/** The valid. */
	private boolean valid = false;

	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid() {
		return this.valid;
	}

	/**
	 * Checks if is valid.
	 *
	 * @param valid the valid
	 */
	public void isValid(boolean valid) {
		this.valid = valid;
	}

	/** The session id. */
	private String sessionID = "";

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public String getSessionID() {
		return this.sessionID;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionID the new session id
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public long getUid() {
		return 0;
	}

	/** The session time out. */
	private int sessionTimeOut = 1;

	/**
	 * Sets the session timout.
	 *
	 * @param minutes the new session timout
	 */
	public void setSessionTimout(int minutes) {
		this.sessionTimeOut = minutes;
	}

	/**
	 * Gets the session timout.
	 *
	 * @return the session timout
	 */
	public int getSessionTimout() {
		return this.sessionTimeOut;
	}

	/**
	 * Auth.
	 *
	 * @param userName the user name
	 * @param passWord the pass word
	 */
	public abstract void auth(String userName, String passWord);
}
