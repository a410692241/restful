package com.wei.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//开启配置
@Configuration
public class SwaggleTest {


    @Bean
    public Docket createDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wei.controller"))
                //不会出现base_error_controller
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //接口文档的描述信息
                .title("接口文档")
                .description("用户端接口文档,用于构建用户端的各个接口")
                .termsOfServiceUrl("https://blog.csdn.net/qq_40354317")
                .version("1.0")
                .build();
    }

}
