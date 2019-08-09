create table test_fallRecords (
    record_id int not null AUTO_INCREMENT,
    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    fall_condition varchar(255) not null,
    room varchar(100),
	PRIMARY KEY (record_id)
) DEFAULT CHARSET=utf8;