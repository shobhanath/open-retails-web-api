create sequence or_profile_hibernate_seq start 1 increment 1
create table address (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, address_free_text varchar(255) not null, comment varchar(255) not null, primary key (entity_id))
create table roles (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, name varchar(255) not null, primary key (entity_id))
create table users (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, dob timestamp not null, comment varchar(255) not null, first_name varchar(255) not null, gender varchar(255) not null, is_email_verified boolean not null, is_mobile_verified boolean not null, last_name varchar(255) not null, middle_name varchar(255) not null, password varchar(60) not null, primary_email_id varchar(255) not null, primary_mobile_number int8 not null, salary float8 not null, title varchar(255) not null, user_type int4 not null, user_name varchar(255) not null, permanent_address_id int8, secondary_address_id int8, primary key (entity_id))
create table users_roles (user_id int8 not null, role_id int8 not null, primary key (user_id, role_id))
alter table users add constraint UKkkfprsw1l62be2hc6qvs953dp unique (primary_email_id)
alter table users add constraint UK5kc281qc17ploo981qfhowgk7 unique (user_name)
alter table users add constraint UK_tk96o881wh6rcpqsx96vh7hkn unique (permanent_address_id)
alter table users add constraint UK_rru6w4i1dp2yx0w2ntowgxlsp unique (secondary_address_id)
alter table users_roles add constraint UK_k2mq1ee4ob6uw649wgaus1ate unique (role_id)
alter table users add constraint FK2tyqw1a1c51br1ot3xt28o0i3 foreign key (permanent_address_id) references address
alter table users add constraint FK3m5wntbub0ujjpfojla3m1eoe foreign key (secondary_address_id) references address
alter table users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles
alter table users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users
