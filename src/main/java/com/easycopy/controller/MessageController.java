package com.easycopy.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class MessageController {

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public String send(String msg) {
        System.out.println("RECEIVED:: " + msg);
        return msg.toUpperCase();
    }
}