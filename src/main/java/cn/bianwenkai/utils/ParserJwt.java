package cn.bianwenkai.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @author BianWenKai
 * @DATE 2022/5/9 - 9:46
 **/
public class ParserJwt {

    public static Claims decoding(String token) {
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
        return claims;
    }

}
