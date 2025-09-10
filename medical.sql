CREATE TABLE attendance_Detail(
		user_id CHAR(6),
		c_code VARCHAR(10),
		c_hours INT,
		lec_date DATE,
		at_state VARCHAR(10),
		c_type Char(1),
		PRIMARY KEY(user_id, c_code, lec_date, c_type),
		FOREIGN KEY(user_id) REFERENCES user(user_id)
		);


--------------------------------
Medical Table
--------------------------------

CREATE TABLE Medical
(Medical_id CHAR(3),
ST_Id CHAR(6),
Description VARCHAR(40),
Sub_date DATE,
State VARCHAR(30),
c_code char(7),
c_type CHAR(1),
cut_lec_hour  int,
PRIMARY KEY(Medical_id,c_code,c_type),
FOREIGN KEY(user_id) REFERENCES user(user_id),
FOREIGN KEY(c_code) REFERENCES course_units(c_code));


INSERT INTO Medical VALUES
('M01','TG1340','Matara hospital','2025-04-04','approval','ICT2152','T',4),
('M02','TG1341','Ruhunugama hospital','2025-03-27','approval','ICT2142','P',4),
('M02','TG1341','Ruhunugama hospital','2025-03-27','approval','ICT2122','T',4),
('M03','TG1341','Ruhunugama hospital','2025-04-10','not approval','ICT2142','P',4),
('M03','TG1341','Ruhunugama hospital','2025-04-10','not approval','ICT2122','T',4),
('M04','TG1342','Kamburupitiya hospital','2025-03-13','approval','ICT2142','P',4),
('M05','TG1342','Kamburupitiya hospital','2025-02-18','approval','ICT2133','P',2),
('M06','TG1347','Dickoya hospital','2025-01-30','approval','ICT2142','P',4),
('M07','TG1347','Dickoya hospital','2025-03-20','approval','ICT2122','T',4),
('M08','TG1347','Karapitiya hospital','2025-04-04','approval','ICT2152','T',4),
('M09','TG1353','Matara hospital','2025-03-28','not approval','ICT2152','T',4),
('M10','TG1353','Matara hospital','2025-04-25','approval','ICT2152','T',4),
('M11','TG1356','Ruhunugama hospital','2025-01-30','approval','ICT2122','T',4),
('M12','TG1356','Ruhunugama hospital','2025-03-20','not approval','ICT2122','T',4),
('M13','TG1360','Matara hospital','2025-03-20','approval','ICT2142','P',4),
('M13','TG1360','Matara hospital','2025-03-20','approval','ICT2122','T',4),
('M14','TG1362','Karapitiya hospital','2025-03-10','approval','ICT2113','P',4),
('M14','TG1362','Karapitiya hospital','2025-03-10','approval','ICT2113','T',2),
('M15','TG1363','Ruhunugama hospital','2025-03-10','not approval','ICT2113','T',4),
('M16','TG1363','Matara hospital','2025-02-11','approval','ICT2133','T',4),
('M17','TG1363','Matara hospital','2025-02-14','approval','ICT2152','T',4);












