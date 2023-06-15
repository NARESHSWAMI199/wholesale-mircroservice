

# **Swami Sales**


## Tables




* Users :

    | id | slug | status |username | email | password | first_name | last_name | bio | address_id | mobile | profile_pic | user_type | address_id | created_at |  updated_at | created_by | updated_by | is_deleted |
    |---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
    |1|abcd|true|naresh|nareshswami2334@gmail.com|123456|naresh|swami|hello jio|19145808226|hello.png|A|1||---|---|---|---|
 

> **_NOTE :_**  Currently we are not use following or followers controls.

    
* Store :

    | id | slug | user_id |store_name | image| store_category | rating | description | address_id | status | created_at | updated_at | created_by | updated_by | is_deleted |
    |---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|

  
* Items : 

    | id | slug | store_id |item_name | image| price |discount| status| in_stock |item_category | rating | description | created_at | updated_at | created_by | updated_by | is_deleted |
    |---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|



* Items Comments

    | id | user_id | item_id | message | created_by |  created_at |  updated_at  | updated_by | is_deleted |
    |---|---|---|---|---|---|---|---|---|



* Notification

    | id  | title | body | html | sender_id |reciver_id| status | sender_ip | created_at | created_by |is_deleted |
    |---|---|---|---|---|---|---|---|---|---|---|
    



* Slips

    | id | user_id | store_id | item_id | quantity | total_price | created_at | created_by | is_deleted |
    |---|---|---|---|---|---|---|---|---|
    
    
* Documents

    | id | user_id | image | first_name | last_name | dob | status | created_at | created_by |  updated_at  | updated_by | is_deleted |
    |---|---|---|---|---|---|---|---|---|---|---|---|
    
* group
    
    | id | name | status | created_at | created_by | updated_at  | updated_by | is_deleted |
    |---|---|---|---|---|---|---|---|


* User group permission
    | user_id | group_id |
    |---|---|


*  User permmission
    | id | dispaly_name | table_name | url 
    |---|---|---|---|

    



```Sql


create database swami_sales;

use swami_sales;

-- user table 

create table user (
	id int primary key auto_increment not null,
	slug varchar(25) not null ,
	username varchar(20),
	email varchar(20),
	password varchar(50),
	first_name varchar(20),
	last_name varchar(20),
	bio text,
	address_id  integer,
	mobile varchar(12),
	profile_pic text,
	user_type enum('R','W','A'),
    status boolean,
	updated_at bigint,
	created_at bigint,
	updated_by integer,
	created_by integer,
	is_deleted boolean
);



-- Store
create table stores (
	id int primary key auto_increment not null,
	slug varchar(25) not null ,
	user_id integer,
	store_name varchar(20),
	image varchar(50),
	store_category varchar(20),
	rating varchar(20),
	description text,
	address_id  integer,
	status boolean,
	updated_at bigint,
	created_at bigint,
	updated_by integer,
	created_by integer,
	is_deleted boolean
);



-- items
create table items (
	id int primary key auto_increment not null,
	slug varchar(25) not null ,
	store_id varchar(20),
	name varchar(20),
	image varchar(50),
	price float,
	discount float,
	description text,
    status boolean,
	in_stock  integer,
	item_category boolean,
	rating bigint,
    updated_at bigint,
	created_at bigint,
	updated_by integer,
	created_by integer,
	is_deleted boolean
);


-- item_comments
create table item_comments (
	id int primary key auto_increment not null,
	user_id integer,
	item_id integer,
	message varchar(50),
    updated_at bigint,
	created_at bigint,
	updated_by integer,
	created_by integer,
	is_deleted boolean
);




-- notifications
create table notifications (
	id int primary key auto_increment not null,
	title varchar(20),
	body varchar(20),
	html varchar(50),
    sender_id integer,
	reciver_id integer,
    sender_ip varchar(20),
	status boolean,
	created_at bigint,
	created_by integer,
	is_deleted boolean
);

-- slips

create table slips (
	id int primary key auto_increment not null,
	user_id integer,
	store_id integer,
	item_id integer,
	quantity integer,
    total_price integer,
	status boolean,
    updated_at bigint,
	created_at bigint,
	updated_by integer,
	created_by integer,
	is_deleted boolean
);


-- groups
create table `groups` (
	id int primary key auto_increment not null,
	name integer,
	status boolean,
    updated_at bigint,
	created_at bigint,
	updated_by integer,
	created_by integer,
	is_deleted boolean
);

-- user group permmision
create table group_permmision (
	user_id integer,
	name integer
);


-- user permmision
create table user_permmision (
	id int primary key auto_increment not null,
	display_name varchar(30),
    table_name varchar(50),
    url text
);

```