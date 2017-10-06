package com.njfu.wa.sys.websocket;

import com.njfu.wa.sys.domain.util.JsonUtil;
import com.njfu.wa.sys.domain.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * 成功连接WebSocket后执行
     *
     * @param session session
     * @throws Exception Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
        log.info("session {} open", session.getId());
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

        log.error("session {} error, errorMessage: {}", session.getId(), exception.getMessage());
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
        String res = JsonUtil.toJsonString(result);

        if (null != res) {
            for (WebSocketSession webSocketSession : webSocketSessions) {
                // TODO 识别权限发送消息
                webSocketSession.sendMessage(new TextMessage(res));
            }
        } else {
            log.error("报警消息为空，广播报警消息失败！");
        }
    }
}
