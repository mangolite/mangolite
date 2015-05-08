package org.spamjs.mangolite.app;

import org.spamjs.mangolite.WebContextUtil;
import org.spamjs.mangolite.WebUtilsConstants;
import org.spamjs.mangolite.abstracts.WebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Sep 10, 2014
 */
public class StompTunnelClient {

	private static SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	public StompTunnelClient(SimpMessagingTemplate simpMessagingTemplateTemp) {
		simpMessagingTemplate = simpMessagingTemplateTemp;
	}

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
	 * @param user
	 * @param eventPath
	 * @param payload
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
	 * @param eventName
	 * @param payload
	 */
	public static void send(String eventName, Object payload) {
		simpMessagingTemplate.convertAndSendToUser(WebContextUtil.get()
				.getUserToken(), WebUtilsConstants.USER_PREFIX
				+ WebUtilsConstants.SLASH + eventName, payload);
	}

	public static void send(Object payload) {
		WebAppContext webAppConext = WebContextUtil.get();
		WebRequest webRequest = webAppConext.getWebRequest();
		if (webRequest != null) {
			simpMessagingTemplate.convertAndSend(WebUtilsConstants.CALLBACKS
					+ WebUtilsConstants.SLASH + webRequest.getNamesapce(),
					payload, webAppConext.getResponseHeader(false));
		}
	}

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
	 * @param eventName
	 * @param payload
	 */
	public static void sendToAll(String eventName, Object payload) {
		simpMessagingTemplate.convertAndSend(WebUtilsConstants.ALL_PREFIX
				+ WebUtilsConstants.SLASH + eventName, payload);
	}
}
