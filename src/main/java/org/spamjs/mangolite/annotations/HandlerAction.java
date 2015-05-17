package org.spamjs.mangolite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface HandlerAction.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com"> Lalit Tanwar</a>
 * 
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HandlerAction {
	
	/** The Constant defaultString. */
	public static final String defaultString = "";

	/**
	 * Name.
	 *
	 * @return the string
	 */
	String name() default defaultString;

}