package com.example.videoconnection.controller;

import com.example.videoconnection.model.SignalMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

//@Controller
//public class SignalingController {
//
//    @MessageMapping("/signal/{roomCode}")
//    @SendTo("/topic/signal/{roomCode}")
//    public SignalMessage signal(@DestinationVariable String roomCode, SignalMessage message) {
//        System.out.println("Отримано сигнал: " + message.getType() + " від " + message.getSender());
//        return message;
//    }
//}
@Controller
public class SignalingController {

    private final SimpMessagingTemplate messagingTemplate;

    public SignalingController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/signal/{roomCode}")
    public void signal(@DestinationVariable String roomCode, @Payload String message) {
        messagingTemplate.convertAndSend("/topic/signal/" + roomCode, message);
    }
}

