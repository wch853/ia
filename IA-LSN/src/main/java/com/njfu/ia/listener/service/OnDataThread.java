package com.njfu.ia.listener.service;

import com.njfu.ia.listener.connection.Connections;
import com.njfu.ia.listener.mq.AmqProducer;
import com.njfu.ia.listener.property.StaticProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * socket服务端接收线程，处理上行数据
 */
public class OnDataThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnDataThread.class);

    /**
     * socket连接对象
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

    public OnDataThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // socket异常重读记录
        int tryReadCount = 0;
        try (InputStream inputStream = socket.getInputStream()) {
            // 加入socket列表
            Connections.join(socket);
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
                    LOGGER.error("read stream Exception", e);
                    if (++tryReadCount == StaticProperty.DEFAULT_TRY_READ_COUNT_LIMIT) {
                        // 达到异常重读数量上限，抛出异常给外层处理
                        throw new Exception(e);
                    }
                }
            }
        } catch (Exception e) {
            Connections.kick(socket);
            LOGGER.error("on data Thread Exception", e);
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
                        // 验证为有效消息，推送队列
                        AmqProducer.send(StaticProperty.AMQ_UPLOAD_DATA, validMsg);
                        // 消息缓存清空
                        messageBuffer.setLength(0);
                    }
                }
            }

            if (allowBufRead) {
                messageBuffer.append((char) single);
            }
        }
    }
}
