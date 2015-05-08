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

/**
 * @author <a mailto:lalit.tanwar07@gmail.com> Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Aug 19, 2014
 */
public class WebAppClient {

	private static final Log LOG = new Log();

	public static HandlerManager handlerManager = new HandlerManager();
	public static ModelManager modelManager = new ModelManager();

	private static WebAppProperties staticProperties;

	@Autowired
	public WebAppClient(WebAppProperties myWebAppProperties,
			StompTunnelClient stompTunnelClient) {
		staticProperties = myWebAppProperties;
		stompClient = stompTunnelClient;
	}

	public static WebAppProperties getWebAppProperties() {
		return staticProperties;
	}

	private static StompTunnelClient stompClient;

	public void setStompClient(StompTunnelClient stompClientInstance) {
		stompClient = stompClientInstance;
	}

	public static StompTunnelClient getStompClient() {
		return stompClient;
	}

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

	public static AbstractUser getUser() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		return (AbstractUser) modelManager.getUser();
	}

	/**
	 * @param handlerName
	 * @param actionName
	 * @param message
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
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

	public static HandlerResponse invokeHanldler(String handlerName,
			String actionName, WebRequest webRequest)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		return invokeHanldler(handlerName, actionName, webRequest.getData());
	}

}