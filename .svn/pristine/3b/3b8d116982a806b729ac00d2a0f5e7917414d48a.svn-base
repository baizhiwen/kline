package org.ktrade.kline.kline.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by Nono on 2017/6/20.
 */
@Configuration
@EnableWebSocketMessageBroker   //开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    //表示注册STOMP协议的节点
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //这一行代码用来注册STOMP协议节点，同时指定使用SockJS协议。并指定映射的URL /endpointVR
        stompEndpointRegistry.addEndpoint("/endpointWS").withSockJS();
    }
    @Override
    //用来配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //registry.enableSimpleBroker("/topic");
        registry.enableSimpleBroker("/topic","/user");  //这句表示在topic和user这两个域上可以向客户端发消息；
        registry.setUserDestinationPrefix("/user");    //这句表示给指定用户发送（一对一）的主题前缀是“/user”;
        registry.setApplicationDestinationPrefixes("/app");     //这句表示客户端向服务端发送时的主题上面需要加"/app"作为前缀；
    }
}
