package cn.bianwenkai.websocket;

import cn.bianwenkai.service.DustEnvService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author BianWenKai
 * @DATE 2022/5/13 - 10:25
 **/


public class TransmitDataSchedule{

    @Resource
    private DustEnvService dustEnvService;

    public void pushData(String msg) throws InterruptedException {
        JSONObject obj = (JSONObject) JSON.parse(msg);

        String dust = (String) obj.get("dustLimit");
        String temperature =(String) obj.get("temperature");

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool((10));
        threadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("休眠10秒");
                  JSON.toJSONString(dustEnvService.getDustEnvData(dust, temperature));
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 10L,10L,TimeUnit.SECONDS);
    }

}
