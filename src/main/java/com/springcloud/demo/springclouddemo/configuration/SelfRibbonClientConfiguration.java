package com.springcloud.demo.springclouddemo.configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import com.springcloud.demo.springclouddemo.entity.SelfZoneAwareLoadBalancer;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SelfRibbonClientConfiguration
 * @Description
 * @Author shanghao5
 * @Date 2019/2/26 14:10
 **/
@Configuration
public class SelfRibbonClientConfiguration extends RibbonClientConfiguration {

    @Bean
    @Override
    public ILoadBalancer ribbonLoadBalancer(IClientConfig config, ServerList<Server> serverList, ServerListFilter<Server> serverListFilter, IRule rule, IPing ping, ServerListUpdater serverListUpdater) {
        ILoadBalancer iLoadBalancer = super.ribbonLoadBalancer(config, serverList, serverListFilter, rule, ping, serverListUpdater);
        if(iLoadBalancer instanceof ZoneAwareLoadBalancer){
            iLoadBalancer = new SelfZoneAwareLoadBalancer(config, rule, ping, serverList, serverListFilter, serverListUpdater);
        }
        return iLoadBalancer;
    }
}
