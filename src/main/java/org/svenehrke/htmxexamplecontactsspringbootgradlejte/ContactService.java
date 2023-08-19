package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import lombok.AllArgsConstructor;
import org.jooq.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Service
@AllArgsConstructor
public class ContactService {

	private DSLContext jooq;

	private static final Field<String> firstName = field("first_name", String.class);
	private static final Field<String> lastName = field("last_name", String.class);
	private static final Field<String> phone = field("phone", String.class);
	private static final Field<String> email = field("email", String.class);

	public List<Contact> all() {
		return jooqResultToList(getSelect().fetch());
	}

	public List<Contact> search(String q) {
		return jooqResultToList(getSelect().where(firstName.like(q + "%")).fetch());
	}

	private SelectJoinStep<Record4<String, String, String, String>> getSelect() {
		return jooq.select(firstName, lastName, phone, email).from(table("contact"));
	}

	private static List<Contact> jooqResultToList(Result<Record4<String, String, String, String>> jooqResult) {
		return jooqResult.stream()
			.map(it -> new Contact(it.get(firstName), it.get(lastName), it.get(phone), it.get(email)))
			.toList();
	}

}
