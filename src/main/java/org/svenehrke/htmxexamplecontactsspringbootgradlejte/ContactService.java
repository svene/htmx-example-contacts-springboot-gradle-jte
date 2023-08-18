package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ContactService {

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
