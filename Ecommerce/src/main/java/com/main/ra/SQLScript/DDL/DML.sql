
use ecommerce;

INSERT INTO users(
    user_name  ,
    email      ,
    full_name  ,
    password   ,
    phone      ,
    address
)VALUES (
    'tung_123',
         'tung124@edu.com',
         'vu thanh tung',
         '12345',
         '0198984230',
         'akjdfghsdjkfhijsd'
        );

SELECT * FROM users WHERE user_name LIKE '%tung%' ESCAPE '\\';

SELECT * FROM address;
SELECT * FROM categories;
SELECT * FROM order_details;
SELECT * FROM orders;
SELECT * FROM products;
SELECT * FROM roles;
SELECT * FROM shopping_carts;
SELECT * FROM user_roles;
SELECT * FROM users;
SELECT * FROM wish_lists;