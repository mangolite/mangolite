package org.spamjs.mangolite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface ActionHandler.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com"> Lalit Tanwar</a>
 * 
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ActionHandler {

	/** The Constant defaultString. */
	public static final String defaultString = "";

	/**
	 * Name.
	 *
	 * @return the string
	 */
	String name() default defaultString;

}