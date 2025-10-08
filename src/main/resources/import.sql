INSERT INTO department (id, email, name, phone) VALUES (100, 'informatica@iescelia.org', 'Informática', '950 15 61 51');
INSERT INTO department (id, email, name, phone) VALUES (101, 'FOL@iescelia.org', 'FOL', '950 15 61 51');

INSERT INTO employee (department_id, dni, username, name, password, is_admin) VALUES (100, '75654321S', 'john', 'John Doe', '{noop}1234', 0);
INSERT INTO employee (department_id, dni, username, name, password, is_admin) VALUES (101, '75432100X', 'jane', 'Jane Doe', '{noop}4321', 0);
INSERT INTO employee (department_id, dni, username, name, password, is_admin) VALUES (100, '12345678A', 'admin', 'Administrador', '{noop}admin', 1);
