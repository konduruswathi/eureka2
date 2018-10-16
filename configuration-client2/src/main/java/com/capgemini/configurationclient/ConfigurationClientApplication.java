package com.capgemini.configurationclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient

@SpringBootApplication
public class ConfigurationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationClientApplication.class, args);
	}
}

@RefreshScope
@RestController
class ServiceInstanceRestController {
	
	@Value("${message:default}")
	private String message;

	@RequestMapping("/message")
	public String getmessage() {
		return this.message;
	}
}

/*
 * @Autowired private DiscoveryClient discoveryClient;
 * 
 * @RequestMapping("/service-instances/{applicationName}") public
 * List<ServiceInstance> serviceInstancesByApplicationName(
 * 
 * @PathVariable String applicationName) { return
 * this.discoveryClient.getInstances(applicationName); } }
 */
