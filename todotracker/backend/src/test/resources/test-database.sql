create table person (
    user_id serial not null,
    user_name varchar(64) unique	 not null,
    user_login varchar(16) not null,
    user_password varchar(16) not null,
    is_admin boolean,
    constraint pk_person primary key (user_id)
);

create table todo (
    todo_id serial not null,
    todo_name varchar(64) not null,
    user_id int not null,
    description varchar(256) not null,
    date_created date not null,
    date_completed date,
    is_complete boolean,
    constraint pk_todo primary key (todo_id),
    constraint fk_todo foreign key (user_id)
        references person (user_id)
);

insert into person (user_name, user_login, user_password, is_admin) values
('Alesha LeGair', 'alegair', 'alesha1011', true);
insert into person (user_name, user_login, user_password, is_admin) values
('Randy LeGair', 'rlegair', 'randy1207', true);

insert into todo (todo_name, user_id, description, date_created, is_complete) values
('Wash car', 1, 'Alesha needs to wash her car', '2024-11-01', false);
insert into todo (todo_name, user_id, description, date_created, date_completed, is_complete) values
('Clean house', 2, 'Randy need to clean the house', '2024-11-01', '2024-11-10', true);