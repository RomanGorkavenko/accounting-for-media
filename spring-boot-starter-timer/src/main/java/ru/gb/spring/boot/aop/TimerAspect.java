package ru.gb.spring.boot.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;


@Slf4j
@Aspect
@RequiredArgsConstructor
public class TimerAspect {

    private final TimerProperties properties;

    @Pointcut("within(@ru.gb.spring.boot.aop.annotations.* *)")
    public void beansAnnotatedWith() {
    }

    @Pointcut("@annotation(ru.gb.spring.boot.aop.annotations.Timer)")
    public void methodsAnnotatedWithTimer() {
    }

    @Around("beansAnnotatedWith() || methodsAnnotatedWithTimer()")
    public Object executionTimeOfMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        StopWatch countdown = new StopWatch();

        Object result;

        try {
            countdown.start();
            result = proceedingJoinPoint.proceed();
            countdown.stop();
            doLog("{}-{} #({} ms)", className, methodName, countdown.getTotalTimeMillis());
        } catch (Exception e) {
            countdown.stop();
            doLog("{}-{} #({} ms)", className, methodName, countdown.getTotalTimeMillis());
            throw e;
        }

        return result;
    }

    private void doLog(String text, Object... params) {
        log.atLevel(properties.getLogLevel()).log(text, params);
    }

}
