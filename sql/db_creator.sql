create database play_base;

use play_base;

create table users(user_id int primary key auto_increment,
				   user_login varchar(15) not null,
                   user_password varchar(15) not null,
                   user_email varchar(15) not null,
                   user_phone varchar(12) not null,
                   user_role enum('user', 'courier', 'admin') not null);

create table authors(author_id int primary key auto_increment,
					 author_name varchar(30) not null);

create table genres(genre_id int primary key auto_increment,
					 genre_name varchar(20) not null);

create table plays(play_id int primary key auto_increment,
				   play_name varchar(30) not null,
                   play_img varchar(100),
                   play_description text not null,
                   author_id int not null,
                   genre_id int not null,
                   foreign key(author_id) references authors(author_id) on delete cascade,
                   foreign key(genre_id) references genres(genre_id) on delete cascade);
                    
create table dates(date_id int primary key auto_increment,
				   date_day date not null,
                   play_id int not null,
                   foreign key(play_id) references plays(play_id));
                   
create table orders(order_id int primary key auto_increment,
					category enum('parterre', 'balcony') not null,
                    quantity int not null,
                    date_id int not null,
                    user_id int not null,
                    foreign key(date_id) references dates(date_id) on delete cascade,
                    foreign key(user_id) references users(user_id) on delete cascade);
                   
                   