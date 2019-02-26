package com.springcloud.demo.springclouddemo.controller;

import com.google.gson.Gson;
import com.springcloud.demo.springclouddemo.microservice.SerivceFeginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName HelloController
 * @Description
 * @Author shanghao5
 * @Date 2019/2/22 15:02
 **/
@RestController
public class HelloController {

    @Autowired
    private SerivceFeginClient serivceFeginClient;
    @Autowired
    private ConsulDiscoveryClient consulDiscoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello() {
        return "hello consul";
    }


    @RequestMapping("/helloDemo")
    public String helloDemo() {
        String hello = serivceFeginClient.hello();
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println(hello);
        System.out.println("=====================");

        return "12";
    }


    @RequestMapping("/helloDiscoveryDemo")
    public String helloDiscoveryDemo() {
        List<ServiceInstance> instances = consulDiscoveryClient.getInstances("hello-springCloud");
        System.out.println("===================");
        System.out.println(new Gson().toJson(instances));
        System.out.println("==============");
        return "helloDiscoveryDemo";
    }


    @RequestMapping("/helloDiscoveryRest")
    public String helloDiscoveryRest() {
        String forObject = restTemplate.getForObject("http://service-producer/hello", String.class);
        System.out.println("===================");
        System.out.println(new Gson().toJson(forObject));
        System.out.println("==============");
        return "helloDiscoveryDemo";
    }
}
