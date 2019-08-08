create table test_contact (
    contact_id int not null AUTO_INCREMENT,
    contact_name varchar(255) not null,
    relationship varchar(255) not null,
    email varchar(255) not null,
    phone_number varchar(255) not null,
	PRIMARY KEY (contact_id)
);