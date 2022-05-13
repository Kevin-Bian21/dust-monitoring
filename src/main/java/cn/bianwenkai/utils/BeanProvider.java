package cn.bianwenkai.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author BianWenKai
 * @DATE 2022/5/13 - 17:50
 **/

@Component
public class BeanProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    public static <T> T  getBean(Class<T> requiredType) throws BeansException {
        return context.getBean(requiredType);
    }
}
