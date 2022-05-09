package cn.utils;

import cn.bianwenkai.entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author BianWenKai
 * @DATE 2022/5/9 - 9:32
 **/
public class CreateJwt {

    public static String getoken(User user) {
//        Jwts.builder();//生成JWT
//        Jwts.parser();//解密JWT

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(user.getUserId() + "")//设置需要加密的内容
                .setSubject(user.getUserName())
                .setIssuedAt(new Date())//token保留时间
                .signWith(SignatureAlgorithm.HS256, "antd").setExpiration(
                        new Date(new Date().getTime() + 86400000)
                );
        System.out.println(jwtBuilder.compact());
        return jwtBuilder.compact();

    }
}
