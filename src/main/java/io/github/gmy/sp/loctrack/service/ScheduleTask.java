package io.github.gmy.sp.loctrack.service;

import io.github.gmy.sp.loctrack.DTO.GpsDTO;
import io.github.gmy.sp.loctrack.WebSocket.WebSocketDemoHanlder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

/**
 * @authon GMY
 * @create 2020-06-24 20:43
 */

@Service
public class ScheduleTask {
    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    GpsDTO location;
    @Scheduled(cron = "* * 0/1 * * ?")
    public void sendMessage(){
        String message = "你好";

        Map<String, WebSocketSession> map = WebSocketDemoHanlder.getUserIdSessionMap();

        WebSocketSession session = map.get("gmy");
        if(session != null){
            try {
                session.sendMessage(new TextMessage(message));
            }catch (Exception e){
                logger.error("定时任务异常:{}",e);
            }
        }
    }

}
