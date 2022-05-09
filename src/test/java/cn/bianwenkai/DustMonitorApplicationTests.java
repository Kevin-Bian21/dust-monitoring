package cn.bianwenkai;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class DustMonitorApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public  void getoken() {
//        Jwts.builder();//生成JWT
//        Jwts.parser();//解密JWT

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(1 + "")//设置需要加密的内容
                .setSubject("边文凯")
                .setIssuedAt(new Date())//token保留时间
                .signWith(SignatureAlgorithm.HS256, "antd").setExpiration(
                        new Date(new Date().getTime() + 86400000)
                );
        System.out.println(jwtBuilder.compact());
        //return jwtBuilder.compact();

    }

    @Test
    public void tokenToOut() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoi6L655paH5YevIiwiaWF0IjoxNjUyMDYxMjMxLCJleHAiOjE2NTIxNDc2MzF9.LiOHB2VwVxGZ_y8Qyb-7zGDtc3RQQUv6metYFNdFX1A";
        Claims claims = Jwts.parser()
                .setSigningKey("antd")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("用户id:"+claims.getId());
        System.out.println("用户名:"+claims.getSubject());
        System.out.println("用户时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                format(claims.getIssuedAt()));System.out.println("过期时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                format(claims.getExpiration()));
        System.out.println("用户角色:"+claims.get("role"));
    }
}
