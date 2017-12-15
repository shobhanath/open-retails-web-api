create sequence or_stock_hibernate_seq start 1 increment 1
create table offer_types (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, name varchar(255), percentage_discount float8, primary key (entity_id))
create table offers (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, offer_type_id int8 not null, prod_id int8 not null, primary key (entity_id))
create table prod_categories (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, gst float8, name varchar(255), primary key (entity_id))
create table products (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, bar_code varchar(255), name varchar(255), prod_category_id int8 not null, primary key (entity_id))
create table stocks (entity_id int8 not null, created timestamp not null, modified timestamp, obsolete boolean not null, buying_price int8, mrp int8, quantity int8, prod_id int8 not null, primary key (entity_id))
alter table offers add constraint FKnbx2yjxkl8bjt9o2mn8s2r1jd foreign key (offer_type_id) references offer_types
alter table offers add constraint FKiqvwvmm6vay0fge88mnk9a333 foreign key (prod_id) references products
alter table products add constraint FKfa3wlc38pr7sw6fb4fowmx4u3 foreign key (prod_category_id) references prod_categories
alter table stocks add constraint FKfeensq1e12qc62r5b1mj0uwmq foreign key (prod_id) references products
