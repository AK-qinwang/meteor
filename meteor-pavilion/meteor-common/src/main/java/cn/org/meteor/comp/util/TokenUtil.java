package cn.org.meteor.comp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class TokenUtil {

    private static String sercetKey = "XX#$%()(#*!()!KL<><MQLMNQNQJQK";

    public static String getToken(String userName, String password) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名密钥
        Key signingKey = generalKey();
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("userName", userName)
                .claim("password", password)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);
        //生成JWT
        return builder.compact();
    }

    /**
     * 解密token
     *
     * @param token
     * @return
     */
    public static Claims parseJwt(String token) {
        try {
            Key key = generalKey();
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 由字符串生成加密key
     *
     * @return
     */
    private static Key generalKey() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(sercetKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

}
