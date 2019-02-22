package com.springcloud.demo.springclouddemo;

import com.ecwid.consul.v1.ConsulClient;
import com.springcloud.demo.springclouddemo.entity.ConsulDiscoveryClientDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringcloudDemoApplication {

    @Autowired
    private ConsulClient consulClient;

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDemoApplication.class, args);
    }

    @Bean
//    @ConditionalOnMissingBean
    public ConsulDiscoveryClient consulDiscoveryClient(ConsulDiscoveryProperties discoveryProperties) {

        ConsulDiscoveryClient consulDiscoveryClient = new ConsulDiscoveryClientDemo(this.consulClient, discoveryProperties);
        consulDiscoveryClient.getServices();
        return consulDiscoveryClient;



    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
