package com.example.springdome.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * pom.xml 添加Aop支持
 *     <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-aop</artifactId>
 *         </dependency>
 *
 * 切片Aspect，既然Spring那么支持AOP，可以拿到原始的HTTP请求和响应的信息，
 *      也可以拿到你真正处理请求方法的信息，也可以传进参数的那个值。
 *
 * 拦截顺序：filter—>Interceptor-->ControllerAdvice-->@Aspect -->Controller
 */
@Component  //表示它是一个Spring的组件
@Aspect  //表示它是一个切面
public class HttpAspect {

    /**
     * 通过ProceedingJoinPoint对象的getArgs()我们可以得到传进来的参数。
     * 通过ProceedingJoinPoint对象的proceed()我们可以得到拿到切面方法返回值的对象。
     * @return
     * 环绕通知    首先是:包名  然后是: 类名  然后是方法名:方法名   括号内是:参数
     */
    @Around("execution(* com.example.springdome.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("HttpAspect handleControllerMethod filter start");

        //原始的HTTP请求和响应的信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        //获取当前执行的方法
//        Method targetMethod = methodSignature.getMethod();
        Method targetMethod = methodSignature.getMethod();
        System.out.println("当前执行的方法: "+targetMethod.getName());

        //获取参数
        Object[] objs = pjp.getArgs();
        for (Object obj:objs){
            System.out.println("参数:"+obj);
        }

        //获取返回对象
        Object object = pjp.proceed();
        System.out.println("获得返回对象 : "+object);
        System.out.println("HttpAspect handleControllerMethod filter end");

        return pjp.proceed();//代理方法的返回值
    }
}
