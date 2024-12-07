package com.pioriko.ms_restaurante.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {



    //configuramos los agentes
    @Override
    public void configureMessageBroker(MessageBrokerRegistry  config) {
        config.enableSimpleBroker("/topic"); //para la salida de mensajes o datos
        config.setApplicationDestinationPrefixes("/app"); // para la entrada de mensajes o datos
    }

    //configuramos el punto de conexion
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // configurampos la url de acceso para los clientes
        .setAllowedOrigins("*");  //configuracion de coors

    }
}
