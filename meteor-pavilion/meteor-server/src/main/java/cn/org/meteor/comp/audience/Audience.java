package cn.org.meteor.comp.audience;

import com.alibaba.dubbo.common.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class Audience {
    /**
     * 前置通知：目标方法执行之前执行以下方法体的内容
     *
     * @param jp
     */
    @Before(value = "execution(* cn.org.meteor.comp.controller.*.*(..))")
    public void beforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        log.info("进入方法:" + methodName);
    }

    /**
     * 返回通知：目标方法正常执行完毕时执行以下代码
     *
     * @param jp
     * @param result
     */
    @AfterReturning(value = "execution(* cn.org.meteor.comp.controller.*.*(..))", returning = "result")
    public void afterReturningMethod(JoinPoint jp, Object result) throws Exception {
        String methodName = jp.getSignature().getName();
        log.info("方法" + methodName + "返回参数:{}", JSON.json(result));
    }

    /**
     * 异常通知
     *
     * @param jp
     */
    @AfterThrowing(value = "execution(* cn.org.meteor.comp.service.userinfo.*.*(..))", throwing = "ex")
    public void afterMethod(JoinPoint jp, Exception ex) {
        String methodName = jp.getSignature().getName();
        log.error(methodName + " 异常:{}", ex.getMessage());
    }

}
