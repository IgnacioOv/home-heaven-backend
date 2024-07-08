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







-- Producto 2: Cafetera de Goteo
INSERT INTO products (product_name, product_description, image_url, price, category, stock, seller_id)
VALUES ('Cafetera de Goteo', 'Cafetera de goteo con temporizador programable y jarra t�rmica.', 'https://orbegozo.com/wp-content/uploads/2019/03/cafetera-de-goteo-orbegozo-CG-4024_1.jpg', 89.99, 'kitchen', 35, 3);


-- Producto 1: Toallero Eléctrico
INSERT INTO products (product_name, product_description, image_url, price, category, stock, seller_id)
VALUES ('Toallero Eléctrico', 'Toallero eléctrico con temporizador programable y protección contra sobrecalentamiento.', 'https://example.com/images/toallero-electrico.jpg', 129.99, 'bathroom', 20, 3);

-- Producto 2: Espejo LED para Baño
INSERT INTO products (product_name, product_description, image_url, price, category, stock, seller_id)
VALUES ('Espejo LED para Baño', 'Espejo para baño con iluminación LED y función anti-vaho.', 'https://example.com/images/espejo-led-bano.jpg', 99.99, 'bathroom', 15, 3);

-- Producto 3: Cojín Decorativo
INSERT INTO products (product_name, product_description, image_url, price, category, stock, seller_id)
VALUES ('Cojín Decorativo', 'Cojín decorativo con diseño moderno y relleno de espuma viscoelástica.', 'https://example.com/images/cojin-decorativo.jpg', 24.99, 'decoration', 50, 3);

-- Producto 4: Lámpara de Pie Vintage
INSERT INTO products (product_name, product_description, image_url, price, category, stock, seller_id)
VALUES ('Lámpara de Pie Vintage', 'Lámpara de pie con diseño vintage y bombilla de estilo Edison.', 'https://example.com/images/lampara-pie-vintage.jpg', 79.99, 'living', 30, 3);

-- Producto 5: Colcha para Cama
INSERT INTO products (product_name, product_description, image_url, price, category, stock, seller_id)
VALUES ('Colcha para Cama', 'Colcha para cama de matrimonio con diseño elegante y tela de alta calidad.', 'https://example.com/images/colcha-cama.jpg', 59.99, 'bedroom', 25, 3);

-- Producto 6: Estantería Modular
INSERT INTO products (product_name, product_description, image_url, price, category, stock, seller_id)
VALUES ('Estantería Modular', 'Estantería modular con múltiples configuraciones y acabados en madera natural.', 'https://example.com/images/estanteria-modular.jpg', 149.99, 'living', 40, 3);


insert into role (role_name) values ('BUYER')
insert into role (role_name) values ('SELLER')
insert into role (role_name) values ('ADMIN')

select * from users


delete from product_order
delete from products

select * from products
delete from users where user_id = 2

select * from product_order

select * from orders

delete from orders where total = 0

