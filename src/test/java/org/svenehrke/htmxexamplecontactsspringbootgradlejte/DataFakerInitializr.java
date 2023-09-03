package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import net.datafaker.Faker;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DataFakerInitializr implements InitializingBean {

	@Autowired
	ContactService contactService;

	@Override
	public void afterPropertiesSet() {
		var faker = new Faker(Locale.GERMAN);
		int i = 1000;
		while (i > 0) {
			var c = ContactBuilder.builder()
				.firstName(faker.name().firstName())
				.lastName(faker.name().lastName())
				.email(faker.internet().emailAddress()).phone(faker.phoneNumber().cellPhone()).build();
			contactService.insertContact(c);
			i--;
		}
	}
}
