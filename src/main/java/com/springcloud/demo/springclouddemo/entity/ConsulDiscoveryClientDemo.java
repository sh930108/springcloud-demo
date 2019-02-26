package com.springcloud.demo.springclouddemo.entity;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ConsulDiscoveryClientDemo
 * @Description
 * @Author shanghao5
 * @Date 2019/2/22 15:08
 **/
public class ConsulDiscoveryClientDemo extends ConsulDiscoveryClient {



    public ConsulDiscoveryClientDemo(ConsulClient client, ConsulDiscoveryProperties properties) {
        super(client, properties);
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId){
        List<ServiceInstance> instances = super.getInstances(serviceId);

        // 定制
        instances.add(new ServiceInstance() {
            @Override
            public String getServiceId() {
                return "service-producer";
            }

            @Override
            public String getHost() {
                return "localhost";
            }

            @Override
            public int getPort() {
                return 8501;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public URI getUri() {
                return null;
            }

            @Override
            public Map<String, String> getMetadata() {
                return null;
            }
        });

        return instances;
    }

    @Override
    public List<String> getServices(){
        List<String> services = super.getServices();

        // 定制
        if(CollectionUtils.isEmpty(services)){
            services = new ArrayList<>();
        }
        services.add("haha");
        services.add("heiheihei");
        return services;
    }

}
