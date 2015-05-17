package org.spamjs.mangolite.app;

import org.spamjs.mangolite.WebContextUtil;
import org.spamjs.mangolite.WebUtilsConstants;
import org.spamjs.mangolite.abstracts.WebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

// TODO: Auto-generated Javadoc
/**
 * The Class StompTunnelClient.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.0
 */
public class StompTunnelClient {

	/** The simp messaging template. */
	private static SimpMessagingTemplate simpMessagingTemplate;

	/**
	 * Instantiates a new stomp tunnel client.
	 *
	 * @param simpMessagingTemplateTemp the simp messaging template temp
	 */
	@Autowired
	public StompTunnelClient(SimpMessagingTemplate simpMessagingTemplateTemp) {
		simpMessagingTemplate = simpMessagingTemplateTemp;
	}

	/**
	 * Gets the simp messaging template.
	 *
	 * @return the simp messaging template
	 */
	public static SimpMessagingTemplate getSimpMessagingTemplate() {
		return simpMessagingTemplate;
	}

	/**
	 * To send message to specified user
	 * 
	 * <p>
	 * stompClient.on('eventName',listener);
	 * </p>
	 *
	 * @param user the user
	 * @param eventPath the event path
	 * @param payload the payload
	 */
	public static void sendToUser(String user, String eventPath, Object payload) {
		simpMessagingTemplate.convertAndSendToUser(user,
				WebUtilsConstants.USER_PREFIX + WebUtilsConstants.SLASH
						+ eventPath, payload);
	}

	/**
	 * To send message to current user (Current user is user who has initiated
	 * the request).
	 * 
	 * To listen to this message on client-side use: -
	 * <p>
	 * stompClient.on('eventName',listener);
	 * </p>
	 *
	 * @param eventName the event name
	 * @param payload the payload
	 */
	public static void send(String eventName, Object payload) {
		simpMessagingTemplate.convertAndSendToUser(WebContextUtil.get()
				.getUserToken(), WebUtilsConstants.USER_PREFIX
				+ WebUtilsConstants.SLASH + eventName, payload);
	}

	/**
	 * Send.
	 *
	 * @param payload the payload
	 */
	public static void send(Object payload) {
		WebAppContext webAppConext = WebContextUtil.get();
		WebRequest webRequest = webAppConext.getWebRequest();
		if (webRequest != null) {
			simpMessagingTemplate.convertAndSend(WebUtilsConstants.CALLBACKS
					+ WebUtilsConstants.SLASH + webRequest.getNamesapce(),
					payload, webAppConext.getResponseHeader(false));
		}
	}

	/**
	 * Done.
	 *
	 * @param payload the payload
	 */
	public static void done(Object payload) {
		WebAppContext webAppConext = WebContextUtil.get();
		WebRequest webRequest = webAppConext.getWebRequest();
		if (webRequest != null) {
			simpMessagingTemplate.convertAndSend(WebUtilsConstants.CALLBACKS
					+ WebUtilsConstants.SLASH + webRequest.getNamesapce(),
					payload, webAppConext.getResponseHeader(true));
		}
	}

	/**
	 * To broadcast the message, to listen to this message on client-side use: -
	 * 
	 * <p>
	 * stompClient.on('event','eventName',listener);
	 * </p>
	 *
	 * @param eventName the event name
	 * @param payload the payload
	 */
	public static void sendToAll(String eventName, Object payload) {
		simpMessagingTemplate.convertAndSend(WebUtilsConstants.ALL_PREFIX
				+ WebUtilsConstants.SLASH + eventName, payload);
	}
}
