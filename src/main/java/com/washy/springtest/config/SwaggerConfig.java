package com.washy.springtest.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * @Author zbinyds
 * @Create 2022/09/13 17:22
 * <p>
 * 项目集成swagger2,进行接口测试
 */

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     * swagger文档相关配置
     *
     * @return Docket
     */
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                // 全局 header 配置
                .globalRequestParameters(
                        List.of(
                                new springfox.documentation.builders.RequestParameterBuilder()
                                        .name("token")
                                        .description("token")
                                        .in(ParameterType.HEADER)
                                        .required(false)
                                        .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                                        .build()))
                // swagger 存储 token
                .securitySchemes(List.of(new ApiKey("token", "token", "header")))
                .securityContexts(List.of(context()));
    }

    /**
     * api基本信息配置
     *
     * @return ApiInfo
     */
    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("江阴艾力特信息管理系统-接口文档")
                .description("项目API文档示例")
                .version("1.0")
                .contact(new Contact("zbinyds", "https://alite.com", "alite@qq.com"))
                .build();
    }

    /**
     * 全局token验证配置
     *
     * @return SecurityContext
     */
    private SecurityContext context() {
        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopes = {scope};
        SecurityReference tokenReference = new SecurityReference("token", scopes);
        return SecurityContext.builder()
                .securityReferences(List.of(tokenReference))
                .build();
    }
}
