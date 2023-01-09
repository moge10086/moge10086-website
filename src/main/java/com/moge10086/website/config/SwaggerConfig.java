package com.moge10086.website.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sq
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi customApi(){
        String[] paths = { "/**" };
        String[] packagedToMatch = { "com.moge10086.website.api.controller" };
        return GroupedOpenApi.builder().group("用户模块")
                .pathsToMatch(paths)
//                .addOperationCustomizer((operation, handlerMethod) -> {
//                    return operation.addParametersItem(new HeaderParameter().name("groupCode").example("测试")
//                            .description("集团code").schema(new StringSchema()._default("BR").name("groupCode").description("集团code")));
//                })
                .packagesToScan(packagedToMatch).build();
    }
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                .title("moge10086-website")
                .version("1.0")
                .description( "moge10086-website 接口信息")
                .termsOfService("https://www.moge10086.top")
                .license(new License().name("Apache 2.0")
                .url("https://www.moge10086.top")));
    }


}