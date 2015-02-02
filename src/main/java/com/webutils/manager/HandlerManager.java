package com.webutils.manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.reflections.Reflections;

import com.utils.Log;
import com.webutils.abstracts.AbstractHandler;
import com.webutils.annotations.ActionHandler;
import com.webutils.annotations.HandlerAction;

/**
 * @author <a mailto:lalit.tanwar07@gmail.com> Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Aug 19, 2014
 */
public class HandlerManager {
	private static final Log LOG = new Log();
	private Map<String, AbstractHandler> handlerMapping = new ConcurrentHashMap<String, AbstractHandler>();
	private Map<String, Method> actionMapping = new ConcurrentHashMap<String, Method>();
	public void scan(String handlerScanPath) {
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
				getHandlerMapping().put(cName,
						(AbstractHandler) ctor.newInstance());
				Method[] methods = annotatedOne.getMethods();
				for (Method method : methods) {
					HandlerAction annos = method
							.getAnnotation(HandlerAction.class);
					if (annos != null) {
						getActionMapping().put(cName + "_" + annos.name(),
								method);
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
	}

	public Map<String, AbstractHandler> getHandlerMapping() {
		return handlerMapping;
	}

	public void setHandlerMapping(Map<String, AbstractHandler> handlerMapping) {
		this.handlerMapping = handlerMapping;
	}

	public Map<String, Method> getActionMapping() {
		return actionMapping;
	}

	public void setActionMapping(Map<String, Method> actionMapping) {
		this.actionMapping = actionMapping;
	}
}
