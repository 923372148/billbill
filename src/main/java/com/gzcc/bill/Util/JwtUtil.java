package com.gzcc.bill.Util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

/**
 @Autor zhuoyj[hopnetworks]
 @Date 2018/9/29
 @function JSON WEB Util 工具类
 @Description

 */
public class JwtUtil {
    final static String base64EncodedSecretKey =  ResourceBundleUtil.getSystemString("base64EncodedSecretKey") ;//私钥
    final static long TOKEN_EXP = Long.parseLong(ResourceBundleUtil.getSystemString("TOKEN_EXP") ) ;;//过期时间

    public static String getToken(String userName,String role
    ) {
        if(role==null){
            role="user";
        }
        return Jwts.builder().setSubject(userName)
                .claim("roles", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }
    /**
     * 检查token,只要不正确就会抛出异常
     **/

    public static Claims checkToken(String token) throws ServletException {

        try {

            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();

            return claims;

        } catch (ExpiredJwtException e1) {

            System .out .println("过期Token");
            throw new TokenStatusException();

//            throw new UserLoginInvalidException("登录信息过期，请重新登录");

        } catch (Exception e) {

            System .out .println("未登录") ;
            throw new TokenStatusException();
//            throw new UserLoginException("用户未登录，请重新登录");

        }
//return null;
    }

    public static boolean checkIfManager(String token) throws ServletException {


        final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        if (!claims.get("roles").toString().equals("superManager")) {
            throw new NoauthorityException();
        }
        return true;

    }


    public static boolean checkIfCompanyManager(String token) throws ServletException{

        final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();

        if(!claims.get("roles").toString().equals("companyManager")&&!claims.get("roles").toString().equals("superManager")){
            throw new NoauthorityException();
        }

        return true;

    }






}
