package com.springcloud.demo.springclouddemo.configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import com.springcloud.demo.springclouddemo.entity.SelfZoneAwareLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.commons.httpclient.HttpClientConfiguration;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClientName;
import org.springframework.cloud.netflix.ribbon.apache.HttpClientRibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.okhttp.OkHttpRibbonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName SelfRibbonClientConfiguration
 * @Description
 * @Author shanghao5
 * @Date 2019/2/26 14:10
 **/
//@Configuration
//@EnableConfigurationProperties
//@Import({HttpClientConfiguration.class, OkHttpRibbonConfiguration.class, HttpClientRibbonConfiguration.class})
//public class SelfRibbonClientConfiguration extends RibbonClientConfiguration {
//
//    @RibbonClientName
//    private String name = "client";
//    @Autowired
//    private PropertiesFactory propertiesFactory;
//
//
//    @Bean
//    @Override
//    public ILoadBalancer ribbonLoadBalancer(IClientConfig config, ServerList<Server> serverList, ServerListFilter<Server> serverListFilter, IRule rule, IPing ping, ServerListUpdater serverListUpdater) {
//        ILoadBalancer iLoadBalancer;
//        if (this.propertiesFactory.isSet(ILoadBalancer.class, this.name)) {
//            iLoadBalancer = this.propertiesFactory.get(ILoadBalancer.class, config, this.name);
//        } else {
//            iLoadBalancer = new SelfZoneAwareLoadBalancer(config, rule, ping, serverList, serverListFilter, serverListUpdater);
//        }
//
//        return iLoadBalancer;
//    }
//}
