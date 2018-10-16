package com.capgemini.configurationclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigurationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationClientApplication.class, args);
	}
}
/*@RestController
class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}*/

@RefreshScope
@RestController
class ServiceInstanceRestController {
	@Autowired
	private EurekaClient eurekaClient;
	
	private static final RestTemplate REST_TEMPLATE=new RestTemplate();
	
	@RequestMapping("/message")
	public String getmessage() {
		Application application=eurekaClient.getApplication("configuration");
		InstanceInfo instanceInfo=application.getInstances().get(0);
		String url="http://"+instanceInfo.getIPAddr()+":"+instanceInfo.getPort()+"/message";
		return REST_TEMPLATE.getForObject(url, String.class);
		
	}
	
}