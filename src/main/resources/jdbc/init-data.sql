-- PASSWORD = admin
INSERT INTO users (
    id,
    created_at,
    updated_at,
    created_by,
    updated_by,
    name,
    email,
    password
) VALUES (
    'b0501818-f1a8-11ec-8ea0-0242ac120002',
    '2022-06-21T00:00:00',
    '2022-06-21T00:00:00',
    'admin@admin.com.br',
    'admin@admin.com.br',
    'Admin',
    'admin@admin.com.br',
    '$2a$12$.wIt6/gZOCOzadiaKlrTluu3nIDSM7X4kIIciNZlbq.AyMW7FIENC'
);

INSERT INTO roles (
    id,
    created_at,
    updated_at,
    created_by,
    updated_by,
    name,
) VALUES (
    '9c4f933e-f6d0-11ec-b939-0242ac120002',
    '2022-06-21T00:00:00',
    '2022-06-21T00:00:00',
    'admin@admin.com.br',
    'admin@admin.com.br',
    'ADMIN'
);

INSERT INTO roles (
    id,
    created_at,
    updated_at,
    created_by,
    updated_by,
    name,
) VALUES (
    '97ea1cbe-f7df-11ec-b939-0242ac120002',
    '2022-06-21T00:00:00',
    '2022-06-21T00:00:00',
    'admin@admin.com.br',
    'admin@admin.com.br',
    'SERVICE_DESK'
);

INSERT INTO roles (
    id,
    created_at,
    updated_at,
    created_by,
    updated_by,
    name,
) VALUES (
    '7b493b2a-f7e0-11ec-b939-0242ac120002',
    '2022-06-21T00:00:00',
    '2022-06-21T00:00:00',
    'admin@admin.com.br',
    'admin@admin.com.br',
    'CLIENT'
);

INSERT INTO users_roles (
    id,
    created_at,
    updated_at,
    created_by,
    updated_by,
    role_id,
    user_id
) VALUES (
    '9f760dc2-f6d0-11ec-b939-0242ac120002',
    '2022-06-21T00:00:00',
    '2022-06-21T00:00:00',
    'admin@admin.com.br',
    'admin@admin.com.br',
    '9c4f933e-f6d0-11ec-b939-0242ac120002',
    'b0501818-f1a8-11ec-8ea0-0242ac120002'
);

INSERT INTO users_roles (
    id,
    created_at,
    updated_at,
    created_by,
    updated_by,
    role_id,
    user_id
) VALUES (
    'e4ed3f86-f7e0-11ec-b939-0242ac120002',
    '2022-06-21T00:00:00',
    '2022-06-21T00:00:00',
    'admin@admin.com.br',
    'admin@admin.com.br',
    '97ea1cbe-f7df-11ec-b939-0242ac120002',
    'b0501818-f1a8-11ec-8ea0-0242ac120002'
);
