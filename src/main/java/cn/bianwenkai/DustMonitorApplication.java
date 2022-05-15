package cn.bianwenkai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAsync  //开启异步注解功能
@EnableScheduling   //开启定时注解功能
@SpringBootApplication
@EnableTransactionManagement
public class DustMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DustMonitorApplication.class, args);
    }

}
