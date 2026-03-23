DELETE FROM wallets;
DELETE FROM students;

INSERT INTO students (id, full_name, email, password, registration_number, student_type, cpf, role)
VALUES (
    CAST('f67d12f4-6cbd-4f3c-882e-148be349b688' AS UUID),
    'Rafael Souza de Almeida',
    'rafael@ufrrj.br',
    '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW',
    '20240015099',
    'UNDERGRADUATE',
    '19781451793',
    'ROLE_STUDENT'
);

INSERT INTO wallets (id, student_id, breakfast_balance, lunch_dinner_balance)
VALUES (
    CAST('a1b2c3d4-e5f6-4a5b-8c9d-0123456789ab' AS UUID),
    CAST('f67d12f4-6cbd-4f3c-882e-148be349b688' AS UUID),
    0,
    0
);