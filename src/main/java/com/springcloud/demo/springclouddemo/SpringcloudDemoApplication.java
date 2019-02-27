package com.springcloud.demo.springclouddemo;

import com.ecwid.consul.v1.ConsulClient;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import com.springcloud.demo.springclouddemo.entity.ConsulDiscoveryClientDemo;
import com.springcloud.demo.springclouddemo.entity.MyRibbonClient;
import com.springcloud.demo.springclouddemo.entity.SelfZoneAwareLoadBalancer;
import config.MyRibbonFeignConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.cloud.netflix.ribbon.RibbonClientName;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RibbonClients( defaultConfiguration  = MyRibbonFeignConfig.class)
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

    @Bean
    public LoadBalancerClient loadBalancerClient(SpringClientFactory springClientFactory) {
        return new MyRibbonClient(springClientFactory);
    }



}
