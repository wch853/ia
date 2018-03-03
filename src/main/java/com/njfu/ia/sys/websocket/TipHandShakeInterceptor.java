package com.njfu.ia.sys.websocket;

import com.njfu.ia.sys.utils.CommonConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * 系统提示WebSocket拦截器
 */
@Service
public class TipHandShakeInterceptor implements HandshakeInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TipHandShakeInterceptor.class);

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
        Subject subject;
        try {
            // 用户经过认证才能获取websocket连接
            subject = SecurityUtils.getSubject();
            // 验证websocket推送权限
            if (subject.isPermitted(CommonConstants.WARN_PERM)) {
                attributes.put(CommonConstants.WARN_PERM, Boolean.TRUE);
            }
            return subject.isAuthenticated();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Boolean.FALSE;
        }
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
