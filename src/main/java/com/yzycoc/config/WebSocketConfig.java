package com.yzycoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @program: cscocutil -> com.yzycoc.config
 * @description: WebSocket 配置类
 * @author: XinDa2020
 * @create: 2021/5/24 14:14:46
 * @Version 1.0
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter()
    {
        return new ServerEndpointExporter();
    }
}
