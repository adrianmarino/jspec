package ar.com.nonosoft.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleContext {
	@Bean
	public String testString() {
		return  "Hello";
	}
}
