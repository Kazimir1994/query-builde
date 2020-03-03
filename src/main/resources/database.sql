CREATE TABLE IF NOT EXISTS Gender(
id_gender bigint NOT NULL PRIMARY KEY,
sex VARCHAR(30) NOT NULL,
UNIQUE (id_gender),
UNIQUE (sex)
);

CREATE TABLE IF NOT EXISTS Summary(
id BIGINT NOT NULL PRIMARY KEY,
name VARCHAR(30) NOT NULL,
surname VARCHAR(30) NOT NULL,
patronymic VARCHAR(30) NOT NULL,
date_of_birth DATE NOT NULL,
gender_id BIGINT NOT NULL,
FOREIGN KEY (gender_id) REFERENCES Gender(id_gender),
UNIQUE (id)
);

CREATE TABLE IF NOT EXISTS Contacts(
id BIGINT NOT NULL PRIMARY KEY,
contact  VARCHAR(100) NOT NULL,
summary_id BIGINT NOT NULL,
FOREIGN KEY (summary_id) REFERENCES Summary(id),
UNIQUE (id),
UNIQUE (contact)
);

CREATE TABLE IF NOT EXISTS Technology(
id BIGINT NOT NULL PRIMARY KEY,
name  VARCHAR(100) NOT NULL,
UNIQUE (id),
UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS Summary_Technology(
summary_id BIGINT  NOT NULL,
technology_id BIGINT NOT NULL,
FOREIGN KEY (summary_id) REFERENCES Summary(id),
FOREIGN KEY (technology_id) REFERENCES Technology(id),
UNIQUE (summary_id,technology_id)
);

INSERT INTO Technology VALUES (1,'Git');
INSERT INTO Technology VALUES (2,'Spring Boot');
INSERT INTO Technology VALUES (3,'HTML');
INSERT INTO Technology VALUES (4,'JavaEE');
INSERT INTO Technology VALUES (5,'Java Core');
INSERT INTO Technology VALUES (6,'Spring');
INSERT INTO Technology VALUES (7,'REST');
INSERT INTO Technology VALUES (8,'Maven');

INSERT INTO Gender VALUES (1,'male');
INSERT INTO Gender VALUES (2,'female');


INSERT INTO Summary VALUES (1,'Петр','Петров','Петрович','12.12.1986',1);
INSERT INTO Summary_Technology VALUES(1,1);
INSERT INTO Summary_Technology VALUES(1,2);
INSERT INTO Summary_Technology VALUES(1,3);
INSERT INTO Contacts VALUES(1,'+375(29)123-45-67',1);
INSERT INTO Contacts VALUES(2,'http://github.com/petya',1);
INSERT INTO Contacts VALUES(3,'petrovich@gmail.com',1);

INSERT INTO Summary VALUES (2,'Иван','Иванов','Иванович','4.4.1997',1);
INSERT INTO Summary_Technology VALUES(2,1);
INSERT INTO Summary_Technology VALUES(2,4);
INSERT INTO Summary_Technology VALUES(2,5);
INSERT INTO Contacts VALUES(4,'+375(29)87-65-43',2);
INSERT INTO Contacts VALUES(5,'http://github.com/vanya',2);
INSERT INTO Contacts VALUES(6,'skype:ivanko',2);

INSERT INTO Summary VALUES (3,'Мария','Морская','Васильевна','07.11.1999',2);
INSERT INTO Summary_Technology VALUES(3,8);
INSERT INTO Summary_Technology VALUES(3,7);
INSERT INTO Summary_Technology VALUES(3,6);
INSERT INTO Contacts VALUES(7,'+375(29)999-99-99',3);
INSERT INTO Contacts VALUES(8,'https://www.linkedin.com/in/mariya/',3);