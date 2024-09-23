begin transaction;

drop table if exists user, todo, user_todo;

create table user (
    user_id serial not null,
    user_name varchar(64) not null,
    user_login varchar(16) unique not null,
    user_password varchar(16) not null,
    is_admin boolean,
    constraint pk_user primary key (user_id)
);

insert into user (user_name, user_login, user_password, is_admin) values
('Fabian LeGair', 'legair', 'admin1127', true);

create table todo (
    todo_id serial not null,
    todo_name varchar(64) not null,
    user_name varchar(64) unique not null,
    description(256) varchar not null,
    date_created date not null,
    date_completed date,
    is_complete boolean,
    constraint pk_todo primary key (todo_id),
    constraint fk_todo_user_todo foreign key (user_name)
        references user_todo (user_name)
);

insert into todo (todo_name, user_name, description, date_created, date_completed, is_complete) values 
('Design database', 'Fabian LeGair', 'Design database for project', '2024-09-23', '2024-09-23', true),
('Create database', 'Fabian LeGair', 'Create database for project', '2024-09-23', '2024-09-23', true);


create table user_todo (
    user_todo_id serial not null,
    user_id int not null,
    user_name varchar(64) unique not null,
    todo_id integer not null,
    constraint pk_user_todo primary key (user_todo_id),
    constraint fk_user_todo_id foreign key (user_id)
        references user (user_id),
    constraint fk_user_todo_user foreign key (user_name)
        references user (user_name),
    constraint fk_user_todo_todo foreign key (todo_id)
        references todo (todo_id)
);

insert into user_todo (user_id, user_name, todo_id) values
(1, 'Fabian LeGair', 1),
(1, 'Fabian LeGair', 2);

commit transaction;