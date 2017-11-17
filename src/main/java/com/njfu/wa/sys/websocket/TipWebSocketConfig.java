package com.njfu.wa.sys.websocket;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${websocket.origin}")
    private String origin;

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

        registry.addHandler(tipHandler, "/tipHandler")
                .addInterceptors(tipHandShakeInterceptor)
                .setAllowedOrigins(origin);

        // 当浏览器不支持WebSocket，使用SockJs支持
        registry.addHandler(tipHandler, "/sockjs-tipHandler")
                .addInterceptors(tipHandShakeInterceptor)
                .setAllowedOrigins(origin)
                .withSockJS();
    }
}
