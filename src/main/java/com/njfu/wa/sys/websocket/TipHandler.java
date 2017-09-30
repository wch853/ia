package com.njfu.wa.sys.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.wa.sys.domain.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 系统提示WebSocket处理器
 */
@Service
public class TipHandler extends AbstractWebSocketHandler {

    /**
     * 用于存放所有WebSocket连接
     */
    private static final CopyOnWriteArraySet<WebSocketSession> webSocketSessions = new CopyOnWriteArraySet<>();

    private static final Logger log = LoggerFactory.getLogger(TipHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 成功连接WebSocket后执行
     *
     * @param session session
     * @throws Exception Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
    }

    /**
     * 处理WebSocketMessage transport error
     *
     * @param session   session
     * @param exception exception
     * @throws Exception Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 对于异常连接，关闭并从webSocketSessions中移除
        if (session.isOpen()) {
            session.close();
        }
        webSocketSessions.remove(session);

        log.error("session {} error, errorMessage: {}", exception.getMessage());
    }

    /**
     * 在两端WebSocket connection都关闭或transport error发生后执行
     *
     * @param session     session
     * @param closeStatus closeStatus
     * @throws Exception Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        webSocketSessions.remove(session);
    }

    /**
     * 广播报警消息
     * @param result result
     * @throws Exception Exception
     */
    public void broadcastWarnTip(Result result) throws Exception {
        // 将Result对象转为json字符串
        String res = objectMapper.writeValueAsString(result);

        for (WebSocketSession webSocketSession : webSocketSessions) {
            webSocketSession.sendMessage(new TextMessage(res));
        }
    }
}
