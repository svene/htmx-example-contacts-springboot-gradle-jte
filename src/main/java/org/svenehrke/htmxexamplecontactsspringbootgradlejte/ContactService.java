package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Service
@AllArgsConstructor
public class ContactService {

	private DSLContext jooq;

	String getName() {
		return "World";
	}

	private static Field<String> firstName = field("first_name", String.class);
	private static Field<String> lastName = field("last_name", String.class);

	record Contact(String firstName, String lastName){};

	public List<Contact> all() {
		Result<Record2<String, String>> jooqResult = jooq.select(firstName, lastName).from(table("contact")).fetch();
		return jooqResultToList(jooqResult);
	}

	public List<Contact> search(String q) {
		Result<Record2<String, String>> jooqResult = jooq.select(firstName, lastName)
			.from(table("contact"))
			.where(firstName.like(q + "%"))
			.fetch();
		return jooqResultToList(jooqResult);
	}

	private static List<Contact> jooqResultToList(Result<Record2<String, String>> jooqResult) {
		return jooqResult.stream()
			.map(it -> new Contact(it.get(firstName), it.get(lastName)))
			.toList();
	}

}
