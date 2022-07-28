package com.qf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 初始化配置Swagger
 * @author zhaojian
 */
@Configuration   //相当于spring配置文件 <beans>标签
@EnableSwagger2  //开启swagger
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket getDocket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                //.paths(Predicates.or(PathSelectors.regex("/api2/.*")))//  过滤  该路径下的请求生成RESTful api
                .build();
    }

    /**
     * 接口文档的一些基本信息
     * @return
     */
    private ApiInfo apiInfo(){
        //1  2
        return new ApiInfoBuilder()
                .title("千锋 - 北科电商后台管理系统")//大标题
                .description("电商后台管理系统")//详细描述
                .version("2.0")//版本
                .contact(new Contact("java", "http://itqf.com", "123456@qq.com"))//作者
                .license("The Apache License, Version 2.0")//许可证信息
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")//许可证地址
                .build();

    }

    /**
     * 放行swagger的静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }


}
