
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