insert into user_details(id,birth_date,name)
values(10001, current_date(), 'Andi');

insert into user_details(id,birth_date,name)
values(10002, current_date(), 'Nadi');

insert into user_details(id,birth_date,name)
values(10003, current_date(), 'Duda');

insert into post(id,description,user_id)
values(20001, 'I want a remote job in 2023', 10001);

insert into post(id,description,user_id)
values(20002,  'I want to travel more', 10001);

insert into post(id,description,user_id)
values(20003, 'I want a new career in product management', 10002);

insert into post(id,description,user_id)
values(20004, 'I want to exercise more', 10002);
