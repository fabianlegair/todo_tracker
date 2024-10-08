create table person (
    user_id serial not null,
    user_name varchar(64) unique	 not null,
    user_login varchar(16) not null,
    user_password varchar(16) not null,
    is_admin boolean,
    constraint pk_person primary key (user_id)
);

insert into person (user_name, user_login, user_password, is_admin) values
('Alesha LeGair', 'alegair', 'alesha1011', true);
insert into person (user_name, user_login, user_password, is_admin) values
('Randy LeGair', 'rlegair', 'randy1207', true);