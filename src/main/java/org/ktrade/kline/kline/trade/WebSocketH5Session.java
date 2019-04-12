package org.ktrade.kline.kline.trade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/webSocketH5")
//@ServerEndpoint(value="/websocketH5Session",configurator=GetHttpSessionConfigurator.class)
@Component
public class WebSocketH5Session {
  
    private static int onlineCount = 0;
    private Gson gson = new GsonBuilder().create();
  
    public  static CopyOnWriteArraySet<WebSocketH5Session> webSocketSet = new CopyOnWriteArraySet<>();
    public  static CopyOnWriteArraySet<WebSocketH5Session> buySellListLast5webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;



    @OnOpen
    public void onOpen (Session session ){

        this.session = session;
        String type=session.getRequestParameterMap().get("type").get(0);
        if("history".equals(type)) {
            webSocketSet.add(this);
        }
        if("last5".equals(type)){
            buySellListLast5webSocketSet.add(this);
        }
        addOnlineCount();  
        System.out.println("有新链接加入!当前在线人数为" + getOnlineCount());  
    }

    /*@OnOpen
    public void onOpen(Session session,EndpointConfig config) {
        this.httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        //System.out.println( httpSession.getAttribute("name"));
        //sessionMap.put(session.getId(), session);
        this.httpSessionId=session.getId();

        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新链接加入!当前在线人数为" + getOnlineCount());
    }
*/
    @OnClose
    public void onClose (){  
        webSocketSet.remove(this);
        buySellListLast5webSocketSet.remove(this);
        try{
            subOnlineCount();
            System.out.println("有一链接关闭!当前在线人数为" + getOnlineCount());
        }catch(Exception e){

        }
    }

    @OnError
    public void onError(Throwable e, Session session){
        e.printStackTrace();
        System.out.println("有异常!");
    }
  
    @OnMessage
    public void onMessage (String message, Session session) throws IOException {
        System.out.println("来自客户端的消息:" + message);  
        // 群发消息
        //TODO: 压力测试时关闭
       /* for ( WebSocketH5Session item : webSocketSet ){
                item.sendHistoryMessage("服务器返回："+message);
        }  */
    }

    /**
     * 推送历史成交记录
     * @param message
     * @throws IOException
     */
    public void sendHistoryMessage(Trade trade) throws IOException {

        String msg=gson.toJson(trade);
        for ( WebSocketH5Session item : webSocketSet ){
            item.session.getBasicRemote().sendText(msg);
        }
    }
    /**
     * 推送历史成交记录
     * @param message
     * @throws IOException
     */
    public void sendLast5Message(List<Trade>buyList,List<Trade>sellList) throws IOException {
        List<List<Trade>> arr= new ArrayList<List<Trade>>() ;
        arr.add(buyList);
        arr.add(sellList);
        String msg=gson.toJson(arr);
        for ( WebSocketH5Session item : buySellListLast5webSocketSet ){
            item.session.getBasicRemote().sendText(msg);
        }
    }

    public static synchronized  int getOnlineCount (){  
        return WebSocketH5Session.onlineCount;
    }  
  
    public static synchronized void addOnlineCount (){
        WebSocketH5Session.onlineCount++;
    }  
  
    public static synchronized void subOnlineCount (){
        WebSocketH5Session.onlineCount--;
    }  
  
}  