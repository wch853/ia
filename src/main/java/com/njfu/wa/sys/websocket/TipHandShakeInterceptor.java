package com.njfu.wa.sys.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 系统提示WebSocket拦截器
 */
@Service
public class TipHandShakeInterceptor implements HandshakeInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TipHandShakeInterceptor.class);

    /**
     * 握手前
     *
     * @param request    the current request
     * @param response   the current response
     * @param wsHandler  the target WebSocket handler
     * @param attributes attributes from the HTTP handshake to associate with the WebSocket
     *                   session; the provided attributes are copied, the original map is not used.
     * @return whether to proceed with the handshake ({@code true}) or abort ({@code false})
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // TODO 验证用户是否有权限收到该类消息

        // 获取HttpSession
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpSession session = servletRequest.getServletRequest().getSession();

        // 在握手前验证是否存在用户信息，不存在时拒绝连接
        String username = (String) session.getAttribute("username");

        /*
        if (null == username) {
            log.error("Invalid User!");
            return false;
        } else {
            // 将用户信息放入WebSocketSession中
            attributes.put("username", username);
            // httpSessionId用于唯一确定连接客户端的身份
            attributes.put("httpSessionId", session.getId());
            attributes.put("host", request.getRemoteAddress().getHostString());
            return true;
        }
         */

        return true;
    }

    /**
     * 握手后
     *
     * @param request   the current request
     * @param response  the current response
     * @param wsHandler the target WebSocket handler
     * @param exception an exception raised during the handshake, or {@code null} if none
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {

    }
}
