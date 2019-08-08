create table Users (
	user_id int not null AUTO_INCREMENT,
    user_email varchar(255) not null,
    user_password varchar(255) not null,
    username varchar(255) not null,
    phone_number varchar(255) not null,
	PRIMARY KEY (user_id)
);