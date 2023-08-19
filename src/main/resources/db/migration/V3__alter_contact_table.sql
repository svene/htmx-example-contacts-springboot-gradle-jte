alter table CONTACT
add column phone varchar(100),
add column email varchar(100)
;

update contact set phone = '0157 1234567' where contact.phone is null;
update contact set email = 'my.email@mailit.org' where contact.email is null;
