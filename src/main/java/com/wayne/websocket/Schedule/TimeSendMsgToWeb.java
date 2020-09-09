package com.wayne.websocket.Schedule;

import com.wayne.websocket.Utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

@Configuration
public class TimeSendMsgToWeb {

    @Autowired
    private SimpMessagingTemplate templeMsg;

    //记录当前登陆的用户数量
    private CopyOnWriteArraySet<String> onlineUser=new CopyOnWriteArraySet<>();

    //每一秒发送数据往web端
    @Scheduled(fixedRate = 1000)
    public void sentMsgToWeb(){
        //系统的内核数量
        int processors=Runtime.getRuntime().availableProcessors();
        //系统的最大内存
        long maxMemory=Runtime.getRuntime().maxMemory();
        //系统的空闲内存
        long freeMemory=Runtime.getRuntime().freeMemory();
        StringBuilder sb=new StringBuilder();
        sb.append("系统的内核数量:").append(processors).append("系统的最大内存:").append(maxMemory).
        append("系统的空闲内存:").append(freeMemory).append(":当前系统时间:").append("在线人数:").append(onlineUser.size()).append("人,:"+DateTimeUtil.dateToStr(new Date()));
        templeMsg.convertAndSend("/server/serverToWeb",sb.toString());
    }

    @Scheduled(fixedRate = 1000)
    public void sentMsgToPointWeb(){
        templeMsg.convertAndSendToUser("1","/serverToPointWeb",System.currentTimeMillis());
    }

    //添加人数
    public void AddPeople(String sessionId){
        onlineUser.add(sessionId);
    }
    //减少人数
    public void removePeople(String sessionId){
        onlineUser.remove(sessionId);
    }
}
