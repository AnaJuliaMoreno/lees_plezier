insert into schedule (id, closes_at, day_of_week, opens_at)
values (nextval('schedule_seq'), 1800, 'monday', 900),
       (nextval('schedule_seq'), 1800, 'tuesday', 900),
       (nextval('schedule_seq'), 1800, 'wednesday', 900),
       (nextval('schedule_seq'), 1800, 'thursday', 900),
       (nextval('schedule_seq'), 1800, 'friday', 900),
       (nextval('schedule_seq'), 1800, 'saturday', 900),
       (nextval('schedule_seq'), 1700, 'sunday', 1200);

insert into location (id, name, address_loc)
VALUES (nextval('location_seq'), 'Neude', 'Neude Square, Utrecht, Netherlands'),
       (nextval('location_seq'), 'De Meern', 'De Meern, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Hoograven', 'Hoograven, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Kanaleneiland', 'Kanaleneiland, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Leidsche Rijn Centrum', 'Leidsche Rijn Centrum, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Lunetten', 'Lunetten, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Oog in Al', 'Oog in Al, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Overvecht', 'Overvecht, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Tuinwijk', 'Tuinwijk, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Vleuten', 'Vleuten, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Vleuterweide', 'Vleuterweide, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Waterwin', 'Waterwin, Utrecht, Netherlands'),
       (nextval('location_seq'), 'Zuilen', 'Zuilen, Utrecht, Netherlands');

insert into child(id, age, name)
VALUES (nextval('child_seq'), 10, 'Peter');


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

INSERT INTO reading_focus (name, description, useful_info)
VALUES ('Vlotheid', 'Om vloeiend te kunnen lezen, moeten kinderen',
        'Snel lezen voor begrip en snelheid in tekstverwerking.'),
       ('Woordenschat',
        'Om te begrijpen wat je leest, moet je de meeste woorden in de tekst begrijpen. Een sterke woordenschat is een belangrijk onderdeel van begrijpend lezen. Leerlingen kunnen woordenschat leren door middel van instructie, in de klas.',
        'Het begrijpen van woorden in een tekst voor leesbegrip.'),
       ('Decode',
        'Decoderen is een essentiële stap in het leesproces. Kinderen gebruiken deze vaardigheid om woorden die ze eerder hebben gehoord, maar nog niet uitgeschreven hebben gezien, te verklanken.',
        'Vaardigheid om geschreven woorden correct uit te spreken.'),
       ('Fonetisch bewustzijn',
        'Me woorden direct herkennen, ook woorden die ze niet kunnen uitspreken. Vloeiendheid versnelt de snelheid waarmee ze tekst kunnen lezen en vooral begrijpen.nsen denken vaak dat lezen begint met het leren klinken van letters.',
        'Herkennen en gebruiken van gesproken taalklanken in geschreven woorden.'),
       ('Begrijpend lezen',
        'Begrijpend lezen is het vermogen om de betekenis van een tekst te verwerken en te begrijpen. Wanneer we een tekst begrijpen.',
        'Het vermogen om de betekenis van een tekst te begrijpen en te integreren.'),
       ('Geheugen en aandacht',
        'Als kinderen lezen, stelt aandacht hen in staat om informatie uit de tekst op te nemen. Het geheugen stelt ze in staat om die informatie vast te houden en te gebruiken om betekenis te krijgen en kennis op te bouwen uit wat ze lezen.',
        'Het vermogen om informatie vast te houden en te begrijpen tijdens het lezen.');

insert into child_focus(child_id, reading_focus_name)
VALUES (1, 'Decode'),
       (1, 'Woordenschat');

insert into book_subjects(book_isbn, book_subjects)
VALUES (9384593874534, 'adventure'),
       (9384593874534, 'dyslexia');

insert into child_availability(start_at, child_id, day)
VALUES ('12:00:00', 1, 'tuesday');


INSERT INTO users (id, username, password, role, email, last_name)
VALUES
    --password password
    (nextval('user_seq'), 'JohnDoe', '$2a$10$nnd72WdUamIcghWrl1MRqeB9c22Y86mMLFo3nooslZ.z0vj3e38q6', 'ROLE_VOLUNTEER',
     'john.doe@example.com', 'Doe'),
    --password parent
    (nextval('user_seq'), 'parent', '$2a$10$oN8vHL6Agnpu7gXbcnPGtOyPiVRDtvk/MwrcAewBlhvk9Tys440SG', 'ROLE_PARENT',
     'parent@email.com', 'parent'),
    --password volunteer
    (nextval('user_seq'), 'volunteer', '$2a$10$YixaeAi5VU7ZpC/AUBO5AewU8FbBdRK53z0uPH55HeX1Xa0r/grbK', 'ROLE_VOLUNTEER',
     'volunteer@email.com', 'volunteer'),
    --password admin
    (nextval('user_seq'), 'admin', '$2a$10$6APMlxMCL7uaiuslBaSkMekEztzrWjq4GyL2MobUBKH007M7v/wGe', 'ROLE_ADMIN',
     'admin@example.com', 'admin')



