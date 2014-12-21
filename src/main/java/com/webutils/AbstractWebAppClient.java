package com.webutils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.reflections.Reflections;

import com.utils.JsonUtil;
import com.utils.Log;
import com.webutils.annotations.ActionHandler;
import com.webutils.annotations.HandlerAction;
import com.webutils.annotations.HandlerScan;
import com.webutils.annotations.ModelScan;
import com.webutils.annotations.RxModel;
import com.webutils.annotations.RxModel.ModelType;

/**
 * @author <a mailto:lalit.tanwar07@gmail.com> Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Aug 19, 2014
 */
public class AbstractWebAppClient {

	private static final Log LOG = new Log();
	private static Map<String, AbstractHandler> handlerMapping = new ConcurrentHashMap<String, AbstractHandler>();
	private static Map<String, Method> actionMapping = new ConcurrentHashMap<String, Method>();

	private static Constructor<?> userConstructor;
	static {
		try {
			userConstructor = AbstractUser.class.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			LOG.error(e);
		}
	}

	{

		String handlerScanPath = this.getClass()
				.getAnnotation(HandlerScan.class).value();
		// System.out.println("+++++"+handlerScanPath);

		Reflections reflections = new Reflections(handlerScanPath);
		Set<Class<?>> annotated = reflections
				.getTypesAnnotatedWith(ActionHandler.class);
		for (Class<?> annotatedOne : annotated) {
			String cName = annotatedOne.getAnnotation(ActionHandler.class)
					.name();
			Constructor<?> ctor;
			try {
				ctor = annotatedOne.getConstructor();
				handlerMapping.put(cName, (AbstractHandler) ctor.newInstance());
				Method[] methods = annotatedOne.getMethods();
				for (Method method : methods) {
					HandlerAction annos = method
							.getAnnotation(HandlerAction.class);
					if (annos != null) {
						actionMapping.put(cName + "_" + annos.name(), method);
					}
				}
			} catch (NoSuchMethodException e) {
				LOG.error(e);
			} catch (SecurityException e) {
				LOG.error(e);
			} catch (InstantiationException e) {
				LOG.error(e);
			} catch (IllegalAccessException e) {
				LOG.error(e);
			} catch (IllegalArgumentException e) {
				LOG.error(e);
			} catch (InvocationTargetException e) {
				LOG.error(e);
			}
		}

		String modelScanPath = this.getClass().getAnnotation(ModelScan.class)
				.value();
		Reflections modelReflections = new Reflections(modelScanPath);
		Set<Class<?>> modelAnnotated = modelReflections
				.getTypesAnnotatedWith(RxModel.class);
		for (Class<?> annotatedOne : modelAnnotated) {
			ModelType cName = annotatedOne.getAnnotation(RxModel.class).value();
			try {
				switch (cName) {
				case USER: {
					userConstructor = annotatedOne.getConstructor();
				}
					break;
				default:
					break;
				}
			} catch (NoSuchMethodException e) {
				LOG.error(e);
			} catch (SecurityException e) {
				LOG.error(e);
			} catch (IllegalArgumentException e) {
				LOG.error(e);
			}
		}
	}

	public static AbstractUser getUser() {
		try {
			return (AbstractUser) userConstructor.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			LOG.error(e);
		}
		return null;
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
	public Object invokeHanldler(String handlerName, String actionName,
			WebSockRequest message) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		AbstractHandler handeler = handlerMapping.get(handlerName);
		Method md = actionMapping.get(handlerName + "_" + actionName);
		if (md != null) {
			Class<?> clazz = md.getParameterTypes()[0];
			return md.invoke(handeler,
					JsonUtil.fromJson(message.getData(), clazz));
		} else {
			return null;
		}
	}

}
