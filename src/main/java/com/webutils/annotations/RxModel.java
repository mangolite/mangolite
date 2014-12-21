package com.webutils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a mailto:lalit.tanwar07@gmail.com> Lalit Tanwar</a>
 * @created 16-Dec-2014
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RxModel {
	public enum ModelType {
		USER,
	}

	public static final String defaultString = "";

	ModelType value();

}