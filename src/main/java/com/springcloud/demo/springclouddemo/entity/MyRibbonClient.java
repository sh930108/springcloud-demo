package com.springcloud.demo.springclouddemo.entity;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.netflix.ribbon.DefaultServerIntrospector;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonUtils;
import org.springframework.cloud.netflix.ribbon.ServerIntrospector;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

public class MyRibbonClient extends RibbonLoadBalancerClient{
	private SpringClientFactory clientFactory;
	public MyRibbonClient(SpringClientFactory clientFactory) {
		
		super(clientFactory);
		this.clientFactory=clientFactory;
	}
	
	@Override
	public ServiceInstance choose(String serviceId) {
		ServiceInstance serviceInstance=super.choose(serviceId);
		if (serviceInstance == null) {
			serviceInstance=new ServiceInstance() {
				
				@Override
				public boolean isSecure() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public URI getUri() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getServiceId() {
					// TODO Auto-generated method stub
					return serviceId;
				}
				
				@Override
				public int getPort() {
					// TODO Auto-generated method stub
					return 81;
				}
				
				@Override
				public Map<String, String> getMetadata() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getHost() {
					// TODO Auto-generated method stub
					return "localhost";
				}
			};
			
		}
		return serviceInstance;
	}
	@Override
	public URI reconstructURI(ServiceInstance instance, URI original) {
		
		return super.reconstructURI(instance, original);
	}
	@Override
	public <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException {
		
		ILoadBalancer loadBalancer = getLoadBalancer(serviceId);
		Server server = getServer(loadBalancer);
		if (server == null) {
			server=new Server("localhost", 81);
			//throw new IllegalStateException("No instances available for " + serviceId);
		}
		RibbonServer ribbonServer = new RibbonServer(serviceId, server, isSecure(server,
				serviceId), serverIntrospector(serviceId).getMetadata(server));

		return execute(serviceId, ribbonServer, request);
	}
	@Override
	public <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException {
		
		return super.execute(serviceId, serviceInstance, request);
	}
	private ServerIntrospector serverIntrospector(String serviceId) {
		ServerIntrospector serverIntrospector = clientFactory.getInstance(serviceId,
				ServerIntrospector.class);
		if (serverIntrospector == null) {
			serverIntrospector = new DefaultServerIntrospector();
		}
		return serverIntrospector;
	}
	private boolean isSecure(Server server, String serviceId) {
		IClientConfig config = this.clientFactory.getClientConfig(serviceId);
		ServerIntrospector serverIntrospector = serverIntrospector(serviceId);
		return RibbonUtils.isSecure(config, serverIntrospector, server);
	}
}
