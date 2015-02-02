package com.webutils.manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;

import com.utils.Log;
import com.webutils.abstracts.AbstractUser;
import com.webutils.annotations.RxModel;
import com.webutils.annotations.RxModel.ModelType;

public class ModelManager {
	private static final Log LOG = new Log();

	private static Constructor<?> userConstructor;
	static {
		try {
			userConstructor = AbstractUser.class.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			LOG.error(e);
		}
	}

	public AbstractUser getUser() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return (AbstractUser) userConstructor.newInstance();
	}
	
	public void scan(String modelScanPath) {
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
}
