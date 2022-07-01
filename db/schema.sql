create table if not exists accident_rule(
    id serial primary key,
    rule_name varchar(200)
);

create table if not exists accident_type(
    id serial primary key,
    type_name varchar(1000)
);

create table if not exists accident(
    id serial primary key,
    name varchar(2000),
    text varchar(2000),
    address varchar(2000),
    type_id int references accident_type(id)
);

create table if not exists accident_rules(
    accident_id int not null references accident(id),
    rule_id int not null references accident_rule(id)
);

insert into accident_type (type_name) values ('Машина и велосипед'), ('Машина и человек'),('Две машины');

insert into accident_rule (rule_name) values ('Статья 1'), ('Статья 2'), ('Статья 3');