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
public class ExecutionTimeAspect {

    @Pointcut("execution(* kr.co.hhjpetclinicstudy.service.service.OwnerService.*(..))")
    private void allOwnerService() {
    }

    @Pointcut("execution(* kr.co.hhjpetclinicstudy.service.service.PetService.*(..))")
    private void allPetService() {
    }

    @Pointcut("execution(* kr.co.hhjpetclinicstudy.service.service.VetService.*(..))")
    private void allVetService() {
    }

    @Pointcut("execution(* kr.co.hhjpetclinicstudy.service.service.VisitService.*(..))")
    private void allVisitService() {
    }

    //TODO : @issue - 현재 메소드 내부에서 다른 메소드를 호출하는 상황에서 내부 메소드에 해당 AOP가 적용되지 않는 이슈 발생.
    @Around("allOwnerService() || allPetService() || allVetService() || allVisitService()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getSimpleName();

        String methodName = joinPoint.getSignature().getName();

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        log.info("======= ExecutionTime -> {}.{} : {}ms =======", className, methodName, stopWatch.getTotalTimeMillis());

        return result;
    }
}
