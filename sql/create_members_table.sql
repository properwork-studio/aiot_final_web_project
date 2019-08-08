create table test_members (
    member_id int not null AUTO_INCREMENT,
    member_name varchar(255) not null,
    member_code varchar(255) not null,
    birthday varchar(255) not null,
    id_number varchar(255) not null,
    member_photo varchar(255) not null,
	PRIMARY KEY (member_id)
);