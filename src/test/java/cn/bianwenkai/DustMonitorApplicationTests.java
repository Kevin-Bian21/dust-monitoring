package cn.bianwenkai;

import cn.bianwenkai.entity.DustEnvironment;
import cn.bianwenkai.mapper.DustEnvDataMapper;
import cn.bianwenkai.utils.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DustMonitorApplicationTests {

    @Resource
    private DustEnvDataMapper dustEnvDataMapper;

    @Autowired
    private MailService mailService;

    @Test
    void contextLoads() {

        String[] allUserEmail = dustEnvDataMapper.GetAllUserEmail();

        System.out.println(allUserEmail.toString());
        List<DustEnvironment> dustEnvironments = dustEnvDataMapper.GetEnvData(0,1);
        DustEnvironment de = dustEnvironments.get(0);
        String text = "监测时间:" + de.getMonitorDateTime() + "\n\n" + "当前预警值： " + "粉尘浓度：" + "50" + "g/m³" + "温度：" + "30" + "℃ 。"
                + "\n" + "预警位置："+de.getMonitorLocal() + "\n" + "监测数据：  "+ "粉尘浓度：\t" +de.getDustDensity()+"温度：\t" + de.getTemperature()
                + "湿度：\t" +de.getHumidity() + "风速:\t"+de.getWindSpeed();
        mailService.sendMail("1467295546@qq.com", allUserEmail, "粉尘浓度预警！", text);

    }
//
//    @Test
//    public  void getoken() {
////        Jwts.builder();//生成JWT
////        Jwts.parser();//解密JWT
//
//        JwtBuilder jwtBuilder = Jwts.builder()
//                .setId(1 + "")//设置需要加密的内容
//                .setSubject("边文凯")
//                .setIssuedAt(new Date())//token保留时间
//                .signWith(SignatureAlgorithm.HS256, "antd").setExpiration(
//                        new Date(new Date().getTime() + 86400000)
//                );
//        System.out.println(jwtBuilder.compact());
//        //return jwtBuilder.compact();
//
//    }
//
//    @Test
//    public void tokenToOut() {
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoi6L655paH5YevIiwiaWF0IjoxNjUyMDYxMjMxLCJleHAiOjE2NTIxNDc2MzF9.LiOHB2VwVxGZ_y8Qyb-7zGDtc3RQQUv6metYFNdFX1A";
//        Claims claims = Jwts.parser()
//                .setSigningKey("antd")
//                .parseClaimsJws(token)
//                .getBody();
//        System.out.println("用户id:"+claims.getId());
//        System.out.println("用户名:"+claims.getSubject());
//        System.out.println("用户时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
//                format(claims.getIssuedAt()));System.out.println("过期时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
//                format(claims.getExpiration()));
//        System.out.println("用户角色:"+claims.get("role"));
//    }

//    @Test
//    public void test() {
//       String string= "{\"dustLimit\":\"25\",\"temperatureLimit\":\"55\"}";
//        JSONObject obj = (JSONObject) JSON.parse(string);
//        String str = (String) obj.get("dustLimit");
//        System.out.println(str);
//    }




//    @Test
//    private void mailTest() {
//        String[] allUserEmail = dustEnvDataMapper.GetAllUserEmail();
//
//        MailService mailService = new MailService();
//        List<DustEnvironment> dustEnvironments = dustEnvDataMapper.GetEnvData(0,1);
//        DustEnvironment de = dustEnvironments.get(0);
//        String text = "监测时间:" + de.getMonitorDateTime() + "\n\n" + "当前预警值： " + "粉尘浓度：" + "50" + "g/m³" + "温度：" + "30" + "℃ 。"
//                + "\n" + "预警位置："+de.getMonitorLocal() + "\n" + "监测数据：  "+ "粉尘浓度：\t" +de.getDustDensity()+"温度：\t" + de.getTemperature()
//                + "湿度：\t" +de.getHumidity() + "风速:\t"+de.getWindSpeed();
//        mailService.sendMail("1467295546@qq.com", allUserEmail,"", "粉尘浓度预警！", text);
//    }
}
