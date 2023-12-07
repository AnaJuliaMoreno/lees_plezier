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

insert into child(id, age, grade, name)
VALUES (nextval('child_seq'), 10, 6, 'Sara');

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

