package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.HelloController;
import com.example.demo.HelloLogic;

@Configuration
public class ControllerConfig {
	HelloLogic helloLogic = null;
	
	@Bean
	public HelloController helloController() {
		HelloController helloController = new HelloController();
		helloController.setHelloLogic(helloLogic);
		return helloController;
	}
	
}
