package com.njfu.wa.sys.web;

import com.njfu.wa.sys.web.socket.SysWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class WsController {

    @Autowired
    private SysWebSocketHandler sysWebSocketHandler;

    private CopyOnWriteArraySet<WebSocketSession> webSocketSessions = SysWebSocketHandler.webSocketSessions;

    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @RequestMapping("/ws")
    public String ws() {
        return "wsTest";
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleTest() throws IOException {
        for (WebSocketSession webSocketSession : webSocketSessions) {
            sysWebSocketHandler.handleTextMessage(webSocketSession, sdf.format(new Date()));
        }
    }
}
