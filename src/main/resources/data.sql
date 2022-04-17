INSERT INTO region (id, name, country, department, postcode) VALUES
(1, 'ILE-DE-FRANCE', 'FRANCE', 'Département de Paris', 75),
(2, 'GRAND EST', 'FRANCE', 'Moselle', 57);

INSERT INTO city (id, name, postcode, idregion) VALUES
(1, 'Paris', 75019, 1),
(2, 'Metz', 57000, 2),
(3, 'Metz', 57070, 2);

INSERT INTO address (id, number, street, idcity) VALUES
(1, 1, 'rue de la Liberté', 1),
(2, 5, 'place de la République', 2),
(3, 1, 'rue de Bourgogne', 3),
(4, 55, 'boulevard d''Alsace', 3);

INSERT INTO users (id, firstname, lastname, birthdate, phone_number, gender, idaddress, username, password, email, enabled) VALUES
(1, 'John', 'DOE', '2000-04-30', '0123456789', 'MALE', 1, 'admin', '$2y$10$ccWZxkKo/drx5OorDVA5XO2IgZq5NAF8UUBTajBap2uVqoIiccTpm', 'joe@hotmail.com', true),
(2, 'Jane', 'DOE', '2004-12-31', '9876543210', 'FEMALE', 2, 'janedoe', '$2y$10$QGDOeGGx1C3kUj8jZfhq/O7qi1YoEGCzrUHWL924bExCodtxQlcHO', 'jane@gmail.com', true),
(3, 'James', 'BROWN', '1955-02-28', '0682691966', 'MALE', 3, 'user', '$2y$10$QGDOeGGx1C3kUj8jZfhq/O7qi1YoEGCzrUHWL924bExCodtxQlcHO', 'jb@outlook.fr', true),
(4, 'Amy', 'KAL', '1960-07-01', '0798789754', 'FEMALE', 4, 'amykal', '$2y$10$QGDOeGGx1C3kUj8jZfhq/O7qi1YoEGCzrUHWL924bExCodtxQlcHO', 'amykal@yahoo.fr', true);

INSERT INTO user_role (user_id, role) VALUES
(1, 'ADMIN'),
(1, 'USER'),
(2, 'USER'),
(3, 'USER'),
(4, 'USER');