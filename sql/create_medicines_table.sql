create table test_medicines (
    rule_id int not null AUTO_INCREMENT,
    member_name varchar(255) not null,
    alert_time varchar(255) not null,
    box_1 varchar(20) not null,
    box_2 varchar(20) not null,
    box_3 varchar(20) not null,
    box_4 varchar(20) not null,
    box_5 varchar(20) not null,
    medicine_1 varchar(255),
    medicine_2 varchar(255),
    medicine_3 varchar(255),
    medicine_4 varchar(255),
    medicine_5 varchar(255),
	PRIMARY KEY (rule_id)
);