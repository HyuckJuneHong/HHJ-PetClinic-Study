package kr.co.hhjpetclinicstudy.infrastructure.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ExecutionTimeLoggingAspect {

    @Pointcut("execution(* kr.co.hhjpetclinicstudy.service.service.OwnerService.*(..))")
    private void allOwnerService(){ }

    @Around("allOwnerService()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{

        String className = joinPoint.getTarget().getClass().getSimpleName();

        String methodName = joinPoint.getSignature().getName();

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        log.info("[ExecutionTime] {}.{} : {}ms", className, methodName, stopWatch.getTotalTimeMillis());

        return result;
    }

}
