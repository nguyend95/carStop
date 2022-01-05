CREATE SCHEMA APP_DB;

CREATE TABLE IF NOT EXISTS app_db.user (
    ID INT PRIMARY KEY,
    EMAIL VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD VARCHAR(100) NOT NULL,
    FORENAME VARCHAR(50) NOT NULL,
    SURNAME VARCHAR(100) NOT NULL
);

insert into app_db.user (id, email, password, forename, surname) values (1, 'dmeake0@army.mil', 'IanlPLKP', 'Demott', 'Meake');
insert into app_db.user (id, email, password, forename, surname) values (2, 'ngerholz1@blogger.com', 'cL6R2f', 'Nonna', 'Gerholz');
insert into app_db.user (id, email, password, forename, surname) values (3, 'lworsfold2@cpanel.net', 'NGLtIAZ7t5cj', 'Lynette', 'Worsfold');
insert into app_db.user (id, email, password, forename, surname) values (4, 'griteley3@bravesites.com', 'YAK7KLDJww23', 'Giacobo', 'Riteley');
insert into app_db.user (id, email, password, forename, surname) values (5, 'chamerton4@wikimedia.org', 'SWNToIS', 'Coraline', 'Hamerton');
insert into app_db.user (id, email, password, forename, surname) values (6, 'ghankey5@narod.ru', 'Bu9vB4d', 'Gerhard', 'Hankey');
insert into app_db.user (id, email, password, forename, surname) values (7, 'amiall6@earthlink.net', 'pQFYcgAi5lj', 'Aliza', 'Miall');
insert into app_db.user (id, email, password, forename, surname) values (8, 'dtate7@nationalgeographic.com', 'g1W4zUNcho', 'Delmore', 'Tate');
insert into app_db.user (id, email, password, forename, surname) values (9, 'sblacklock8@rakuten.co.jp', 'zTwvl8OJr', 'Sile', 'Blacklock');
insert into app_db.user (id, email, password, forename, surname) values (10, 'n@gmail.com', '$2a$10$yjzT9w0QI/kZS8f8e7wUf.zycotAnrlv370oQ8tFBbKMc3N6IgHiq', 'Katherine', 'Alyoshin');