package cn.bianwenkai.websocket;

import cn.bianwenkai.utils.ParserJwt;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author BianWenKai
 * @DATE 2022/5/12 - 15:03
 **/

@Component
@ServerEndpoint(value = "/ws/{token}") //响应路径为 /ws/{token} 的连接请求
public class WebSocketServer {
    /**
     * 静态变量，用来记录当前在线连接数
     */
    private static int onlineCount = 0;

    /**
     * concurrent 包的线程安全Set，用来存放每个客户端对应的 myWebSocket对象
     * 根据 用户id 来获取对应的 WebSocketServer 示例
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 用户id
     */
    private String accountId="";
    private String token ="";

    /**
     * logger
     */
    private static Logger logger = LogManager.getLogger(WebSocketServer.class.getName());

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     * @param token 用户id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws Exception {

        this.session = session;
        this.token = token;

        //设置超时，同httpSession
        session.setMaxIdleTimeout(3600000);

        //解析令牌，拿取用户信息
        String accountId = ParserJwt.tokenToOut(token).getId();
        this.accountId = accountId;
        System.out.println("================" +accountId);

        //存储websocket连接，存在内存中，若有同一个用户同时在线，也会存，不会覆盖原有记录
        webSocketMap.put(accountId, this);
        System.out.println("++++++++++++++");
        logger.info("Open a WebSocket by " + ParserJwt.tokenToOut(token).getSubject());

        try {
            sendMessage(JSON.toJSONString("连接成功"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("websocket IO异常！！！！");
        }


    }

    /**
     * 客户端连接关闭时触发
     **/
    @OnClose
    public void onClose(Session session) {
        //客户端连接关闭时，移除map中存储的键值对
        webSocketMap.remove(accountId);
        logger.info("close a websocket, concurrentHashMap remove sessionId= {}"+accountId);
    }

    /**
     * 接收到客户端消息时触发
     */
    @OnMessage
    public void onMessage(String message) {

        logger.info("receive a message from client id={},msg={}",accountId, message);
    }

    /**
     * 连接发生异常时候触发
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("Error while websocket. ", error);
    }
    /**
     * 实现服务器主动推送
     * @param message 消息字符串
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        //需要使用同步机制，否则多并发时会因阻塞而报错
        synchronized(this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.error("发送给用户 ["+this.accountId +"] 的消息出现错误",e.getMessage());
                throw e;
            }
        }
    }

}