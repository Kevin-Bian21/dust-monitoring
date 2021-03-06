package cn.bianwenkai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author BianWenKai
 * @DATE 2022/4/30 - 20:18
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //配置Swagger的Docket的Bean实例
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //使其只扫描有 @RestController 注解的类
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

    //配置Swagger信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("BianWenKai","https://www.bianwenkai.cn","1467295546@qq.com");
        return new ApiInfo(
                "Dust Monitor API Document",
                "粉尘监测系统API文档",
                "v1.0",
                "",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }

}