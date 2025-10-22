CREATE TABLE books (
   id SERIAL PRIMARY KEY,
   title VARCHAR(150) NOT NULL,
   author VARCHAR(100) NOT NULL,
   publication_date DATE,
   genre VARCHAR(50),
   price DECIMAL(10, 2),
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
