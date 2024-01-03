package cn.iocoder.yudao.module.business.framework.web.config;

import cn.iocoder.yudao.framework.swagger.config.YudaoSwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class BusinessWebConfiguration {
    @Bean
    public GroupedOpenApi businessGroupedOpenApi() {
        return YudaoSwaggerAutoConfiguration.buildGroupedOpenApi("business");
    }
}
