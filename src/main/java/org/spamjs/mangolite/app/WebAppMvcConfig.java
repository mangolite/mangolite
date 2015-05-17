package org.spamjs.mangolite.app;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class WebAppMvcConfig.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com"> Lalit Tanwar</a>
 * @version 1.0
 */
public class WebAppMvcConfig extends WebMvcConfigurerAdapter {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// currently not using these configs
		// registry.addResourceHandler("/css").addResourceLocations("/css/").setCachePeriod(31556926);
		// registry.addResourceHandler("/img").addResourceLocations("/img/").setCachePeriod(31556926);
		// registry.addResourceHandler("/js").addResourceLocations("/js/").setCachePeriod(31556926);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new WebAppHttpInterceptor());
	}
}
