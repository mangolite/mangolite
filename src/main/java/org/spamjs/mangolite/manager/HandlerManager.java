package org.spamjs.mangolite.manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.reflections.Reflections;
import org.spamjs.mangolite.abstracts.AbstractHandler;
import org.spamjs.mangolite.annotations.ActionHandler;
import org.spamjs.mangolite.annotations.HandlerAction;
import org.spamjs.utils.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class HandlerManager.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com"> Lalit Tanwar</a>
 * @version 1.0
 */
public class HandlerManager {
	
	/** The Constant LOG. */
	private static final Log LOG = new Log();
	
	/** The handler mapping. */
	private Map<String, AbstractHandler> handlerMapping = new ConcurrentHashMap<String, AbstractHandler>();
	
	/** The action mapping. */
	private Map<String, Method> actionMapping = new ConcurrentHashMap<String, Method>();
	
	/**
	 * Scan.
	 *
	 * @param handlerScanPath the handler scan path
	 */
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

	/**
	 * Gets the handler mapping.
	 *
	 * @return the handler mapping
	 */
	public Map<String, AbstractHandler> getHandlerMapping() {
		return handlerMapping;
	}

	/**
	 * Sets the handler mapping.
	 *
	 * @param handlerMapping the handler mapping
	 */
	public void setHandlerMapping(Map<String, AbstractHandler> handlerMapping) {
		this.handlerMapping = handlerMapping;
	}

	/**
	 * Gets the action mapping.
	 *
	 * @return the action mapping
	 */
	public Map<String, Method> getActionMapping() {
		return actionMapping;
	}

	/**
	 * Sets the action mapping.
	 *
	 * @param actionMapping the action mapping
	 */
	public void setActionMapping(Map<String, Method> actionMapping) {
		this.actionMapping = actionMapping;
	}
}
