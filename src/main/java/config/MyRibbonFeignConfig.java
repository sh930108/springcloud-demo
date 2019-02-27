package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
@Configuration
public class MyRibbonFeignConfig {
	@Bean
	public IRule ribbonRule() {
		return new MyRule();
	}
}
