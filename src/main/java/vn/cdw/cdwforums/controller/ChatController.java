package vn.cdw.cdwforums.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import vn.cdw.cdwforums.entity.Chat;

@Controller
public class ChatController {


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public Chat sendMessage(@Payload Chat chatMessage) {
        return chatMessage;
    }
 
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public Chat addUser(@Payload Chat chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    
    @GetMapping("/chat")
    public String Chat() {
    	return "chat";
    }

}
