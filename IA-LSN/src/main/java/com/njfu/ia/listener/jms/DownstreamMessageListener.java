package com.njfu.ia.listener.jms;

import com.njfu.ia.listener.connection.Connections;
import com.njfu.ia.listener.domain.DownstreamRet;
import com.njfu.ia.listener.utils.Constants;
import com.njfu.ia.listener.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 下行数据监听器，用于将下行数据按通信协议打包
 */
public class DownstreamMessageListener implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownstreamMessageListener.class);

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                LOGGER.info("socket service consume message: {}", text);
                DownstreamRet downstreamRet = JsonUtils.toBean(text, DownstreamRet.class);
                String data = downstreamRet.getData();
                StringBuilder sb = new StringBuilder();
                sb.append((char) Constants.START_FLAG)
                        .append(data)
                        .append((byte) data.length())
                        .append((char) Constants.END_FLAG);
                // 广播命令消息
                Connections.broadcast(sb.toString().getBytes());
            } catch (Exception e) {
                LOGGER.error("socket service downstream Exception", e);
            }
        }
    }
}
