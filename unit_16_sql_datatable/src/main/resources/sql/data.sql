insert into departments values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DEVELOPMENT');
insert into departments values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'MANAGER');
insert into departments values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'SECURITY');

insert into employees values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Оксана', 'Візітів', 32);
insert into employees values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Валентина', 'Головко', 26);
insert into employees values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Антон', 'Дзюба', 32);
insert into employees values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Наталія', 'Мантрова', 18);

insert into dep_emp values (1, 1);
insert into dep_emp values (1, 2);
insert into dep_emp values (1, 3);
insert into dep_emp values (1, 4);
insert into dep_emp values (2, 4);
insert into dep_emp values (2, 1);
insert into dep_emp values (3, 2);
insert into dep_emp values (3, 3);


select id, created, updated, first_name, last_name, age from employees left join dep_emp de on de.emp_id = employees.id where de.dep_id = 2;
select id, created, updated, first_name, last_name, age from employees left join dep_emp de on de.emp_id = employees.id where de.dep_id = 1;
select id, created, updated, first_name, last_name, age from employees left join dep_emp de on de.emp_id = employees.id where de.dep_id = 1 and employees.age > 20;
select id, created, updated, first_name, last_name, age from employees left join dep_emp de on de.emp_id = employees.id where de.dep_id = 1 and employees.age > 20 and first_name like '%Ок%';
select id, created, updated, first_name, last_name, age from employees left join dep_emp de on de.emp_id = employees.id where de.dep_id = 1 order by employees.age desc;
select id, created, updated, first_name, last_name, age from employees left join dep_emp de on de.emp_id = employees.id where de.dep_id = 1 order by employees.age desc limit 1,2;
select id, created, updated, first_name, last_name, age from employees left join dep_emp de on de.emp_id = employees.id where de.dep_id = 1 limit 2,2;

select count(*) as count_of_employee from employees;
select sum(age) from employees;

select distinct(first_name) from employees;

select d.id, d.created, d.updated, d.department_type, count(dep_id) as employee_count
from departments as d left join dep_emp as de on d.id = de.dep_id group by d.id having d.id > 0;

select *
from departments as d left join dep_emp as de on d.id = de.dep_id where d.id in (1, 3);

select d.id, d.created, d.updated, d.department_type, count(dep_id) as employee_count
from departments as d left join dep_emp as de on d.id = de.dep_id where de.emp_id in (
    select e.id from employees as e where e.age > 20
) group by d.id;