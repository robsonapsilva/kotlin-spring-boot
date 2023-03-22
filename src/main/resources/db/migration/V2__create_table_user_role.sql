CREATE TABLE "PUBLIC".user_role
(
    user_id int not null,
    role    varchar(255) not null,
    FOREIGN KEY (user_id) REFERENCES user (id),
    PRIMARY KEY (user_id, role)
);