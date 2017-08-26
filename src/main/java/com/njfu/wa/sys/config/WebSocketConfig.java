package com.njfu.wa.sys.config;

import com.njfu.wa.sys.web.socket.SysWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 注册WebSocket处理器
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * 配置WebSocket处理器和路径
     *
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(sysWebSocketHandler(), "/sysHandler");
    }

    @Bean
    public SysWebSocketHandler sysWebSocketHandler() {
        return new SysWebSocketHandler();
    }
}
