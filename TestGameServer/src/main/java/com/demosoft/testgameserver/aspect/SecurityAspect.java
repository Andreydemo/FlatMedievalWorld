package com.demosoft.testgameserver.aspect;

import com.demosoft.testgameserver.rest.AuthenticationController;
import com.demosoft.testgameserver.service.Profile;
import com.demosoft.testgameserver.service.entiry.ServiceResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * Created by Andrii_Korkoshko on 6/10/2016.
 */
@Aspect
@Component
public class SecurityAspect {

    @Autowired
    private Profile profile;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object checkPasickAutorization(ProceedingJoinPoint point) throws Throwable {
        Class aClass = ((MethodSignature) point.getSignature()).getMethod().getReturnType();
        Object retval = null;
        if (aClass.equals(ServiceResponse.class) && !point.getTarget().getClass().equals(AuthenticationController.class) && !profile.isLogedIn()) {
                ServiceResponse serviceResponse = new ServiceResponse();
                serviceResponse.setSuccess(false);
                serviceResponse.getErrors().add("not authorized request");
                retval = serviceResponse;

        } else {
            retval = point.proceed();
        }

        return retval;
    }
}
