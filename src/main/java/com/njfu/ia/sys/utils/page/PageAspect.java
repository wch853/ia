package com.njfu.ia.sys.utils.page;

import com.github.pagehelper.PageHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PageAspect {

    /**
     * 设置切入点为标注有 @PageOffset 注解的路由方法
     */
    @Pointcut("@annotation(com.njfu.ia.sys.utils.page.PageOffset)")
    public void pageOffset() {
    }

    @Around("pageOffset()")
    public Object aroundPaginationRequest(ProceedingJoinPoint joinPoint) {
        Object result = null;
        Object[] args = joinPoint.getArgs();
        Integer offset = (Integer) args[0];
        Integer limit = (Integer) args[1];

        if (null != offset && null != limit) {
            PageHelper.offsetPage(offset, limit);
        } else {
            PageHelper.offsetPage(0, Short.MAX_VALUE);
        }

        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            result = null;
        }
        return result;
    }
}
