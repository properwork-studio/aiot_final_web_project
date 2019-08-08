create table test_DoorRecords (
    record_id int not null AUTO_INCREMENT,
    member_name varchar(255) not null,
    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    door_condition varchar(255) not null,
    real_fake varchar(255),
	PRIMARY KEY (record_id)
) DEFAULT CHARSET=utf8;