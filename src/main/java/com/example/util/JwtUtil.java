package com.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.exception.TokenException;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类（copy文档），也是主要利用了对称加密算法（可逆）
 */
@Slf4j
public class JwtUtil {

    //私钥
    private static final String TOKEN_SECRET = "123456";
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /**
     * 生成token，自定义过期时间 毫秒
     *
     * @return
     */

  private static   Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
    public static String generateToken(Integer userId) {
        try {
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");

            return JWT.create()
                    .withHeader(header)
                    .withClaim("token", userId + "")
                    //不设置过期时间，交给redis，这样就解决了一次性问题，就可以实现退出登录了
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 检验token是否正确
     *
     * @param token
     * @return
     */
    public static Integer parseJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String tokenInfo = jwt.getClaim("token").asString();
            return Integer.parseInt(tokenInfo);
        } catch (Exception e) {
            //简单起见，什么异常都是因为登录异常
            throw new TokenException("您还未登录!");
        }
    }

}