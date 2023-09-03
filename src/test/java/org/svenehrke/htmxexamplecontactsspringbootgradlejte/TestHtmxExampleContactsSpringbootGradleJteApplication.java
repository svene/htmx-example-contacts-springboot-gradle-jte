package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration(proxyBeanMethods = false)
@Import({DataFakerInitializr.class})
public class TestHtmxExampleContactsSpringbootGradleJteApplication {

	public static void main(String[] args) {
		SpringApplication.from(HtmxExampleContactsSpringbootGradleJteApplication::main).with(TestHtmxExampleContactsSpringbootGradleJteApplication.class).run(args);
	}

}
