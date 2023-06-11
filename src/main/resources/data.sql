insert into room_types(name) values ('focus'),
                                    ('team'),
                                    ('conference');

insert into rooms(name, type_id, capacity)  values('mytaxi',1,20),
                                                  ('workly',2,15),
                                                  ('express24',3,15);

insert into residents(name) values ('Anvar Narzullayev'),
                                   ('Azimjon Polatov')