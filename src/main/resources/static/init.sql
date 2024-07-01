create database homeheaven;

use homeheaven


create table role(
	role_id bigint identity(1,1) primary key,
	role_name varchar(20) not null unique

);

create table users(
	user_id bigint identity(1,1) primary key,
	email varchar(255) not null,
	username varchar(20) not null,
	user_password varchar(255) not null,
	first_name varchar(20) not null,
	last_name varchar(20) not null,
	user_role varchar(20) not null,
	constraint fk_user_role foreign key (user_role) references role(role_name)
);

CREATE TABLE products (
    product_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    product_name NVARCHAR(100) NOT NULL,
    product_description NVARCHAR(MAX),
    image_url NVARCHAR(100),
    price DECIMAL(10, 2) NOT NULL,
    category NVARCHAR(100) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    seller_id BIGINT,
    CONSTRAINT fk_seller FOREIGN KEY (seller_id) REFERENCES users(user_id)
);


CREATE TABLE orders (
    order_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    buyer_id BIGINT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_buyer FOREIGN KEY (buyer_id) REFERENCES users(user_id)
);


CREATE TABLE product_order (
    product_order_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(order_id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(product_id)
);






-- Producto 1: Aspiradora Robot
INSERT INTO products (product_name, product_description, image_url, price, category, stock, seller_id)
VALUES ('Aspiradora Robot', 'Aspiradora robot inteligente con programaci�n y control remoto.', 'http://example.com/images/aspiradora_robot.jpg', 299.99, 'Hogar', 20, 10014);

-- Producto 2: Cafetera de Goteo
INSERT INTO products (product_name, product_description, image_url, price, category, stock, seller_id)
VALUES ('Cafetera de Goteo', 'Cafetera de goteo con temporizador programable y jarra t�rmica.', 'http://example.com/images/cafetera_goteo.jpg', 89.99, 'Hogar', 35, 10014);

-- Producto 3: Humidificador Ultras�nico
INSERT INTO products (product_name, product_description, image_url, price, category, stock, seller_id)
VALUES ('Humidificador Ultras�nico', 'Humidificador ultras�nico de gran capacidad para mejorar la calidad del aire.', 'http://example.com/images/humidificador_ultrasonico.jpg', 49.99, 'Hogar', 50, 10014);

insert into role (role_name) values ('BUYER')
insert into role (role_name) values ('SELLER')
insert into role (role_name) values ('ADMIN')

select * from users


select * from products
delete from users where user_id = 2

select * from product_order

select * from orders

delete from orders where total = 0