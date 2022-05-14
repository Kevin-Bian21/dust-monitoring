package cn.bianwenkai.websocket;

import cn.bianwenkai.service.DustEnvService;
import cn.bianwenkai.service.Impl.DustEnvServiceImpl;
import cn.bianwenkai.utils.ParserJwt;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
     * 用户id ，通过解析token获取
     * message 在用户没有进行表单提交时默认使用该数据
     */
    private String accountId="";
    private String token ="";
    private String message = "{\"dustLimit\":\"50\",\"temperatureLimit\":\"30\"}";
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(8);

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
        String accountId = ParserJwt.decoding(token).getId();
        this.accountId = accountId;


        //存储websocket连接，存在内存中，若有同一个用户同时在线，也会存，不会覆盖原有记录
        webSocketMap.put(accountId, this);


        logger.info("Open a WebSocket by " + ParserJwt.decoding(token).getSubject());

        try {
            //sendMessage(JSON.toJSONString("连接成功"));
            init();
        } catch (Exception e) {
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
    public void onMessage(String message) throws Exception {

       // sendMessage(JSON.toJSONString("收到消息"));

        this.message = message;
        //新建定时线程池

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
    public void sendMessage(String message){
        //需要使用同步机制，否则多并发时会因阻塞而报错
//        synchronized(this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.error("发送给用户 ["+this.accountId +"] 的消息出现错误",e.getMessage());

            }
        }
//    }


    private DustEnvService dustEnvService = new DustEnvServiceImpl();


//    @PostConstruct
    public  void init(){
        //新建定时线程池
        Task task = new Task();
        //每10秒向前端推送一次数据
        scheduledExecutorService.scheduleAtFixedRate(task,10,10, TimeUnit.SECONDS);
    }

    //定时自动推送数据
    class Task implements Runnable {
        @Override
        public void run() {
            JSONObject obj = (JSONObject) JSON.parse(message);
            String dust = (String) obj.get("dustLimit");
            String temperature =(String) obj.get("temperatureLimit");
            Map<String, Object> map = new HashMap<>();
            map.put("data",dustEnvService.getDustEnvData(dust, temperature));
            sendMessage(JSON.toJSONString(map));
        }
    }
}