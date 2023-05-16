package kr.co.hhjpetclinicstudy.infrastructure.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class APIInfoAspect {

    @Pointcut("execution(* kr.co.hhjpetclinicstudy.controller..*.*(..))")
    private void allController() {
    }

    /**
     * Pointcut 에 의해 필터링된 경로로 들어오는 경우 메서드 호출 전에 적용
     */
    @Before("allController()")
    public void beforeLogApiInfo(JoinPoint joinPoint) {

        log.info("======= Current Thread Name : {} =======", Thread.currentThread().getName());

        Method method = getMethod(joinPoint);

        log.info("======= Method Name : {} =======", method.getName());

        Object[] args = joinPoint.getArgs();

        if (args.length <= 0) {
            log.info("[No Parameter]");
        }

        for (Object arg : args) {
            log.info("Parameter Type : {}", args.getClass().getSimpleName());
            log.info("Parameter Value : {}", arg);
        }
    }

    /**
     * Poincut 에 의해 필터링된 경로로 들어오는 경우 메서드 리턴 후에 적용
     */
    @AfterReturning(value = "allController()", returning = "returnObject")
    public void afterReturnLogApiInfo(JoinPoint joinPoint,
                                      Object returnObject) {

        log.info("======= Current Thread Name : {} =======", Thread.currentThread().getName());

        Method method = getMethod(joinPoint);

        log.info("======= Method Name : {} =======", method.getName());

        log.info("Return Type : {}", returnObject.getClass().getSimpleName());
        log.info("Return Value : {}", returnObject);
    }

    /**
     * @param joinPoint
     * @return : JoinPoint 로 Method 정보 반환
     */
    private Method getMethod(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        return methodSignature.getMethod();
    }
}
