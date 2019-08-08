create table test_medicineRecords (
    record_id int not null AUTO_INCREMENT,
    member_name varchar(255) not null,
    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    medicine varchar(255) not null,
    member_condition varchar(255) not null,
	PRIMARY KEY (record_id)
);