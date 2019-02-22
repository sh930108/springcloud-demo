package com.springcloud.demo.springclouddemo.discoveryDemo;

import com.springcloud.demo.springclouddemo.SpringcloudDemoApplication;
import com.springcloud.demo.springclouddemo.microservice.SerivceFeginClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ClassName DiscoveryDemo
 * @Description
 * @Author shanghao5
 * @Date 2019/2/22 15:22
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={SpringcloudDemoApplication.class})
public class DiscoveryDemoTest {

    @Autowired
    private ConsulDiscoveryClient consulDiscoveryClient;
    @Autowired
    private SerivceFeginClient serivceFeginClient;


    @Test
    public void testDiscovery(){

        consulDiscoveryClient.getInstances("service-producer");



        System.out.println("=====================");

    }

    @Test
    public void testHelloRequest(){

        String hello = serivceFeginClient.hello();

        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println(hello);
        System.out.println("=====================");

    }

}
