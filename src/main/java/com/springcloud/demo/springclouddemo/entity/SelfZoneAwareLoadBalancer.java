package com.springcloud.demo.springclouddemo.entity;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;

/**
 * @ClassName SelfZoneAwareLoadBalancer
 * @Description
 * @Author shanghao5
 * @Date 2019/2/26 11:50
 **/
public class SelfZoneAwareLoadBalancer<T extends Server> extends ZoneAwareLoadBalancer<T> {


    public SelfZoneAwareLoadBalancer(IClientConfig clientConfig, IRule rule, IPing ping, ServerList<T> serverList, ServerListFilter<T> filter, ServerListUpdater serverListUpdater) {
        super(clientConfig, rule, ping, serverList, filter, serverListUpdater);
    }

    @Override
    public Server chooseServer(Object key){
        Server server = super.chooseServer(key);

        if(server ==null){
            server =  new Server("127.0.0.1",8051);
        }

        return server;
    }

}
