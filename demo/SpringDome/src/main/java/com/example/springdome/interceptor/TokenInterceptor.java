package com.example.springdome.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器
 * 自定义拦截器后，需要配置进Spring
 *
 * 拦截器Interceptor可以拿到原始的HTTP请求和响应的信息，
 *    也可以拿到你真正处理请求方法的信息，但是拿不到传进参数的那个值。
 *
 *拦截顺序：filter—>Interceptor-->ControllerAdvice-->@Aspect -->Controller
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 在访问Controller某个方法之前这个方法会被调用。
     * @param request
     * @param response
     * @param handler
     * @return false则表示不执行postHandle方法,true 表示执行postHandle方法
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String token = request.getHeader("token");
        System.out.println("TokenInterceptor preHandle " +pageNum+","+pageSize +"\n"+token);

        //spring boot 2.0对静态资源也进行了拦截，当拦截器拦截到请求之后，
        // 但controller里并没有对应的请求时，该请求会被当成是对静态资源的请求。
        // 此时的handler就是 ResourceHttpRequestHandler，就会抛出上述错误。
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            System.out.println("TokenInterceptor preHandle getMethod "+method.getName());
        }else if(handler instanceof ResourceHttpRequestHandler){//静态资源
            ResourceHttpRequestHandler resourceHttpRequestHandler = (ResourceHttpRequestHandler) handler;
            System.out.println("Token Interceptor preHandle getMethod " + resourceHttpRequestHandler.getMediaTypes());
        }

        //false则表示不执行postHandle方法,不执行下一步chain链，直接返回response
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     * preHandle方法处理之后这个方法会被调用，如果控制器Controller出现了异常，则不会执行此方法
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 不管有没有异常，这个afterCompletion都会被调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
