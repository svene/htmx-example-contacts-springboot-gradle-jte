package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ContactService {

	private DataSource dataSource;

	String getName() {
		return "World";
	}

	public List<String> all() {
		return Arrays.asList("contact 1", "contact 2");
	}
	public List<String> search(String q) {
		return Arrays.asList("contact 1");
	}
}
