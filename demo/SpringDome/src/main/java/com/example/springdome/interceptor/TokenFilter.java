package com.example.springdome.interceptor;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义Filter
 * 对请求的header 过滤token
 *
 * 过滤器Filter可以拿到原始的HTTP请求和响应的信息，
 *     但是拿不到你真正处理请求方法的信息，也就是方法的信息
 *
 * @Component 注解让拦截器注入Bean，从而让拦截器生效
 * @WebFilter 配置拦截规则
 *
 * 拦截顺序：filter—>Interceptor-->ControllerAdvice-->@Aspect -->Controller
 *
 */
@Component
@WebFilter(urlPatterns = {"/**"},filterName = "tokenAuthorFilter")
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TokenFilter init "+filterConfig.getFilterName());
//        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TokenFilter doFilter 拦截到了请求 ");
        System.out.println("TokenFilter doFilter "+((HttpServletRequest)request).getParameter("pageNum") +","+((HttpServletRequest)request).getParameter("pageSize"));
        System.out.println("TokenFilter doFilter "+((HttpServletRequest)request).getHeader("token"));
        System.out.println("TokenFilter doFilter "+((HttpServletRequest)request).getRequestURL().toString());
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("TokenFilter destroy");
//        Filter.super.destroy();
    }
}
