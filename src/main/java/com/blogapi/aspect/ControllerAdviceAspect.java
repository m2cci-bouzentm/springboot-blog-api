package com.blogapi.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAdviceAspect {

    @Pointcut("execution(public * com.blogapi.controller.*.*(..))")
    public void controllerMethods() {}

    @AfterReturning(value = "controllerMethods()", returning = "result")
    public void handleControllerException(Object result) throws Throwable {
        System.out.println("After a controller method !");
//        System.out.println(result);
    }
}
