-- ==================== 1 ====================
CREATE DATABASE springshop

USE springshop;
-- ~~Khéch hang khi mu6n mua hang => phéi déng ky téi khoén => béng users
-- mat khau ma hoa
CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(10) NOT NULL,
    address VARCHAR(200) DEFAULT '',
    password VARCHAR(100) NOT NULL DEFAULT '', 
    created_at DATETIME,
    updated_at DATETIME,
    is_active TINYINT(1) DEFAULT 1,
    date_of_birth DATE,
    facebook_account_id INT DEFAULT 0,
    google_account_id INT DEFAULT 0
);

-- user_id : fk
CREATE TABLE tokens (
    id INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_rybe VARCHAR(50) NOT NULL,
    expiration_date DATETIME,
    revoked TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ==================== 3 =======================
ALTER TABLE users ADD COLUMN role_id INT;
CREATE TABLE roles (
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);
ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles (id);


-- ==================== 2 ====================
-- sp login = gg & facebook
CREATE TABLE social_accounts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    provider VARCHAR(29) NOT NULL COMMENT 'Tn đơn vị social network',
    provider_id VARCHAR(50) NOT NULL,
    email VARCHAR(156) NOT NULL COMMENT 'Email tài khoản',
    name VARCHAR(169) NOT NULL COMMENT 'Ten người dùng',
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- bang danh muc san pham (category)
CREATE TABLE categories(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) NOT NULL DEFAULT '' COMMENT 'Tén danh muc, vd: do dien tu'
);

-- bang product
CREATE TABLE products(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(300) COMMENT 'Ten san pham',
    price FLOAT NOT NULL CHECK (price >= 0),
    thumbnail VARCHAR(300) DEFAULT '',
    description LONGTEXT DEFAULT '',
    created_at DATETIME,
    updated_at DATETIME,
    category_id INT,
    FOREIGN KEY(category_id) REFERENCES categories(id)
);

-- ==================== 3 ====================
CREATE TABLE orders {
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users(id),
    fullname VARCHAR(100) DEFAULT '',
    email VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    note VARCHAR(100) DEFAULT '',
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20),
    total_money FLOAT CHECK (total_money >= 0)

};

ALTER TABLE orders ADD COLUMN `shipping_method` VARCHAR(100);
ALTER TABLE orders ADD COLUMN `shipping_address` VARCHAR(200);
ALTER TABLE orders ADD COLUMN `shipping_date` DATE;
ALTER TABLE orders ADD COLUMN `tracking_number` VARCHAR(100);
ALTER TABLE orders ADD COLUMN `payment_metho` VARCHAR(100);

--không delete data mà chỉ đổi active thành disable là dc
ALTER TABLE orders ADD COLUMN active TINYINT(1)
-- trạngt hi đơn hàng chỉ dc nhận 1 số giá trị cụ thể thôi
MODIFY COLUMN status ENUM('pending', 'processing', 'shipped','delivered','cancelled') COMMENT 'Trang thai don hang';


CREATE TABLE order_details(
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES products (id),
    price FLOAT CHECK(price >= 0),
    number_of_products INT CHECK(number_of_products > 0),
    tota1_money FLOAT CHECK (total_money >= 0),
    color VARCHAR(20) DEFAULT ''
);


-- /////////////////////////////////////////////////////////
-- Table for authentication tokens
CREATE TABLE IF NOT EXISTS tokens (
    id INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_rybe VARCHAR(50) NOT NULL,
    expiration_date DATETIME,
    revoked TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


-- Table for social media accounts linked to users
CREATE TABLE IF NOT EXISTS social_accounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    provider VARCHAR(29) NOT NULL COMMENT 'Name of the social network provider',
    provider_id VARCHAR(50) NOT NULL,
    email VARCHAR(156) NOT NULL COMMENT 'Email of the account',
    name VARCHAR(169) NOT NULL COMMENT 'Name of the user',
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Table for product categories
CREATE TABLE IF NOT EXISTS categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'Name of the category, e.g., electronics'
);

-- Table for products
CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(300) COMMENT 'Name of the product',
    price FLOAT NOT NULL CHECK (price >= 0),
    thumbnail VARCHAR(300) DEFAULT '',
    description LONGTEXT DEFAULT '',
    created_at DATETIME,
    updated_at DATETIME,
    category_id INT,
    FOREIGN KEY(category_id) REFERENCES categories(id)
);

-- Table for orders
CREATE TABLE IF NOT EXISTS orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    fullname VARCHAR(100) DEFAULT '',
    email VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    note VARCHAR(100) DEFAULT '',
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled') COMMENT 'Order status',
    total_money FLOAT CHECK (total_money >= 0),
    shipping_method VARCHAR(100),
    shipping_address VARCHAR(200),
    shipping_date DATE,
    tracking_number VARCHAR(100),
    payment_method VARCHAR(100),
    active TINYINT(1),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Table for order details
CREATE TABLE IF NOT EXISTS order_details (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id INT,
    price FLOAT CHECK(price >= 0),
    number_of_products INT CHECK(number_of_products > 0),
    total_money FLOAT CHECK (total_money >= 0),
    color VARCHAR(20) DEFAULT '',
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);






