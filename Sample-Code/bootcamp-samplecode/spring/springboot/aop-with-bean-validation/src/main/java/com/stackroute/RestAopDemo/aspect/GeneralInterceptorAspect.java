package com.stackroute.RestAopDemo.aspect;


import com.stackroute.RestAopDemo.model.Department;
import com.stackroute.RestAopDemo.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class GeneralInterceptorAspect {

//@Pointcut("execution(* com.stackroute.RestAopDemo.controller.*.*(..))")

    @Pointcut("within(com.stackroute.RestAopDemo..*))")
    public void loggingPointCut(){}

//    @Before("loggingPointCut()")
//    public void before(JoinPoint joinPoint) {
//     log.info("Before Method invoke::"+joinPoint.getSignature());
//    }
//
//    @After("loggingPointCut()")
//    public void after(JoinPoint joinPoint){
//     log.info("After method invoked::"+joinPoint.getSignature());
//    }

//    @AfterReturning(value = "execution(* com.stackroute.RestAopDemo.controller.*.*(..))", returning = "employee")
//    public void after(JoinPoint joinPoint, Employee employee){
//        log.info("After method invoked::"+joinPoint.getSignature());
//    }

    @Around("loggingPointCut()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before Method invoked"+joinPoint.getSignature());
        Object object = joinPoint.proceed();
        if(object instanceof Employee){
            log.info("After Method invoked"+joinPoint.getArgs()[0]);
        } else if (object instanceof Department) {
            log.info("After Method invoked"+joinPoint.getSignature());
        }
        return object;
    }
}
