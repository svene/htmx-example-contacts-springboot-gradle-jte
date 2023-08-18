package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

	@Bean
	Something something() {
		return new Something("sven");
	}
}
