package org.spamjs.mangolite.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.spamjs.mangolite.WebContextUtil;
import org.spamjs.mangolite.abstracts.AbstractHandler;
import org.spamjs.mangolite.abstracts.AbstractUser;
import org.spamjs.mangolite.abstracts.HandlerResponse;
import org.spamjs.mangolite.abstracts.WebRequest;
import org.spamjs.mangolite.annotations.HandlerScan;
import org.spamjs.mangolite.annotations.ModelScan;
import org.spamjs.mangolite.manager.HandlerManager;
import org.spamjs.mangolite.manager.ModelManager;
import org.spamjs.utils.JsonUtil;
import org.spamjs.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;

// TODO: Auto-generated Javadoc
/**
 * The Class WebAppClient.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com"> Lalit Tanwar</a>
 * @version 1.0
 */
public class WebAppClient {

	/** The Constant LOG. */
	private static final Log LOG = new Log();

	/** The handler manager. */
	public static HandlerManager handlerManager = new HandlerManager();
	
	/** The model manager. */
	public static ModelManager modelManager = new ModelManager();

	/** The static properties. */
	private static WebAppProperties staticProperties;

	/**
	 * Instantiates a new web app client.
	 *
	 * @param myWebAppProperties the my web app properties
	 * @param stompTunnelClient the stomp tunnel client
	 */
	@Autowired
	public WebAppClient(WebAppProperties myWebAppProperties,
			StompTunnelClient stompTunnelClient) {
		staticProperties = myWebAppProperties;
		stompClient = stompTunnelClient;
	}

	/**
	 * Gets the web app properties.
	 *
	 * @return the web app properties
	 */
	public static WebAppProperties getWebAppProperties() {
		return staticProperties;
	}

	/** The stomp client. */
	private static StompTunnelClient stompClient;

	/**
	 * Sets the stomp client.
	 *
	 * @param stompClientInstance the new stomp client
	 */
	public void setStompClient(StompTunnelClient stompClientInstance) {
		stompClient = stompClientInstance;
	}

	/**
	 * Gets the stomp client.
	 *
	 * @return the stomp client
	 */
	public static StompTunnelClient getStompClient() {
		return stompClient;
	}

	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	public static WebAppContext getContext() {
		return WebContextUtil.get();
	}

	{

		LOG.info("Scanning Handlers...");
		handlerManager.scan(this.getClass().getAnnotation(HandlerScan.class)
				.value());
		LOG.info("Scanning Models...");
		modelManager.scan(this.getClass().getAnnotation(ModelScan.class)
				.value());
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public static AbstractUser getUser() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		return (AbstractUser) modelManager.getUser();
	}

	/**
	 * Invoke hanldler.
	 *
	 * @param handlerName the handler name
	 * @param actionName the action name
	 * @param data the data
	 * @return the handler response
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public static HandlerResponse invokeHanldler(String handlerName,
			String actionName, String data) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		AbstractHandler handeler = handlerManager.getHandlerMapping().get(
				handlerName);
		Method md = handlerManager.getActionMapping().get(
				handlerName + "_" + actionName);
		HandlerResponse resp = new HandlerResponse();
		if (md != null) {
			Class<?>[] paramClazzes = md.getParameterTypes();
			Object arglist[] = new Object[paramClazzes.length];
			for (int i = 0; i < paramClazzes.length; i++) {
				Class<?> paramClazz = paramClazzes[i];
				if (HandlerResponse.class.isAssignableFrom(paramClazz)) {
					arglist[i] = resp;
				} else if (AbstractUser.class.isAssignableFrom(paramClazz)) {
					arglist[i] = WebContextUtil.get().getUser();
				} else {
					arglist[i] = JsonUtil.fromJson(data, paramClazz);
				}
			}
			Object respObject = md.invoke(handeler, arglist);
			if (respObject != null) {
				resp.setData(respObject);
			}
		} 
		StompTunnelClient.done(resp);
		return resp;
	}

	/**
	 * Invoke hanldler.
	 *
	 * @param handlerName the handler name
	 * @param actionName the action name
	 * @param webRequest the web request
	 * @return the handler response
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public static HandlerResponse invokeHanldler(String handlerName,
			String actionName, WebRequest webRequest)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		return invokeHanldler(handlerName, actionName, webRequest.getData());
	}

}