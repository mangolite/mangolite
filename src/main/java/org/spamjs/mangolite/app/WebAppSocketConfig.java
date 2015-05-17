package org.spamjs.mangolite.app;

import org.spamjs.mangolite.WebUtilsConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

// TODO: Auto-generated Javadoc
/**
 * WebSocketConfig sets url-prefix for socket communication.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com"> Lalit Tanwar</a>
 * @version 1.0
 */
public class WebAppSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer#configureMessageBroker(org.springframework.messaging.simp.config.MessageBrokerRegistry)
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/queue", "/event", WebUtilsConstants.CALLBACKS);
		// config.enableSimpleBroker("/event", "/notify", "/user");
		 config.setUserDestinationPrefix("/user/");
		///config.setApplicationDestinationPrefixes("/action");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry)
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/tunnel").withSockJS().setInterceptors(getWebAppHandshakeInterceptor());
	}
	
	/**
	 * Gets the web app handshake interceptor.
	 *
	 * @return the web app handshake interceptor
	 */
	@Bean
	public WebAppHandshakeInterceptor getWebAppHandshakeInterceptor() {
		return new WebAppHandshakeInterceptor();
	}

}