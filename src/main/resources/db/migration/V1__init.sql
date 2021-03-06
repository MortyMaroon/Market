create table products (
    id                  bigserial primary key,
    title               varchar(255),
    price               int,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

insert into products (title, price)
values
('milk', 85),
('chocolate', 120),
('bread', 35),
('sausage', 400),
('meat', 1500),
('beer', 80),
('wine', 600),
('oil', 260),
('salt', 100),
('sugar', 55),
('cookies', 149),
('lemonade', 135),
('nuts', 900),
('soap', 88),
('tomato', 463),
('cucumber', 28),
('orange', 99),
('flour', 870),
('pasta', 132),
('powder', 70);