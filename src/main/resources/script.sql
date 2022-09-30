CREATE TABLE users
(
    name varchar(255) NOT NULL,
    login varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	PRIMARY KEY(login)
);
CREATE TABLE role
(
    id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL

);
CREATE TABLE user_role (
  user_id varchar(255),
  role_id INT,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(login),
  CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES role(id)
)
