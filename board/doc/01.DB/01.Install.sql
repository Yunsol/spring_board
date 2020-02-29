create table BOARD(
	ID INT(11) unsigned NOT NULL AUTO_INCREMENT,
    TITLE varchar(255),
    CONTENT mediumtext,
    RGST_DATE datetime,
PRIMARY KEY(ID)
);



alter table board modify rgst_date datetime default now();


create table files(
	FILEID INT(11) unsigned NOT NULL AUTO_INCREMENT,
	BOARDID INT(11) NOT NULL,
	PATH varchar(255),
	FILE_NAME  varchar(255),
	FILE_SIZE BIGINT(20) NOT NULL DEFAULT '0',
	PRIMARY KEY(FILEID),
	FOREIGN KEY (BOARDID) REFERENCES BOARD (ID)
);