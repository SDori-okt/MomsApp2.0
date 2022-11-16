INSERT INTO users(email, first_name, house_number, last_name, location, password, street, user_name, date_of_birth,
                  delete)
VALUES ('admin@momsapp.info', 'First', '1', 'Admin',
        'Győr', '$2a$10$4HoOeX.5beAWkeuxAYnNAuZhV.ow0JImqfz9w6kqAeY.KeuqCJTr.', 'main', 'Admin', '2022-01-01', false),
       ('creator@momsapp.info', 'First', '2', 'Creator',
        'Győr', '$2a$10$TgVsioDH4MJyElD1Q61Vz.YVVY4ktJomrZx1NghlXqlWdlCZBZUx2', 'main', 'Creator', '2022-01-01', false);

INSERT INTO user_roles(user_id, user_role)
VALUES (1, 'ADMIN'),
       (2, 'CREATOR');

INSERT INTO events(event_name, description, event_date_time)
VALUES ('admin-creator event', 'Ez az első közös pelenkázás', '2022-12-10 00:00:00');

INSERT INTO events_users(event_id, user_id)
VALUES (1, 1),
       (1, 2);

