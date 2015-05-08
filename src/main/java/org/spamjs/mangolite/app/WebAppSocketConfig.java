package org.spamjs.mangolite.app;

import org.spamjs.mangolite.WebUtilsConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * WebSocketConfig sets url-prefix for socket communication
 * 
 * 
 * @author <a mailto:lalit.tanwar07@gmail.com> Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Aug 19, 2014
 */
public class WebAppSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/queue", "/event", WebUtilsConstants.CALLBACKS);
		// config.enableSimpleBroker("/event", "/notify", "/user");
		 config.setUserDestinationPrefix("/user/");
		///config.setApplicationDestinationPrefixes("/action");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/tunnel").withSockJS().setInterceptors(getWebAppHandshakeInterceptor());
	}
	
	@Bean
	public WebAppHandshakeInterceptor getWebAppHandshakeInterceptor() {
		return new WebAppHandshakeInterceptor();
	}

}