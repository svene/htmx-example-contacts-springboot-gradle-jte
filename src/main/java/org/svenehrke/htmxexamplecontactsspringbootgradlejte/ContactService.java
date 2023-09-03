package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import lombok.AllArgsConstructor;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Service
@AllArgsConstructor
public class ContactService {

	private DSLContext jooq;

	private static final Table<Record> table = table("contact");
	private static final Field<BigInteger> id = field("ID", BigInteger.class);
	private static final Field<String> firstName = field("first_name", String.class);
	private static final Field<String> lastName = field("last_name", String.class);
	private static final Field<String> phone = field("phone", String.class);
	private static final Field<String> email = field("email", String.class);

	public List<Contact> all() {
		return jooqResultToList(getSelect().fetch());
	}

	public int count() {
		return jooq.fetchCount(table);
	}

	public List<Contact> search(String q) {
		return jooqResultToList(getSelect().where(firstName.like(q + "%")).fetch());
	}

	public Contact insertContact(Contact contact) {
		Record5<BigInteger, String, String, String, String> row = jooq.insertInto(table)
			.columns(firstName, lastName, phone, email)
			.values(contact.firstName(), contact.lastName(), contact.phone(), contact.email())
			.returningResult(id, firstName, lastName, phone, email)
			.fetchOne()
			;
		return mapRecord.apply(row);
	}

	public Contact updateContact(Contact contact) {
		Record5<BigInteger, String, String, String, String> row = jooq.update(table)
			.set(firstName, contact.firstName())
			.set(lastName, contact.lastName())
			.set(phone, contact.phone())
			.set(email, contact.email())
			.where(id.eq(contact.id()))
			.returningResult(id, firstName, lastName, phone, email)
			.fetchOne();
		return mapRecord.apply(row);
	}

	public void deleteContact(BigInteger aId) {
		jooq.delete(table).where(id.eq(aId)).execute();
	}

	public Contact byId(BigInteger aid) {
		Record5<BigInteger, String, String, String, String> record = getSelect().where(id.eq(aid)).fetchOne();
		return mapRecord.apply(record);
	}

	private SelectJoinStep<Record5<BigInteger, String, String, String, String>> getSelect() {
		return jooq.select(id, firstName, lastName, phone, email).from(table);
	}

	private static List<Contact> jooqResultToList(Result<Record5<BigInteger, String, String, String, String>> jooqResult) {
		return jooqResult.stream()
			.map(mapRecord)
			.toList();
	}

	private static final Function<Record5<BigInteger, String, String, String, String>, Contact> mapRecord =
		it -> new Contact(it.get(id), it.get(firstName), it.get(lastName), it.get(phone), it.get(email));

}
