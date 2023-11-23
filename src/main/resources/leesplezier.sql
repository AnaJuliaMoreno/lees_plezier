insert into schedule (id,closes_at,day_of_week,opens_at)
values(nextval('schedule_seq'), 1800,'monday',900),
(nextval('schedule_seq'), 1800,'tuesday',900),
(nextval('schedule_seq'), 1800,'wednesday',900),
      (nextval('schedule_seq'), 1800,'thursday',900),
      (nextval('schedule_seq'), 1800,'friday',900),
      (nextval('schedule_seq'), 1800,'saturday',900);

insert into location (id, address_loc, name_loc)
VALUES(nextval('location_seq'),'Lamadreef 34', 'Zuilen')