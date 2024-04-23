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
# users CHECKer
ALTER TABLE users
    ADD CONSTRAINT chk_user_name_length_users CHECK(length(user_name) >= 6);
ALTER TABLE users
    ADD CONSTRAINT chk_user_name_fmt_users CHECK(user_name NOT REGEXP '[^a-zA-Z0-9\s]');
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
    user_id bigint NOT NULL COMMENT 'ユーザーID',
    role_id bigint NOT NULL COMMENT '許可ID',
    PRIMARY KEY (user_id, role_id)
);
# user_role foreign key
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
create table products
(
    product_id     bigint PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
    sku            varchar(100)                      COMMENT '商品コード',
    product_name   varchar(100)   NOT NULL           COMMENT '商品名前',
    description    text                              COMMENT '説明',
    unit_price     decimal(10, 2) COMMENT 'ユニット価格',
    stock_quantity int            COMMENT '在庫数量',
    image          varchar(255)   COMMENT '写真',
    category_id    bigint         COMMENT 'カテゴリーID',
    created_at     date           COMMENT '作成日付',
    updated_at     date           COMMENT '更新日付'
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
