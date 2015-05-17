package org.spamjs.mangolite.app;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.spamjs.mangolite.WebUtilsConstants;
import org.spamjs.utils.Log;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

// TODO: Auto-generated Javadoc
/**
 * The Class WebAppHandshakeInterceptor.
 */
public class WebAppHandshakeInterceptor implements HandshakeInterceptor {

	/** The Constant LOG. */
	private static final Log LOG = new Log();

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.server.HandshakeInterceptor#beforeHandshake(org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse, org.springframework.web.socket.WebSocketHandler, java.util.Map)
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest()
					.getSession(false);
			if (session != null) {
				attributes.put(WebUtilsConstants.SESESSION_ATTR,
						session.getId());
				attributes.put(WebUtilsConstants.CURRENT_SESSION_USER, session
						.getAttribute(WebUtilsConstants.CURRENT_SESSION_USER));
				LOG.info("Scoekct Handshake:Copying HTTP Session");
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.server.HandshakeInterceptor#afterHandshake(org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse, org.springframework.web.socket.WebSocketHandler, java.lang.Exception)
	 */
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
	}
}
