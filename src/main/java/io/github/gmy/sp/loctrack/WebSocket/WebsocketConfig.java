package io.github.gmy.sp.loctrack.WebSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @authon GMY
 * @create 2020-06-24 20:23
 */


@Configuration
@EnableWebSocket
public class WebsocketConfig  implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //配置指定url
        webSocketHandlerRegistry.addHandler(new WebSocketDemoHanlder(),"/demo").setAllowedOrigins("*");
    }

}
