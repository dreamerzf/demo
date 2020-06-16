package com.zf.demo.config.Interceptor;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Create by zengfei
 * Date 2020/6/4 11:40
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisCluster jedisCluster;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行到preHandle");
        /**
         * 如果拦截之后想返回前台一个标志status
         * 如果不加过滤器的时候跨域第一次发送的Option报文放过
         */
        String userId=request.getHeader("userId");
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        if(userId!=null && userId.length()>0){
            String value=jedisCluster.get(userId);
            if(value==null){
                PrintWriter writer=response.getWriter();
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("loginstatus","9999");//该userId超时
                jsonObject.put("loginuser",userId);
                writer.append(jsonObject.toString());
//                response.sendRedirect(request.getContextPath()+"/login/index");//返回标志和重定向任选其一
                return false;  //此处一定要是return false 不然会有write的错误
            }else{
                jedisCluster.expire(userId,120);//更新缓存时间
                return true;
            }
        }else {
            PrintWriter writer = response.getWriter();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("loginstatus","8888");//为获取到userId
            writer.append(jsonObject.toString());
//            response.sendRedirect(request.getContextPath()+"/login/index");//返回标志和重定向任选其一
            return false;
        }
//        if (1==1){
//            response.sendRedirect(request.getContextPath()+"/login/index");
//        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行到postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("执行到afterCompletion");

    }
}
