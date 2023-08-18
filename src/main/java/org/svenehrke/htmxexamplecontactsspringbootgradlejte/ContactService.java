package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import lombok.AllArgsConstructor;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Service
@AllArgsConstructor
public class ContactService {

	private DataSource dataSource;

	String getName() {
		return "World";
	}

	record Contact(String firstName, String lastName){};

	public List<String> all() {
		var jooq = DSL.using(dataSource, SQLDialect.POSTGRES);
		Result<Record2<Object, Object>> fetch = jooq.select(field("first_name"), field("last_name")).from(table("contact")).fetch();

		return fetch.stream()
			.map(it -> new Contact(it.get(field("first_name", String.class)), it.get(field("last_name", String.class))))
			.map(it -> "%s %s".formatted(it.firstName, it.lastName))
			.toList();
	}
	public List<String> search(String q) {

		return Arrays.asList("contact 1");
	}
}
