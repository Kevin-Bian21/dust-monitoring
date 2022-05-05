package cn.bianwenkai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author BianWenKai
 * @DATE 2022/5/4 - 21:40
        **/

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {



    //配置静态资源处理拦截器，如果访问的路径是 addResourceHandler() 中的的话，那就映射到访问本地的 addResourceLocations() 的参数的路径中
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**","/swagger-ui/**")
                .addResourceLocations("classpath:/static/","classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

}
