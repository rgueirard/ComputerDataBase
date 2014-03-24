create table log (
	id	BIGINT NOT NULL auto_increment,
	computer_id BIGINT,
	time	TIMESTAMP,
	message	VARCHAR(255),
	constraint pk_computer primary key (id))
;