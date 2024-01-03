package cn.iocoder.yudao.module.operation.framework.web.config;

import cn.iocoder.yudao.framework.swagger.config.YudaoSwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OperationWebConfiguration {
    @Bean
    public GroupedOpenApi operationGroupedOpenApi() {
        return YudaoSwaggerAutoConfiguration.buildGroupedOpenApi("operation");
    }
}
