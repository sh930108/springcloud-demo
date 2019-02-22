package com.springcloud.demo.springclouddemo.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName SerivceFeginClient
 * @Description
 * @Author shanghao5
 * @Date 2019/2/22 16:32
 **/
@Component
@FeignClient(value = "service-producer") //这里的name对应调用服务的spring.applicatoin.name
public interface SerivceFeginClient {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hello();



}
