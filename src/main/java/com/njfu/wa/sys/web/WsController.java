package com.njfu.wa.sys.web;

import com.njfu.wa.sys.web.socket.SysWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class WsController {

    @Autowired
    private SysWebSocketHandler sysWebSocketHandler;

    private CopyOnWriteArraySet<WebSocketSession> webSocketSessions = SysWebSocketHandler.webSocketSessions;

    @RequestMapping("/ws")
    public String ws() {
        return "wsTest";
    }

    @RequestMapping("/test")
    public @ResponseBody
    String test() throws IOException {
        for (WebSocketSession webSocketSession : webSocketSessions) {
            sysWebSocketHandler.handleTextMessage(webSocketSession, "testTrig");
        }
        return "test";
    }
}
