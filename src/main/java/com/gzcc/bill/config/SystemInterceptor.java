package com.gzcc.bill.config;




import com.gzcc.bill.Util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 @Autor zhuoyj[hopnetworks]
 @Date 2018/8/11
 @function 负责Session，令牌的控制
 @Description 这是一个拦截器，通过实现接口来使用

 */
@Component
public class SystemInterceptor implements HandlerInterceptor  {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //System.out.println("Post-handle");
        // 在请求处理之前进行调用（Controller方法调用之前）,返回true才会继续往下执行，返回false取消当前请求
        boolean isAccess = false;
        String tokenCode = request.getHeader("token");
        if (tokenCode == null) {
            // !tokenCode.startsWith("Bearer:")
            return false;

        }


        if (!"".equals(tokenCode)) {
//取得token

            // String token = tokenCode.substring(7);
            //验证token
            Claims claims = JwtUtil.checkToken(tokenCode);
            //查询未过期的
            return claims!=null;
//

        }
        logger .debug("Post-handle") ;
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {




    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }


}
