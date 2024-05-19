insert into schedule (id, closes_at, day_of_week, opens_at)
values (nextval('schedule_seq'), 1800, 'monday', 900),
       (nextval('schedule_seq'), 1800, 'tuesday', 900),
       (nextval('schedule_seq'), 1800, 'wednesday', 900),
       (nextval('schedule_seq'), 1800, 'thursday', 900),
       (nextval('schedule_seq'), 1800, 'friday', 900),
       (nextval('schedule_seq'), 1800, 'saturday', 900),
       (nextval('schedule_seq'), 1700, 'sunday', 1200);

insert into location (id, address_loc, name_loc)
VALUES (nextval('location_seq'), 'Lamadreef 34', 'Zuilen');

insert into child(id, age, grade, name, location_id)
VALUES (nextval('child_seq'), 10, 6, 'Peter', 1);


insert into book(ISBN, target_age, AUTHOR, TITLE)
VALUES (9384593874534, 9, 'Tara Bach', 'Brave monster.');

insert into location_schedule(location_id, schedule_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7);

insert into reading_focus(description, name, useful_info)
VALUES ('Kids use this skill to sound out words they’ve heard before but haven’t seen written out. Decoding relies on an early language skill called phonemic awareness which lets kids hear individual sounds in words and “play” with sounds',
        'Decoding', 'www.understood.org'),
       ('To read fluently, kids need to instantly recognize words, including words they can’t sound out. Fluency speeds up the rate at which they can read and understand text. It’s also important when kids encounter irregular words',
        'Fluency',
        'Exposure, repetition, build vocabulary. Children with dyslexia may need to see a word up to 40 times before they recognize it automatically. ');

insert into child_focus(child_id, reading_focus_name)
VALUES (1, 'Fluency'),
       (1, 'Decoding');

insert into book_subjects(book_isbn, book_subjects)
VALUES (9384593874534, 'adventure'),
       (9384593874534, 'dyslexia');

insert into child_availability_list(start_at, child_id, day)
VALUES (1200, 1, 'tuesday');


INSERT INTO users (id, username, password, role, email, last_name)
VALUES
    (nextval('user_seq'), 'JohnDoe', 'P@ssw0rd123', 'ROLE_VOLUNTEER', 'john.doe@example.com', 'Doe'),
       (nextval('user_seq'), 'AliceSmith', 'Alic3_Sm1th', 'ROLE_PARENT', 'alice.smith@example.com', 'Smith'),
       (nextval('user_seq'), 'MichaelJohnson', 'M1k3_J0hns0n', 'ROLE_PARENT', 'michael.johnson@example.com', 'Johnson'),
       (nextval('user_seq'), 'EmilyBrown', 'Br0wnEm1ly', 'ROLE_VOLUNTEER', 'emily.brown@example.com', 'Brown'),
       (nextval('user_seq'), 'DanielRodriguez', 'D@n13l_R0dr1gu3z', 'ROLE_VOLUNTEER', 'daniel.rodriguez@example.com',
        'Rodriguez'),
--     (6, 'parent', 'parent', 'ROLE_PARENT', 'parent@email.com', 'parent' ),
      (nextval('user_seq'), 'admin', 'password', 'ROLE_ADMIN', 'admin@example.com', 'admin');

