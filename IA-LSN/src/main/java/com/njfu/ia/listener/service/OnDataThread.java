package com.njfu.ia.listener.service;

import com.njfu.ia.listener.common.StaticProperty;
import com.njfu.ia.listener.connection.Connections;
import com.njfu.ia.listener.mq.AmqProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * socket服务端接收线程，处理上行数据
 */
public class OnDataThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnDataThread.class);

    /**
     * socket连接
     */
    private Socket socket;

    /**
     * 读入消息缓存标志
     */
    private boolean allowBufRead = Boolean.FALSE;

    /**
     * 消息缓存
     */
    private StringBuilder messageBuffer = new StringBuilder();

    /**
     * 字节缓存队列，用于暂存
     */
    private Queue<Byte> bufferQueue = new ConcurrentLinkedQueue<>();

    public OnDataThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 加入socket列表
        Connections.join(socket);
        try (InputStream inputStream = socket.getInputStream()) {
            // 用于保存读入的字节
            byte[] data;
            while (true) {
                try {
                    int length = inputStream.available();
                    if (length > 0) {
                        data = new byte[length];
                        // 读入字节数组
                        int read = inputStream.read(data);
                        if (read > 0) {
                            this.analysisMessage(data);
                        }
                    }
                } catch (IOException e) {
                    Connections.kick(socket);
                    LOGGER.error("read stream Exception: {}", e.getMessage());
                }
            }
        } catch (Exception e) {
            Connections.kick(socket);
            LOGGER.error("on data Thread Exception: {}", e.getMessage(), e);
        }
    }

    /**
     * 解析数据
     *
     * @param data data
     */
    private void analysisMessage(byte[] data) {
        for (byte single : data) {
            if (StaticProperty.START_FLAG == single) {
                // 读到开始标志，打开读入缓存标志
                allowBufRead = Boolean.TRUE;
                continue;
            } else if (StaticProperty.END_FLAG == single) {
                // 读到结束标志，关闭读入缓存标志
                allowBufRead = Boolean.FALSE;
                int msgBufLen = messageBuffer.length();
                if (msgBufLen > 0) {
                    // 读到结束标志且消息缓存不为空，验证检校位
                    byte validMsgLen = (byte) (messageBuffer.charAt(msgBufLen - 1) - 1);
                    // 截取有效消息体
                    String validMsg = messageBuffer.substring(0, msgBufLen - 1);
                    if (validMsgLen == validMsg.length()) {
                        // 验证为有效消息
                        AmqProducer.send(StaticProperty.AMQ_UPLOAD_DATA, validMsg);
                    }
                }
            }

            if (allowBufRead) {
                messageBuffer.append((char) single);
            }
        }
    }
}
