package cn.iocoder.yudao.server.config;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration(proxyBeanMethods = false)
public class CustomMyBatisPlusConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init(){
        JacksonTypeHandler.setObjectMapper(objectMapper);
    }
}
