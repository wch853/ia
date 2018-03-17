package com.njfu.ia.sys.websocket;

import com.njfu.ia.sys.utils.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * 系统提示WebSocket配置
 */
@Configuration
@EnableWebSocket
public class TipWebSocketConfig implements WebSocketConfigurer {

    @Resource
    private TipHandler tipHandler;

    @Resource
    private TipHandShakeInterceptor tipHandShakeInterceptor;

    /**
     * 注册WebSocket处理器
     * 配置处理器、拦截器、允许域、SockJs支持
     *
     * @param registry registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        String origin = Constants.WS_ORIGIN;

        registry.addHandler(tipHandler, "/tip/handler")
                .addInterceptors(tipHandShakeInterceptor)
                .setAllowedOrigins(origin);

        // 当浏览器不支持WebSocket，使用SockJs支持
        registry.addHandler(tipHandler, "/sockjs/tip/handler")
                .addInterceptors(tipHandShakeInterceptor)
                .setAllowedOrigins(origin)
                .withSockJS();
    }
}
