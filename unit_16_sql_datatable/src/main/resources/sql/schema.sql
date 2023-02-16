create table departments
(
    id bigint auto_increment primary key,
    created datetime(6) null,
    updated datetime(6) null,
    department_type varchar(255) not null
);

create table employees
(
    id bigint auto_increment primary key,
    created datetime(6) null,
    updated datetime(6) null,
    first_name varchar(255) null,
    last_name varchar(255) null,
    age int null
);

create table dep_emp
(
    dep_id bigint not null,
    emp_id bigint not null,
    primary key (dep_id, emp_id),
    foreign key (dep_id) references departments(id),
    foreign key (emp_id) references employees(id)
);
