INSERT INTO user (name, status, email, password)
VALUES ('System user', 'ACTIVE', 'system-user@poc.com', '$2a$12$vY0nPF7/z2f2S2MFKk5vwetdUVMcz6iozKwZdgaaio4FlE3zkc.L.');

INSERT INTO user_role(user_id, role)
values (1, 'ADMIN');

