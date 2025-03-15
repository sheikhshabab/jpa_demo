package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CachingAspect {
    private final CacheManager cacheManager;
    private static final Logger logger = LoggerFactory.getLogger(CachingAspect.class);

    public CachingAspect(CacheManager cacheManager){
        this.cacheManager = cacheManager;
    }

    @Around("execution(* com.example.demo.controllers.*.*(..))")
    public Object cacheAround(ProceedingJoinPoint joinPoint) throws Throwable{
        String cacheName = "defaultCache";
        String cacheKey = joinPoint.getSignature().toString() + "_" + joinPoint.getArgs().toString();
        Cache cache = cacheManager.getCache(cacheName);
        if(cache!=null){
            Cache.ValueWrapper cachedValue = cache.get(cacheKey);
            if(cachedValue!=null){
                return cachedValue.get();
            }
        }
        Object result = joinPoint.proceed();
        if(cache!=null){
            cache.put(cacheKey, result);
            logger.info(cache.get(cacheKey).toString());
        }
        return result;
    }
}
