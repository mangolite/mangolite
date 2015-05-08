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

public class WebAppHandshakeInterceptor implements HandshakeInterceptor {

	private static final Log LOG = new Log();

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

	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
	}
}
