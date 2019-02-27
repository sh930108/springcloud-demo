package config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;

public class MyRule extends ZoneAvoidanceRule {

	@Override
	public Server choose(Object key) {
		Server server=	super.choose(key);
		if(server==null) {
			ILoadBalancer lb = getLoadBalancer();
			if(lb instanceof ZoneAwareLoadBalancer) {
				String serviceName=((ZoneAwareLoadBalancer) lb).getName();
				System.out.println(serviceName);
				server=new Server("localhost", 81);
			}
		}
		return server;
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
		super.initWithNiwsConfig(clientConfig);
	}

}
