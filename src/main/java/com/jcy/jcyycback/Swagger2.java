package com.jcy.jcyycback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/6 下午 11:52:27
 * @description：swagger
 * @modified By：
 */
@Configuration
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return (new Docket(DocumentationType.SWAGGER_2))
                .apiInfo(apiInfo())
                .select()

                .apis(RequestHandlerSelectors.basePackage("com.jcy.jcyycback.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder())
                .title("YC-API")
                        .contact(new Contact("Panyu", "", "2547926632@qq.com"))
                        .version("1.0")
                        .description("API")
                                .build();
    }
}
