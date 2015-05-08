package org.spamjs.mangolite;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.0
 * @since Aug 19, 2014
 * 
 * 
 *        Spring-specific {@link org.springframework.context.MessageSource}
 *        implementation that accesses resource bundles using specified
 *        basenames, participating in the Spring
 *        {@link org.springframework.context.ApplicationContext}'s resource
 *        loading.
 * 
 *        dfferentia:easy access to ReloadableResourceBundleMessageSource for
 *        all Handlers and Abstract Classes
 */
public class MessageResource {

	private static MessageResource messageResource = new MessageResource();

	/**
	 * Use Spring to 'inject' the message resources into this messageSource This
	 * can be used by any class that need access to messageResource property
	 * files
	 * 
	 * @param messageSource
	 */

	private ReloadableResourceBundleMessageSource messageSource;

	private MessageResource() {
		// This constructor is required to instantiate property by bean
		// Dont throw new IllegalStateException();
	}

	public void setMessageSource(
			ReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
		if (messageResource.getMessageSource() == null) {
			messageResource.setMessageSource(messageSource);
		}

	}

	public ReloadableResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	public static ReloadableResourceBundleMessageSource get() {
		return messageResource.getMessageSource();
	}

}
