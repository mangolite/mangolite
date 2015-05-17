package org.spamjs.mangolite;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageResource.
 *
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

	/** The message resource. */
	private static MessageResource messageResource = new MessageResource();

	/**
	 * Use Spring to 'inject' the message resources into this messageSource This
	 * can be used by any class that need access to messageResource property
	 * files.
	 *
	 */

	private ReloadableResourceBundleMessageSource messageSource;

	/**
	 * Instantiates a new message resource.
	 */
	private MessageResource() {
		// This constructor is required to instantiate property by bean
		// Dont throw new IllegalStateException();
	}

	/**
	 * Sets the message source.
	 *
	 * @param messageSource the new message source
	 */
	public void setMessageSource(
			ReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
		if (messageResource.getMessageSource() == null) {
			messageResource.setMessageSource(messageSource);
		}

	}

	/**
	 * Gets the message source.
	 *
	 * @return the message source
	 */
	public ReloadableResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * Gets the.
	 *
	 * @return the reloadable resource bundle message source
	 */
	public static ReloadableResourceBundleMessageSource get() {
		return messageResource.getMessageSource();
	}

}
