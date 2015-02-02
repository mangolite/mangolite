package com.webutils.abstracts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webutils.WebContextUtil;
import com.webutils.WebUtilsConstants;
import com.webutils.app.WebAppClient;

public abstract class AbstractDataController {

	@RequestMapping(value = "/webappindex/**", method = RequestMethod.GET)
	public String index(ModelAndView modelAndView) throws IOException {
		//appClient.getProperties().getAppContext();
		WebAppClient.getContext().getUser().isValid(false);
		return "index";
	}

	@RequestMapping(value = "/data/{handlerName}/{actionName}", method = RequestMethod.POST)
	@ResponseBody
	public HandlerResponse data(String data,
			@PathVariable("handlerName") String handlerName,
			@PathVariable("actionName") String actionName,
			HttpServletRequest req) throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// WebAppContext.setRequestContext(message);
		return WebAppClient.invokeHanldler(handlerName, actionName, data);
	}
	
	
	@MessageMapping("/action/wsr/{handlerName}/{actionName}")
	public AbstractResponse wrappedRequest(@Payload WebRequest webSockRequest,
			@DestinationVariable("handlerName") String handlerName,
			@DestinationVariable("actionName") String actionName,
			Message<Object> message, SimpMessageHeaderAccessor headerAccessor)
			throws Exception {
		// String authedSender = principal.getName();
		// AppClient.getContext().setWebSockRequest(webSockRequest);
		// message.getHeaders()
		String sessionId = headerAccessor.getSessionId();
		AbstractUser user = (AbstractUser) headerAccessor.getSessionAttributes().get(WebUtilsConstants.CURRENT_SESSION_USER);
		WebAppClient.getContext().setUser(user);
		WebContextUtil.get().setWebRequest(webSockRequest);
		System.out.println("-----" + sessionId);

		return WebAppClient.invokeHanldler(handlerName, actionName,
				webSockRequest);
	}

	@SubscribeMapping("/cb/{callbackNamespace}")
	public void init(SimpMessageHeaderAccessor accessor,
			@DestinationVariable("callbackNamespace") String callbackNamespace) {
		// String user = accessor.getFirstNativeHeader("userid");

		accessor.getSessionId();
		System.out.println("-----");
		// messagingTemplate.convertAndSendToUser(user, "/monitor", getOps());
	}
}
