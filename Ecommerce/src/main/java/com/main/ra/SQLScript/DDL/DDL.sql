CREATE DATABASE ecommerce;

USE ecommerce;

# users table
CREATE TABLE users
(
    user_id    bigint PRIMARY KEY AUTO_INCREMENT COMMENT 'ユーザーID',
    user_name  varchar(100)     NOT NULL COMMENT 'ユーザー名前',
    email      varchar(255)              COMMENT '電子メール',
    full_name  varchar(100)     NOT NULL COMMENT '氏名',
    status     bit default 1             COMMENT 'ユーザー状態',
    password   varchar(255)     NOT NULL COMMENT '暗証番号',
    avatar     varchar(255)              COMMENT 'アバター',
    phone      varchar(15)               COMMENT '電話番号',
    address    varchar(255)     NOT NULL COMMENT '住所',
    created_at date                      COMMENT '作成日付',
    updated_at date                      COMMENT '更新日付'
);
# users checker
ALTER TABLE users
    ADD CONSTRAINT chk_user_name_length_users CHECK(length(user_name) >= 6);
ALTER TABLE users
    ADD CONSTRAINT chk_user_name_fmt_users CHECK(user_name NOT REGEXP '[^a-zA-Z0-9_]');
ALTER TABLE users
    ADD CONSTRAINT chk_email_fmt_users CHECK(email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+.[a-z]+.[a-z]');
ALTER TABLE users
    ADD CONSTRAINT chk_phone_fmt_users CHECK(phone REGEXP '^0[1-9]+[0-9]{8}$');
# user trigger
DELIMITER //
CREATE TRIGGER tgr_create_date_user_datestamp
    BEFORE INSERT ON users FOR EACH ROW
    BEGIN
        SET NEW.created_at = CURRENT_DATE;
    END;
// DELIMITER ;

DELIMITER //
CREATE TRIGGER tgr_update_date_user_datestamp
    BEFORE UPDATE ON users FOR EACH ROW
    BEGIN
        SET NEW.updated_at = CURRENT_DATE;
    END;
// DELIMITER ;

# role table
CREATE TABLE roles(
    role_id   bigint PRIMARY KEY AUTO_INCREMENT COMMENT '許可ID',
    role_name enum('ROLE_ADMIN', 'ROLE_USER')   COMMENT '許可'
);

# user_role
CREATE TABLE user_roles(
    id bigint PRIMARY KEY AUTO_INCREMENT COMMENT 'ユーザー許可ID',
    user_id bigint NOT NULL COMMENT 'ユーザーID',
    role_id bigint NOT NULL COMMENT '許可ID',
    status bit default 1 COMMENT 'ユーザー許可状態'
);
DROP TABLE user_roles;
# user_role FOREIGN KEY
ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_role_id
        FOREIGN KEY (role_id) REFERENCES roles (role_id);
ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_user_id
        FOREIGN KEY (user_id) REFERENCES users (user_id);

# categories table
CREATE TABLE categories(
    category_id   bigint PRIMARY KEY AUTO_INCREMENT COMMENT 'カテゴリーID',
    category_name varchar(100)     NOT NULL         COMMENT 'カテゴリー名前',
    description   text                              COMMENT '説明',
    status        bit default 1                     COMMENT 'カテゴリー状態'
);

# products table
CREATE TABLE products
(
    product_id     bigint PRIMARY KEY AUTO_INCREMENT        COMMENT '商品ID',
    sku            varchar(100)   UNIQUE DEFAULT (uuid())   COMMENT '商品コード',
    product_name   varchar(100)   NOT NULL  UNIQUE          COMMENT '商品名前',
    description    text                                     COMMENT '説明',
    unit_price     decimal(10, 2)                           COMMENT 'ユニット価格',
    stock_quantity int                                      COMMENT '在庫数量',
    image          varchar(255)                             COMMENT '写真',
    category_id    bigint                                   COMMENT 'カテゴリーID',
    created_at     date                                     COMMENT '作成日付',
    updated_at     date                                     COMMENT '更新日付'
);

#
ALTER TABLE products
    ADD CONSTRAINT fk_products_category_id
        FOREIGN KEY (category_id) REFERENCES categories (category_id);
# products checkers
ALTER TABLE products
    ADD CONSTRAINT chk_stock_qty_products
        CHECK (stock_quantity > 0);
# products triggers
DELIMITER //
CREATE TRIGGER tgr_created_date_product_datestamp
    BEFORE INSERT ON users FOR EACH ROW
BEGIN
    SET NEW.created_at = CURRENT_DATE;
END;
// DELIMITER ;

DELIMITER //
CREATE TRIGGER tgr_updated_date_product_datestamp
    BEFORE UPDATE ON users FOR EACH ROW
BEGIN
    SET NEW.updated_at = CURRENT_DATE;
END;
// DELIMITER ;

#orders table
CREATE TABLE orders(
    order_id        bigint PRIMARY KEY AUTO_INCREMENT                                      COMMENT '注文ID',
    serial_number   varchar(36)  default (uuid())                                            COMMENT 'シリアル番号',
    user_id         bigint                                                        NOT NULL COMMENT 'ユーザーID',
    total_price     decimal(10, 2)                                                         COMMENT '合計金額',
    status          enum ('WAITING', 'CONFIRM', 'DELIVERY', 'SUCCESS', 'CANCEL', 'DENIED') COMMENT '注文状態',
    note            varchar(100)                                                           COMMENT 'ノート',
    receive_name    varchar(100)                                                           COMMENT '届け先の名前',
    receive_address varchar(255)                                                           COMMENT '届け先の住所',
    receive_phone   varchar(15)                                                            COMMENT '届け先の電話番号',
    created_at      date                                                                   COMMENT '作成日付',
    received_at     date                                                                   COMMENT '配達予定日'
);
# orders FOREIGN KEY
ALTER TABLE orders
    ADD CONSTRAINT fk_orders_user_id
        FOREIGN KEY (user_id) REFERENCES users (user_id);

# order triggers
DELIMITER //
CREATE TRIGGER tgr_created_date_order_datestamp
    BEFORE INSERT ON orders FOR EACH ROW
BEGIN
    SET NEW.created_at = CURRENT_DATE;
    SET NEW.received_at = DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY);
END;
// DELIMITER ;

DELIMITER //
CREATE TRIGGER tgr_update_date_order_datestamp
    BEFORE UPDATE ON orders FOR EACH ROW
BEGIN
    SET NEW.created_at = CURRENT_DATE;
    SET NEW.received_at = DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY);
END;
// DELIMITER ;

DROP TRIGGER tgr_created_date_order_datestamp;

# order_details table
CREATE TABLE order_details(
    order_id       bigint         NOT NULL  COMMENT '注文ID',
    product_id     bigint         NOT NULL  COMMENT '商品ID',
    name           varchar(100)             COMMENT '商品名前',
    unit_price     decimal(10, 2)           COMMENT 'ユニット価格',
    order_quantity int                      COMMENT '注文数量',
    PRIMARY KEY (order_id, product_id)
);
# order_details FOREIGN KEY
ALTER TABLE order_details
    ADD CONSTRAINT fk_orders_details_oder_id
        FOREIGN KEY (order_id) REFERENCES orders (order_id);
ALTER TABLE order_details
    ADD CONSTRAINT fk_orders_details_product_id
        FOREIGN KEY (product_id) REFERENCES products (product_id);
# order_details checker
ALTER TABLE order_details
    ADD CONSTRAINT chk_order_qty_order_details
        CHECK (order_quantity > 0);

# shopping carts
CREATE TABLE shopping_carts(
    shopping_cart_id bigint PRIMARY KEY AUTO_INCREMENT COMMENT 'ショッピングカート',
    product_id       bigint                             COMMENT '商品ID',
    user_id          bigint                             COMMENT 'ユーザーID',
    order_quantity   int                                COMMENT '注文数量',
    constraint fk_cart_product_id
        FOREIGN KEY (product_id) REFERENCES products (product_id),
    constraint fk_cart_user_id
        FOREIGN KEY (user_id) REFERENCES users (user_id),
    constraint chk_order_qty_carts
        check (`order_quantity` > 0)
);
# shopping carts FOREIGN KEY
ALTER TABLE shopping_carts
    ADD CONSTRAINT fk_cart_product_id
        FOREIGN KEY (product_id) REFERENCES products (product_id);
ALTER TABLE shopping_carts
    ADD CONSTRAINT fk_cart_user_id
        FOREIGN KEY (user_id) REFERENCES users (user_id);
# shopping carts checker
ALTER TABLE shopping_carts
    ADD CONSTRAINT chk_order_qty_carts
        CHECK (order_quantity > 0);

# address table
CREATE TABLE address(
    address_id   bigint PRIMARY KEY AUTO_INCREMENT COMMENT '住所ID',
    user_id      bigint       COMMENT 'ユーザーID',
    full_address varchar(255) COMMENT '住所詳細',
    phone        varchar(15)  COMMENT '電話番号',
    receive_name varchar(50)  COMMENT '届け先の名前'
);
# address foreign key
ALTER TABLE address
    ADD CONSTRAINT fk_address_user_id
        FOREIGN KEY (user_id) REFERENCES users (user_id);

# Wish_lists table
CREATE TABLE wish_lists
(
    wish_list_id bigint PRIMARY KEY AUTO_INCREMENT COMMENT 'ウィッシュリスト',
    user_id      bigint                            COMMENT 'ユーザーID',
    product_id   bigint                            COMMENT '商品ID'
);
# wish_lists
ALTER TABLE wish_lists
    ADD CONSTRAINT fk_wish_lists_product_id
        FOREIGN KEY (product_id) REFERENCES products (product_id);
ALTER TABLE wish_lists
    ADD CONSTRAINT fk_wish_lists_user_id
        FOREIGN KEY (user_id) REFERENCES users (user_id);
