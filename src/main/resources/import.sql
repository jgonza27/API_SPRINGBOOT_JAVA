-- Insertar usuarios de ejemplo (password: "password123" encriptado con BCrypt)
-- Para generar nuevas contraseñas usa: https://bcrypt-generator.com/
INSERT INTO users (id, username, password, role, enabled) VALUES (1, 'admin', '{bcrypt}$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_ADMIN', true);
INSERT INTO users (id, username, password, role, enabled) VALUES (2, 'user', '{bcrypt}$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', true);

-- Insertar pacientes de ejemplo
INSERT INTO pacientes (id, nombre, antecedentes) VALUES (1, 'Juan Pérez', 'Hipertensión arterial');
INSERT INTO pacientes (id, nombre, antecedentes) VALUES (2, 'María García', 'Diabetes tipo 2');
INSERT INTO pacientes (id, nombre, antecedentes) VALUES (3, 'Pedro López', 'Sin antecedentes relevantes');
INSERT INTO pacientes (id, nombre, antecedentes) VALUES (4, 'Ana Martínez', 'Asma bronquial');

-- Insertar sanitarios de ejemplo
INSERT INTO sanitario (id, name, especialidad) VALUES (1, 'Dr. Carlos Ruiz', 'Medicina General');
INSERT INTO sanitario (id, name, especialidad) VALUES (2, 'Dra. Ana Martínez', 'Cardiología');
INSERT INTO sanitario (id, name, especialidad) VALUES (3, 'Dr. Luis Fernández', 'Endocrinología');
INSERT INTO sanitario (id, name, especialidad) VALUES (4, 'Dra. Carmen Silva', 'Neumología');

-- Insertar citas de ejemplo
INSERT INTO cita (id, fecha_hora, sintomas, paciente_id) VALUES (1, '2024-12-15 10:00:00', 'Dolor de cabeza persistente', 1);
INSERT INTO cita (id, fecha_hora, sintomas, paciente_id) VALUES (2, '2024-12-16 11:30:00', 'Control de diabetes', 2);
INSERT INTO cita (id, fecha_hora, sintomas, paciente_id) VALUES (3, '2024-12-17 09:00:00', 'Chequeo general', 3);
INSERT INTO cita (id, fecha_hora, sintomas, paciente_id) VALUES (4, '2024-12-18 15:00:00', 'Dificultad para respirar', 4);

-- Relacionar sanitarios con citas (tabla intermedia)
INSERT INTO sanitario_cita (sanitario_id, cita_id) VALUES (1, 1);
INSERT INTO sanitario_cita (sanitario_id, cita_id) VALUES (3, 2);
INSERT INTO sanitario_cita (sanitario_id, cita_id) VALUES (1, 3);
INSERT INTO sanitario_cita (sanitario_id, cita_id) VALUES (4, 4);
INSERT INTO sanitario_cita (sanitario_id, cita_id) VALUES (2, 4); -- Cita con múltiples sanitarios