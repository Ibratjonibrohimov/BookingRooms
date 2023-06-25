insert into room_types(name) values ('focus'),
                                    ('team'),
                                    ('conference');

insert into rooms(name, type_id, capacity)  values('mytaxi',1,1),
                                                  ('workly',2,5),
                                                  ('express24',3,15);

commit