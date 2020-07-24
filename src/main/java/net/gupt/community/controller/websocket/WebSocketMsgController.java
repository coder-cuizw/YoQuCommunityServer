package net.gupt.community.controller.websocket;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.entity.Msg;
import net.gupt.community.entity.Result;
import net.gupt.community.mapper.MsgMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * ClassName  SocketServer <br/>
 * Description WebSocket的业务层 <br/>
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/26 11:28<br/>
 * @since JDK 1.8
 */
@Slf4j
@ServerEndpoint(value = "/msg/{receiverUid}")
@Component
public class WebSocketMsgController {

    private static ApplicationContext applicationContext;
    private MsgMapper msgMapper;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    /**
     * 用线程安全的CopyOnWriteArraySet来存放客户端连接的信息
     */
    private static final CopyOnWriteArraySet<Msg> socketServers = new CopyOnWriteArraySet<>();

    /**
     * websocket 通过session推送信息
     */
    private Session session;

    /**
     * 用户连接时触发
     *
     * @param session     会话session
     * @param receiverUid 用户的uid
     */
    @OnOpen
    public void open(Session session, @PathParam(value = "receiverUid") Integer receiverUid) {
        this.session = session;
        this.msgMapper = applicationContext.getBean(MsgMapper.class);
        Msg msg = new Msg();
        msg.setSession(session);
        msg.setReceiverUid(receiverUid);
        socketServers.add(msg);
    }

    /**
     * 收到客户端的信息时触发
     *
     * @param message 信息
     */
    @OnMessage
    public void onMessage(String message) {
        log.info(message);
        // 销毁收到的聊天信息格式 PING-PONG-ID
        final String spilt = "-";
        final String heart = "PING";
        if(message.equals(heart)){
            socketServers.forEach(client -> {
                try {
                    client.getSession().getBasicRemote().sendText("PONG");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return;
        }
        int last = message.split("-").length - 1;
        String splitStr = message.split(spilt)[last];
        if (Integer.parseInt(splitStr) > 0) {
            msgMapper.deleteMsgById(Integer.parseInt(splitStr));
        }
    }


    /**
     * 发送信息
     *
     * @param msg 信息内容
     */
    private synchronized static void sendMessage(Result<Msg> msg) {
        socketServers.forEach(client -> {
            if (msg.getData().getReceiverUid().equals(client.getReceiverUid())) {
                log.info("客户端" + client);
                try {
                    client.getSession().getBasicRemote().sendText(new Gson().toJson(msg));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * 获取在线连接数量
     *
     * @return int
     */
    public synchronized static int getOnlineNumber() {
        return socketServers.size();
    }

    /**
     * 获取在线的学号
     *
     * @return list
     */
    public synchronized static List<Integer> getOnlineUser() {
        return socketServers.stream()
                .map(Msg::getReceiverUid)
                .collect(Collectors.toList());
    }


    /**
     * 发送给指定用户
     */
    public synchronized static void sendToUser(Result<Msg> msg) {
        sendMessage(msg);
    }

    /**
     * 全局通知--新消息
     */
    public synchronized static void globalNotification(Result<?> result) {
        socketServers.forEach(client -> {
            try {
                client.getSession().getBasicRemote().sendText(new Gson().toJson(result));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 连接关闭的时候触发
     */
    @OnClose
    public void onClose() {
        socketServers.forEach(client -> {
            if (client.getSession().getId().equals(session.getId())) {
                socketServers.remove(client);
            }
        });
    }

    /**
     * 错误时候触发
     *
     * @param error 错误对像
     */
    @OnError
    public void onError(Throwable error) {
        socketServers.forEach(client -> {
            if (client.getSession().getId().equals(session.getId())) {
                socketServers.remove(client);
                error.printStackTrace();
            }
        });
    }
}
