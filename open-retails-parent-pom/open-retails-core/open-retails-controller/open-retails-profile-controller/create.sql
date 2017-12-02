create sequence or_profile_hibernate_seq start 1 increment 1
create table acl_profile (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, description varchar(255), maximum_failed_logins int4, maximum_password_age int4, maximum_password_length int4, minimum_lowercase_letters int4, minimum_number_characters int4, minimum_password_age int4, minimum_password_length int4, minimum_password_reuse int4, minimum_special_characters int4, minimum_uppercase_letters int4, name varchar(255), password_change_warn int4, password_grace_period int4, special_characters varchar(255), version int4, primary key (entity_id))
create table t_address (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, address_free_text varchar(255) not null, city varchar(255) not null, comment varchar(255) not null, country_code varchar(255) not null, country_name varchar(255) not null, pin_code int8 not null, primary key (entity_id))
create table t_api_info (entity_id int8 not null, http_method int4 not null, role_name bytea not null, service_class_name varchar(255) not null, service_method_name varchar(255) not null, service_uri varchar(255) not null, primary key (entity_id))
create table t_profile_access (entity_id int8 not null, profile_name int4 not null, primary key (entity_id))
create table t_profile_api_info (entity_id int8 not null, api_info_id int8, profile_access_id int8, primary key (entity_id))
create table t_role (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, name varchar(255) not null, primary key (entity_id))
create table t_user (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, age int4 not null, comment varchar(255) not null, first_name varchar(255) not null, gender varchar(255), is_email_verified boolean not null, is_mobile_verified boolean not null, last_name varchar(255) not null, locale varchar(255) not null, middle_name varchar(255) not null, password varchar(60) not null, primary_email_id varchar(255) not null, primary_mobile_number int8 not null, salary float8 not null, secondary_email_id varchar(255) not null, secondary_mobile_number int8 not null, title varchar(255) not null, user_name varchar(255) not null, permanent_address_id int8, secondary_address_id int8, user_type_id int8, primary key (entity_id))
create table t_user_profile_access (entity_id int8 not null, profile_api_info_id int8, user_id int8, primary key (entity_id))
create table t_user_role (user_id int8 not null, role_id int8 not null, primary key (user_id, role_id))
create table t_user_type (entity_id int8 not null, created timestamp not null, modified timestamp not null, obsolete boolean not null, comment varchar(255), name varchar(255) not null, primary key (entity_id))
alter table t_user_role add constraint UK_4uvv76e86ms8ru0kk9s01d3s2 unique (role_id)
alter table t_user_type add constraint UK_7yjsydj57kggp9hs0j1b6wu5q unique (name)
alter table t_profile_api_info add constraint FKckumw4akct4m09774flwda2ed foreign key (api_info_id) references t_api_info
alter table t_profile_api_info add constraint FKsq37g8ij0ev843989s117dimj foreign key (profile_access_id) references t_profile_access
alter table t_user add constraint FKaxy7rigb6vv71kvf8s5e6e785 foreign key (permanent_address_id) references t_address
alter table t_user add constraint FK5xj1xkwv8h4gm6tuyyfuwx6lj foreign key (secondary_address_id) references t_address
alter table t_user add constraint FK4tla7e9a83mpa0wikirkje6q8 foreign key (user_type_id) references t_user_type
alter table t_user_profile_access add constraint FK8fettdj7bfy27dql1ononohdu foreign key (profile_api_info_id) references t_profile_api_info
alter table t_user_profile_access add constraint FKtipbd4930q710ytd0b8y6i6xr foreign key (user_id) references t_user
alter table t_user_role add constraint FKa9c8iiy6ut0gnx491fqx4pxam foreign key (role_id) references t_role
alter table t_user_role add constraint FKq5un6x7ecoef5w1n39cop66kl foreign key (user_id) references t_user
