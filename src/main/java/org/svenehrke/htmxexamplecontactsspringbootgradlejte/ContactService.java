package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import lombok.AllArgsConstructor;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.JdbcTemplate;
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
	private JdbcTemplate jdbcTemplate;

	String getName() {
		return "World";
	}

	record Contact(String firstName, String lastName){};

	public List<String> all() {
		var jooq = DSL.using(dataSource, SQLDialect.POSTGRES);
		Result<Record2<Object, Object>> fetch = jooq.select(field("first_name"), field("last_name")).from(table("contact")).fetch();
		List<Contact> list = fetch.stream().map(it -> new Contact(it.get(field("first_name", String.class)), it.get(field("last_name", String.class)))).toList();
		System.out.println(list);

		var sql = jooq.select(field("first_name"), field("last_name")).from(table("contact")).getSQL();
		System.out.println("query = " + sql);
		var stream = jdbcTemplate.queryForStream(sql, (rs, rowNum) -> {
			return new Contact(rs.getString(1), rs.getString(2));
		});
		List<String> result = stream.map(it -> "%s, %s".formatted(it.firstName, it.lastName)).toList();

		return result;
	}
	public List<String> search(String q) {

		return Arrays.asList("contact 1");
	}
}
