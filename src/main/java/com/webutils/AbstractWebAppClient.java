package com.webutils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.utils.JsonUtil;
import com.utils.Log;
import com.webutils.annotations.AppProperties;
import com.webutils.annotations.HandlerScan;
import com.webutils.annotations.ModelScan;

/**
 * @author <a mailto:lalit.tanwar07@gmail.com> Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Aug 19, 2014
 */
public class AbstractWebAppClient {

	private static final Log LOG = new Log();

	public static HandlerManager handlerManager = new HandlerManager();
	public static ModelManager modelManager = new ModelManager();
	private static WebAppProperties properties = new WebAppProperties();

	public static WebAppProperties getProperties() {
		return properties;
	}

	{
		try {
			InputStream ini = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							this.getClass().getAnnotation(AppProperties.class)
									.value());
			properties.scan(ini);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	{

		handlerManager.scan(this.getClass().getAnnotation(HandlerScan.class)
				.value());
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
	public HandlerResponse invokeHanldler(String handlerName,
			String actionName, String data) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		AbstractHandler handeler = handlerManager.getHandlerMapping().get(
				handlerName);
		Method md = handlerManager.getActionMapping().get(
				handlerName + "_" + actionName);
		if (md != null) {
			HandlerResponse resp = new HandlerResponse();

			Class<?>[] paramClazzes = md.getParameterTypes();
			Object arglist[] = new Object[paramClazzes.length];
			for (int i = 0; i < paramClazzes.length; i++) {
				Class<?> paramClazz = paramClazzes[i];
				if (HandlerResponse.class.isAssignableFrom(paramClazz)) {
					arglist[i] = resp;
				} else if (AbstractUser.class.isAssignableFrom(paramClazz)) {
					arglist[i] = WebAppContext.getUser();
				} else {
					arglist[i] = JsonUtil.fromJson(data, paramClazz);
				}
			}
			resp.setData(md.invoke(handeler, arglist));
			return resp;
		} else {
			return null;
		}
	}
}
