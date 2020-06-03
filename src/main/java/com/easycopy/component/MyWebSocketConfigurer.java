package com.easycopy.component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Component
@EnableWebSocket
public class MyWebSocketConfigurer implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(textHandler(), "/text").setAllowedOrigins("*");
        registry.addHandler(binaryHandler(), "/binary").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler textHandler() {
        return new MyTextHandler();
    }

    @Bean
    public WebSocketHandler binaryHandler() {
        return new MyBinaryHandler();
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(500000);
        container.setMaxBinaryMessageBufferSize(500000);
        return container;
    }
}
