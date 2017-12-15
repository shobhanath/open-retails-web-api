create sequence or_profile_hibernate_seq start 1 increment 1
create table offers (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, primary key (entity_id))
create table prod_categories (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, primary key (entity_id))
create table products (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, primary key (entity_id))
create table stocks (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, primary key (entity_id))
