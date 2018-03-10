package com.njfu.ia.listener;

import com.njfu.ia.listener.property.StaticProperty;
import com.njfu.ia.listener.service.OnDataThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 侦听socket连接侦听器
 */
public class Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    public static void main(String[] args) {
        // 在指定端口建立socket服务
        try (ServerSocket serverSocket = new ServerSocket(StaticProperty.LISTEN_PORT)) {
            LOGGER.info("server socket service established, listen port {}", StaticProperty.LISTEN_PORT);
            while (true) {
                // 阻塞状态，等待socket连接
                Socket socket = serverSocket.accept();
                // 获取socket连接，新起服务线程
                new OnDataThread(socket).start();
            }
        } catch (Exception e) {
            LOGGER.info("Server Listener Exception: {}", e.getMessage());
        }
    }
}
