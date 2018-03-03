package com.njfu.ia.listener.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 处理socket连接
 */
public class Connections {

    private static final Logger LOGGER = LoggerFactory.getLogger(Connections.class);

    /**
     * socket连接集合
     */
    private static Set<Socket> CURRENT_CONNECT = new CopyOnWriteArraySet<>();

    /**
     * 新增socket连接
     *
     * @param socket socket
     */
    public static void join(Socket socket) {
        CURRENT_CONNECT.add(socket);
        LOGGER.info("new socket established, ip: {}, remote port: {}", socket.getInetAddress(), socket.getPort());
    }

    /**
     * 移除socket连接
     *
     * @param socket socket
     */
    public static void kick(Socket socket) {
        CURRENT_CONNECT.remove(socket);
    }

    /**
     * 获取当前socket连接集合
     *
     * @return socket set
     */
    public static Set<Socket> list() {
        return CURRENT_CONNECT;
    }
}
