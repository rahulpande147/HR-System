package com.empsystem.employeesystem.aopconfig;

import com.empsystem.employeesystem.model.ExecutionDetails;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Aspect
@Component
public class ExecutionTimeTrackerAdvice {

    Logger logger = LoggerFactory.getLogger(ExecutionTimeTrackerAdvice.class);


    ExecutionDetails executionDetails = new ExecutionDetails();

        @Around("@annotation(com.empsystem.employeesystem.aopconfig.TrackExecutionTime)")
        public Object trackTime (ProceedingJoinPoint joinPoint) throws Throwable {

            executionDetails.setStartTime(System.currentTimeMillis());
            Object obj =  joinPoint.proceed();
            executionDetails.setMethodSignature(String.valueOf(joinPoint.getSignature()));
            executionDetails.setEndtime(System.currentTimeMillis());
            executionDetails.setExcutionTimeInMillis(executionDetails.startTime-executionDetails.endtime);
            logger.info("Method Signature :" + joinPoint.getSignature() + " Time Taken :" + (executionDetails.startTime-executionDetails.endtime));
            System.out.println("execution details:- " +executionDetails);
            RestTemplate restTemplate = new RestTemplate();

            URI url= new URI("http://localhost:8076/api/execution/post");
            ExecutionDetails executionDetailsJson = restTemplate.postForObject(url, executionDetails, ExecutionDetails.class);

            System.out.println("Execution Timeee:- " +executionDetails);
            //restTemplate.postForEntity("http://localhost:8076/api/execution/post", ExecutionDetails.class);

            return obj;
        }
}
