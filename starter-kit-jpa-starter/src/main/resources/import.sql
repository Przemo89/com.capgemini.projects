insert into book (id, title) values (1, 'Pierwsza książka');
insert into book (id, title) values (2, 'Druga książka');
insert into book (id, title) values (3, 'Trzecia książka');

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (1, 8);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);

insert into departaments (id, name, email, phone_stationary, phone_mobile) values ('1', 'Human Resources', 'hr@company.com','713444567','670890432');
insert into departaments (id, name, email, phone_stationary, phone_mobile) values ('2', 'Public relations', 'pr@company.com','713444568','670890433');
insert into departaments (id, name, email, phone_stationary, phone_mobile) values ('3', 'Research and Development', 'rad@company.com','713444569','670890434');
insert into departaments (id, name, email, phone_stationary, phone_mobile) values ('4', 'Accounting and Finance', 'af@company.com','713444570','670890435');
insert into departaments (id, name, email, phone_stationary, phone_mobile) values ('5', 'Production', 'prod@company.com','713444571','670890436');
insert into departaments (id, name, email, phone_stationary, phone_mobile) values ('6', 'Purchasing', 'pur@company.com','713444572','670890437');

insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('1','1','John','Walker','56061256439','1956-06-12','john_walker@company.com','713544500','772345321');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('2','1','Johny','Walkerz','84111256439','1984-11-12','johny_walkerz@company.com','713544501','772345322');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('3','3','Genowefa','Prozna','79063056439','1979-06-30','genowefa_prozna@company.com','713544502','772345323');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('4','1','Johnny','Walkersss','49061256439','1949-06-12','johnny_walkersss@company.com','713544503','772345324');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('5','4','Johny','Bravo','99033056439','1999-03-30','johny_bravo@company.com','713544504','772345325');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('6','4','Jessica','Bravo','87022056439','1987-02-20','jessica_bravo@company.com','713544505','772345326');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('7','2','Barbara','Nolife','33056439','2000-03-30','barbara_nolife@company.com','713544506','772345327');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('8','3','Samantha','Painless','76033056637','1976-03-30','samantha_painless@company.com','713544507','772345328');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('9','5','Stanislaw','Boczny','77033086637','1977-03-30','stanislaw_boczny@company.com','713544508','772345329');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('10','5','Bronek','Poznanski','67033056437','1967-03-30','bronek_poznanski@company.com','713544509','772345330');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('11','5','Sylwia','Apoznanska','88011056637','1988-01-10','sylwia_apoznanska@company.com','713544510','772345331');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('12','3','Abraham','Abrahamski','54021054637','1954-02-10','abraham_abrahamski@company.com','713544511','772345332');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('13','6','Harry','Bob','70021054637','1970-02-10','harry_bob@company.com','713544512','772345333');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('14','6','Dante','Alighieri','67021054637','1967-02-10','dante_alighieri@company.com','713544513','772345334');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('15','4','Danuta','Piasek','85021054637','1985-02-10','danuta_piasek@company.com','713544514','772345335');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('16','2','Frank','Pfang','67021054527','1967-02-10','frank_pfang@company.com','713544515','772345336');
insert into employees (id, id_departament, first_name, last_name, PIN, birth_date, email, phone_stationary, phone_mobile) values('17','6','Mark','oFang','53011712637','1953-01-17','mark_ofang@company.com','713544516','772345337');

insert into projects (id, id_manager, name, is_internal) values ('1', '2', 'Communication', '1');
insert into projects (id, id_manager, name, is_internal) values ('2', '14', 'Javascript Project', '1');
insert into projects (id, id_manager, name, is_internal) values ('3', '4', 'Bank System', '1');
insert into projects (id, id_manager, name, is_internal) values ('4', '7', 'Logistic System', '0');
insert into projects (id, id_manager, name, is_internal) values ('5', '12', 'Database System', '1');
insert into projects (id, id_manager, name, is_internal) values ('6', '15', 'Java Project', '0');

insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('3' , '1', '2001-03-30', '2002-02-26', 'PL', '100.25');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('3' ,'1', '2002-02-27', '2003-01-23', 'DEV', '200.25');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('3' ,'5', '2001-01-15', '2005-05-26', 'TCD', '1100.25');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('3' ,'4', '2003-04-15', '2007-05-26', 'FCD', '1100.25');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('5' ,'1', '2005-01-30', '2007-08-26', 'FCD', '150.75');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('7' ,'2', '2003-08-30', '2007-08-26', 'DEV', '350.64');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('7' ,'3', '2005-08-30', '2010-08-26', 'PL', '245.90');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('9' ,'3', '2001-01-30', '2010-09-26', 'FCD', '545.90');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('9' ,'3', '2010-09-30', '2015-04-29', 'DEV', '645.90');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('9' ,'6', '2010-10-30', '2012-04-29', 'TCD', '234.56');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('10' ,'5', '2008-10-30', '2012-04-29', 'PL', '1234.56');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('11' ,'4', '2006-10-30', '2012-04-29', 'TCD', '432.56');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('11' ,'4', '2013-10-30', '2015-04-29', 'TCD', '450.56');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('13' ,'1', '2014-03-30', '2015-03-26', 'PL', '250.25');
insert into employees_projects (id_employee, id_project, hire_date, termination_date, employee_function, daily_salary) values ('13' ,'5', '2013-03-30', '2016-05-16', 'PL', '370.25');
insert into employees_projects (id_employee, id_project, hire_date, employee_function, daily_salary) values ('8' ,'4', '2014-03-30', 'TCD', '380.25');
insert into employees_projects (id_employee, id_project, hire_date, employee_function, daily_salary) values ('8' ,'3', '2012-09-02', 'DEV', '350.25');
insert into employees_projects (id_employee, id_project, hire_date, employee_function, daily_salary) values ('8' ,'3', '2014-09-02', 'TCD', '350.25');
insert into employees_projects (id_employee, id_project, hire_date, employee_function, daily_salary) values ('3' ,'1', '2012-09-02', 'PL', '178.25');
insert into employees_projects (id_employee, id_project, hire_date, employee_function, daily_salary) values ('13' ,'3', '2014-03-30', 'TCD', '420.25');