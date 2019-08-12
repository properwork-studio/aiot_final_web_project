create table test_environment (
    record_id int not null AUTO_INCREMENT,
    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    temperature varchar(100),
    humidity varchar(100),
    co varchar(100),
	PRIMARY KEY (record_id)
) DEFAULT CHARSET=utf8;