create table test_fallRecords (
    record_id int not null AUTO_INCREMENT,
    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    fall_condition varchar(255) not null,
	PRIMARY KEY (record_id)
);