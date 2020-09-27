package com.empsystem.employeesystem.config;

import com.empsystem.employeesystem.services.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AOPConfig {

    /*
    @Pointcut(
            "execution(public String com.empsystem.employeesystem.services.UserService.addUser(..))"
    )*/

    @Pointcut(value = "execution(* com.empsystem.employeesystem.*.*.*(..))")
    public void monitor() { }

    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
        return new PerformanceMonitorInterceptor(true);
    }

    @Bean
    public Advisor performanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.empsystem.employeesystem.config.AOPConfig.monitor()");
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }

    @Bean
    public UserService userServiceAopBean(){
        return new UserService();
    }
}