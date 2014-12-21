package com.webutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**	
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Sep 10, 2014
 */
public class StompClient {
	private static SimpMessagingTemplate simpMessagingTemplate;
	private static final String USER_PREFIX = "/notify/";
	private static final String ALL_PREFIX = "/event/";

	@Autowired
	public StompClient(SimpMessagingTemplate simpMessagingTemplateTemp){
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
		simpMessagingTemplate.convertAndSendToUser(user, USER_PREFIX + eventPath, payload);
	}

	/**
	 * To send message to current user (Current user is user who has
	 * initiated the request).
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
		simpMessagingTemplate.convertAndSendToUser(WebAppContext.getUserToken(), USER_PREFIX + eventName, payload);
	}

	/**
	 * To broadcast the message, to listen to this message on
	 * client-side use: -
	 * 
	 * <p>
	 * stompClient.on('event','eventName',listener);
	 * </p>
	 * 
	 * @param eventName
	 * @param payload
	 */
	public static void sendToAll(String eventName, Object payload) {
		simpMessagingTemplate.convertAndSend(ALL_PREFIX + eventName, payload);
	}
}
